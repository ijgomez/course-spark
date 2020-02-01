package org.course.spark.exercise

import org.apache.spark.sql.SparkSession

object BasicOperations extends App {

  val sparkSession = SparkSession.builder().getOrCreate()
  
  import sparkSession.implicits._
  
  val vuelosDF = sparkSession.read.format("csv")
      .option("sep", ",")
      .option("encoding", "UTF-8")
      .option("header", true)
      .option("inferSchema", true)
      .option("mode", "PERMISSIVE") //Por defecto, carga todo
      // .option("mode", "DROPMALFORMED") //Avisa de lineas incorrectas
      // .option("mode", "FAILFAST") // error de carga si hay lineas incorrectas
    .load("./data/1987.csv")
  
    // Imprime el schema 
  vuelosDF.printSchema()  
  
  // para aplicar directamente SQL
  
  // asignamos un nombre de vista al dataframe
  vuelosDF.createOrReplaceTempView("VUELOS1987")
  
  val informeDF = sparkSession.sql(
      """
        select 
          Origin, SUM(CAST(Distance AS INTEGER)) as Total 
        from 
          VUELOS1987
        group by 
          Origin
        order by
          Origin 
      """
    )
   informeDF.show(5000, false)  
   
   // Para poder compartir DataFrames entre diferentes sessiones.
   vuelosDF.createOrReplaceGlobalTempView("DATOSVUELOS")
  
   val sparkSession2 = sparkSession.newSession()
   
   sparkSession2.sql(
       """
         select count(*) as NumVuelos from global_temp.DATOSVUELOS
      """
       ).show()
  sparkSession2.stop()
       
       
  sparkSession.stop()
}
