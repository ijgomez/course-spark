package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.course.spark.exercise.partitioners.ParticionadorPorMes

object AuxiliarOperations extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Spark Core Exercise v11.0 - Auxiliar Operations")

  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  //************************************************************************
  
  val ficheroRDD = sparkCtx.textFile("./data/1987.csv").filter(!_.startsWith("Year")).map((linea) => {
    val campos = linea.split(",")
    
    (campos(1).toInt, (campos(16), campos(17)))
  })
  
  // Consultar el numero de particiones de un RDD.
  println("Numero de particiones: " + ficheroRDD.partitions.length)
  
  //Cambiar numero de particiones.
  
  // Intentar no hacer shuffle. Puede provocar particiones descompensadas
   val ficheroCoalesceRDD = ficheroRDD.coalesce(2) //disminuir numero de particiones
  
  println("Numero de particiones despues de coalesce: " + ficheroCoalesceRDD.partitions.length) 
   
  //Provoca shuffle
    val ficheroReparticionRDD = ficheroRDD.repartition(2) 
  
  println("Numero de particiones despues de reparticion: " + ficheroReparticionRDD.partitions.length) 
  
  //************************************************************************
  
  val particionador = new ParticionadorPorMes();
  
  
  val ficheroParticionadoMesRDD = ficheroRDD.partitionBy(particionador)
   
  val parts = ficheroParticionadoMesRDD.glom().collect()
  
  println(s"Dimension matriz parts: ${parts.length}")
  
  var c = 0
  for (part <- parts) {
    println(s"Part $c con dimension: ${part.length}")
    c += 1
  }
  
//  println("partitionBy: " + ficheroParticionadoMesRDD.partitions.length)
//
//  
//  val ficheroReparticionAndShortRDD = ficheroRDD.repartitionAndSortWithinPartitions(particionador)
//   
//  println("repartitionAndSortWithinPartitions: " + ficheroReparticionAndShortRDD.partitions.length)
  

  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
