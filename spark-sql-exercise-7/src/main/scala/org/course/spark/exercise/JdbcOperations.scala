package org.course.spark.exercise

import org.apache.spark.sql.SparkSession
import java.util.Properties

object JdbcOperations extends App {

 val sparkSession = SparkSession.builder().getOrCreate()
  
  val jdbcConfig = new Properties()
  jdbcConfig.put("user", "cursos")
  jdbcConfig.put("password", "cursos")
  
  // para pareliliza la carga de datos
  jdbcConfig.put("numPartitions", "4")
  jdbcConfig.put("partitionColumn", "customer_id")
  jdbcConfig.put("lowerBound", "1")
  jdbcConfig.put("upperBound", "" + Long.MaxValue)
  
  val clientesDF = sparkSession.read.jdbc(
      "jdbc:postgresql://localhost:5432/cursos", 
      "customer", 
      jdbcConfig)
  
      clientesDF.printSchema()
      
  clientesDF.select("first_name", "last_name").show()   
      
   sparkSession.stop()
}
