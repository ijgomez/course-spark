package org.course.spark.exercise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SaveMode

object JsonOperations extends App {

  val sparkSession = SparkSession.builder().getOrCreate()
  
  import sparkSession.implicits._
  
  val alumnosDF = sparkSession.read.json("./data/students.json")
    
  alumnosDF.printSchema()
  
  
  val empresasDF = sparkSession.read.json("./data/companies.json")
    
  empresasDF.printSchema()
  
  empresasDF.createOrReplaceTempView("empresas")
  
  
  val consultaDF = sparkSession.sql(
      """
        select 
          name, number_of_employees, phone_number,founded_year 
        from 
          empresas
      """
   )
  consultaDF.show(20, false)
  
  consultaDF.coalesce(1).write
    .mode(SaveMode.Append)
    .parquet("./parquet/empresas")
  
    //////////////////////////////////////////////////////////////////
  
 val datosDF = sparkSession.read.parquet("./parquet/empresas")
 
 datosDF.printSchema()
 
 datosDF.select("name", "number_of_employees").groupBy("name").sum("number_of_employees").show()
 
  
  sparkSession.stop()
}
