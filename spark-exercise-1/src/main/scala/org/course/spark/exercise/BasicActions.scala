package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object BasicActions extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Basic Action Task v1.0")
  
  // Indicate where the spark job is to be executed (It can be indicated by the spark-summit command)
  // sparkConf.setMaster("local[*]")
  
  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  
  
  
  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
