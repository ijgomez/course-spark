package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

object FileFormat extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Spark Core Exercise v9.0 - File Format")

  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  //************************************************************************
  
  //Tipo de usuario de valores
 case class DatosVueloSimple(
     fecha : String,
     origen : String,
     destino : String,
     distancia : Long,
     retrasoSalida : Long,
     retrasoLlegada : Long
  )
     
  //************************************************************************
  
  val datosRDD = sparkCtx.objectFile[(String, DatosVueloSimple)]("./object/vuelos")
  
  val vuelosLargosRDD = datosRDD.filter(_._2.distancia > 600).sample(false, 0.1).foreach {
    case (origen, datos) => {
      println(s"$origen->${datos.destino} - ${datos.distancia}")
    }
  }
  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
