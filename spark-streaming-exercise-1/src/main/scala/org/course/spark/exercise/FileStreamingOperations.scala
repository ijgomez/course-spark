package org.course.spark.exercise

import org.apache.spark.sql.SparkSession

import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.DoubleType
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.TimestampType

import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.OutputMode

object FileStreamingOperations extends App {
    
  val sparkSession = SparkSession.builder().getOrCreate()
  
  import sparkSession.implicits._
  
  val schemaVehiculo = new StructType()
    .add("idVehiculo", StringType)
    .add("velocidad", DoubleType)
    .add("hora", TimestampType);

  val datosTraficoDF = sparkSession.readStream
    .schema(schemaVehiculo)
    .option("sep", ",")
    .csv("./datos/stream")
    
   val resultadosDF = datosTraficoDF
     .groupBy(
         window($"hora", "2 minutes", "2 minutes"),
         $"idVehiculo"
     )
     .agg(
          avg($"velocidad"), 
          max($"velocidad"), 
          min($"velocidad")
     ) 
     
     resultadosDF.writeStream.format("console").outputMode(OutputMode.Complete()).start().awaitTermination()
}
