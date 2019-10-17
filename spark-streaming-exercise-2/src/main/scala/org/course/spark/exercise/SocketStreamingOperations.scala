package org.course.spark.exercise

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel

object SocketStreamingOperations extends App {
    
  val sparkConf = new SparkConf()
  
  val sparkStream = new StreamingContext(sparkConf, Seconds(2))
  
  
  val lectorDeDatos = sparkStream.socketTextStream("localhost", 5550, StorageLevel.MEMORY_ONLY)
  
  val datosComprasDS = lectorDeDatos.map((linea) => {
    val campos = linea.split(",")
    
    (campos(0), campos(1).toInt)
    
  }).reduceByKeyAndWindow(
      (a : Int, b : Int) => {a + b},
      Seconds(30),
      Seconds(30)
   )
  
   datosComprasDS.print(1000)
   
   // Activar el procesado
   sparkStream.start()
  
   // Para retener la ejecucion del proceso
  sparkStream.awaitTermination()
}
