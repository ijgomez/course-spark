package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object BasicActions extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Basic Action Exercise v3.0")

  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  // Read a compress file into Resilient Distributed Dataset (RDD).
  val fileRDD = sparkCtx.textFile("./data/1987.csv.bz2")

  // Read first 10 elements of RDD.
  val first10 = fileRDD.take(10)
    
  println("#" * 50)
  for (line <- first10) {
    println(line)
  }
  println()
  
  // Count elements of RDD.
  val numberOfFlights = fileRDD.count();
  println("#" * 50)
  println("Numbers of fligths: " + numberOfFlights);  
  println()
  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
