package org.course.spark.exercise

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

import org.apache.kafka.common.serialization.StringDeserializer

object KafkaStreamingOperations extends App {
    
  val sparkConf = new SparkConf()
  
  val sparkStream = new StreamingContext(sparkConf, Seconds(2))
  
  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "use_a_separate_group_id_for_each_stream",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val topics = Array("prueba")
  
  val streamKafka = KafkaUtils.createDirectStream[String, String](
      sparkStream, 
      PreferConsistent, 
      Subscribe[String, String](topics, kafkaParams)
   )
  
   streamKafka.map((datos) => {
    val campos = datos.value().split(",")

    (campos(0), campos(1).toInt)
  }).reduceByKeyAndWindow(
      (a : Int, b : Int) => {a + b},
      Seconds(30),
      Seconds(30)
   )
   .print(1000)
   
   // Activar el procesado
   sparkStream.start()
  
   // Para retener la ejecucion del proceso
  sparkStream.awaitTermination()
}
