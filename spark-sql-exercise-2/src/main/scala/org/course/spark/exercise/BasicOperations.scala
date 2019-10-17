package org.course.spark.exercise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

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
  
  
  val prueba1DF = vuelosDF.select("Origin", "Distance")
    .withColumn("Distance", 'Distance.cast("Int"))
    .groupBy("Origin") //Por nombre
    // .groupBy($"Origin") // Por tipo Column
    .sum("Distance")
    .withColumnRenamed("sum(Distance)", "Total")
  
    
  prueba1DF.show(10, false)
  
  // Para incorporar una funcion de usuario
  
  def funcionUsuario(dato : String) : String = {
    dato + " cambiado"
  }
  
  val fUsuario = udf(funcionUsuario _)
  
  val prueba2DF = vuelosDF.select("Origin", "Distance", "Month")
  .withColumn("Nueva", concat($"Distance", $"Month"))
  .withColumn("Generada", fUsuario($"Origin"))
  .filter("Distance > '800'")
  
 prueba2DF.show()
       
 ////////////////////////////////////////////////
 
 // Estadisticas basicas de todas las columnas
 //vuelosDF.describe().show()
 
 vuelosDF.describe("DepDelay", "Distance").show()
 
 vuelosDF.select("Origin", "ArrDelay", "DepDelay")
   .withColumn("ArrDelay", 'ArrDelay.cast("Int"))
   .withColumn("DepDelay", 'DepDelay.cast("Int"))
   .groupBy("Origin")
   .avg("ArrDelay", "DepDelay").show()
 
 ////////////////////////////////////////////////////////
   
 vuelosDF.select("Origin", "ArrDelay", "DepDelay")
   .withColumn("ArrDelay", 'ArrDelay.cast("Int"))
   .withColumn("DepDelay", 'DepDelay.cast("Int"))
   .groupBy("Origin")
   .agg(
       avg($"ArrDelay"),
       avg($"DepDelay"),
       max($"DepDelay"),
       min($"DepDelay"),
       stddev($"DepDelay"),
       variance($"DepDelay"),
       corr($"DepDelay", $"ArrDelay")
      )
   .show()
   
  sparkSession.stop()
}
