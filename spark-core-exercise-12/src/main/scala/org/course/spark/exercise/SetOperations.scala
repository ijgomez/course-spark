package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object SetOperations extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Spark Core Exercise v12.0 - Set Operations")

  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  //************************************************************************
  
   val datos = Array(
    "1987,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA",
    "1987,10,15,4,729,730,903,849,PS,1451,NA,94,79,NA,14,-1,BOS,SFO,449,NA,NA,0,NA,0,NA,NA,NA,NA,NA",
    "1987,10,17,6,741,730,918,849,PS,1451,NA,97,79,NA,29,11,JFK,SFO,445,NA,NA,0,NA,0,NA,NA,NA,NA,NA",
    "1987,10,18,7,729,730,847,849,PS,1451,NA,78,79,NA,-2,-1,LAX,SFO,444,NA,NA,0,NA,0,NA,NA,NA,NA,NA",
    "1987,10,19,1,749,730,922,849,PS,1451,NA,93,79,NA,33,19,JFK,SFO,443,NA,NA,0,NA,0,NA,NA,NA,NA,NA",
    "1987,10,21,3,728,730,848,849,PS,1451,NA,80,79,NA,-1,-2,LAX,SFO,442,NA,NA,0,NA,0,NA,NA,NA,NA,NA"
  )
  
  val datosRDD = sparkCtx.parallelize(datos).map((linea) => {
    val campos = linea.split(",")
    
    (campos(16), campos(18))
  })

  val vuelosDesdeLaxRDD = datosRDD.filter(_._1 == "LAX")
  
  val vuelosDesdeJfkRDD = datosRDD.filter(_._1 == "JFK")
  
  val interseccionRDD = datosRDD.intersection(vuelosDesdeLaxRDD)
  
  println("Interseccion")
  for (d <- interseccionRDD.collect()) {
    println(d)
  }
  
  val unionRDD = vuelosDesdeJfkRDD.union(vuelosDesdeLaxRDD)
  println("Union")
  for (d <- unionRDD.collect()) {
    println(d)
  }
  
  val diferenciaRDD = datosRDD.subtract(vuelosDesdeLaxRDD);
  println("Diferencia")
  for (d <- diferenciaRDD.collect()) {
    println(d)
  }

  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
