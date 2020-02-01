package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object BasicActions extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Spark Core Exercise v4.0 - Basic Actions")

  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  // Read a compress file into Resilient Distributed Dataset (RDD).
  val fileRDD = sparkCtx.textFile("./data/1987.csv.bz2")

  // Mapper.
  // Recibe datos de un vuelo (como linea) y devuelve la distancia.
  def obtenerDistancia(cadena: String) : Long = {
    
    val campos = cadena.split(",")
    
    try {
        campos(18).toLong
    } catch {
      case _ => 0
    }
    
  }
  
  val distanciasRDD = fileRDD.map(obtenerDistancia)
  
  // Reductor.
  // Recibe distancias y las suma. 
  def sumar(a : Long, b: Long) : Long = {
    a + b
  }
  
  val distanciaTotalFunc = distanciasRDD.reduce(sumar)
  
  println("#" * 50)
  println("Distancia Total (func): " + distanciaTotalFunc);
  println()

  // Metodo de convenencia (para sumar).
  val distanciaTotalSum = distanciasRDD.sum()
  
  println("#" * 50)
  println("Distancia Total (sum): " + distanciaTotalSum);
  println()
  
  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
