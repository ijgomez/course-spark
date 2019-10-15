package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object BasicActions extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Basic Action Exercise v1.0")
  
  // Indicate where the spark job is to be executed (It can be indicated by the spark-summit command)
  // sparkConf.setMaster("local[*]")
  
  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  // leer fichero txt por linea
  // sparkCtx.textFile(path, minPartitions)
  
  // leer ficheros completos.
  //sparkCtx.wholeTextFiles(path, minPartitions)
  
  // leer ficheros binarios.
  // sparkCtx.binaryFiles(path, minPartitions)
  
  // formato Sequence de haddopp
  //sparkCtx.sequenceFile(path, keyClass, valueClass)
  
  // formatos legibles desde hadoop
  //sparkCtx.hadoopFile(path)
  
  
  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
