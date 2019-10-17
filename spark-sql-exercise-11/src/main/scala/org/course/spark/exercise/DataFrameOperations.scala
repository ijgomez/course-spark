package org.course.spark.exercise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import org.course.spark.exercise.helper.StringHelper._

object DataFrameOperations extends App {
    
   val sparkSession = SparkSession.builder().getOrCreate()
  
  val schemaLog = new StructType()
    .add("Host", StringType, false)
    .add("Fecha", StringType, false)
    .add("Comando", StringType, false)
    .add("URI", StringType, false)
    .add("Estado", IntegerType, false)
    .add("Longitud", StringType, false)
  
  val REG_EXP = raw"""^(\S+) (\S+) (\S+) \[([\w:/]+\s[+\-]\d{4})\] "(\S+) (\S+)\s*(\S*)" (\d{3}) (\S+)""".r;  
    
  val logsRDD = sparkSession.sparkContext.textFile("./data/NASA_access_log_Aug95.gz")
  
  val logsLeidos = logsRDD.count();
  
  val logsOk = logsRDD.filter((linea) => {
    REG_EXP.findFirstMatchIn(linea) match {
      case Some(_) => true
      case None => false
    }
  })
  
  val logsOkLeidos = logsOk.count();
  
  
  println(s"""
    Logs Leidos: $logsLeidos
    Logs OK:     $logsOkLeidos
    """)
    
    val logsRDDD = logsOk.map((linea) => {
      linea match {
        case REG_EXP(host, _, _, fecha, comando, uri, _, estado, longitud) => Row(host, fecha, comando, uri, estado.toInt, longitud)
        
      }
    })
    
     val logsDF = sparkSession.createDataFrame(logsRDDD, schemaLog)
  
     
  logsDF.select("Host")
    .groupBy("Host")
    .count()
    .orderBy("Host")
    .show()
    
    logsDF.select("Estado")
    .groupBy("Estado")
    .count()
    .orderBy("Estado")
    .show()

  sparkSession.stop()
}
