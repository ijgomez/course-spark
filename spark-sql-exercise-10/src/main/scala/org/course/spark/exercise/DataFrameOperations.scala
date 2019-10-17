package org.course.spark.exercise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import org.course.spark.exercise.helper.StringHelper._

object DataFrameOperations extends App {
    
  val sparkSession = SparkSession.builder().getOrCreate()
  
  val schemaVuelo = new StructType()
    .add("AeropuertoOrigen", StringType, false)
    .add("AeropuertoDestino", StringType, false)
    .add("DistanciaMillas", LongType, false)
  
  val vuelosRDD = sparkSession.sparkContext.textFile("./data/1987.csv").map((linea) => {
    val campos = linea.split(",")
    
    Row(campos(16), campos(17), leerCampo(campos(18)))
  })
  
  val vuelosDF = sparkSession.createDataFrame(vuelosRDD, schemaVuelo)
  
  
  vuelosDF.select("AeropuertoOrigen", "DistanciaMillas")
    .groupBy("AeropuertoOrigen")
    .avg("DistanciaMillas")
    .orderBy("AeropuertoOrigen")
    .show()
  
  sparkSession.stop()
}
