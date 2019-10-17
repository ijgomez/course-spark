package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

import org.course.spark.exercise.helper.StringHelper._

object FileFormat extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Spark Core Exercise v8.0 - File Format")

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
  
  def generarFicheroDatos() = {
    val ficheroRDD = sparkCtx.textFile("./data/1987.csv")
    
    ficheroRDD.filter(
        // (linea) => !linea.startsWith("Year")
        !_.startsWith("Year")
    ).map(
        (linea) => {
          val campos = linea.split(",")
          
          (
              campos(16), 
              DatosVueloSimple(
                  "%04d-%02d-%02d".format(campos(0).toInt, campos(1).toInt, campos(2).toInt),
                  campos(16),
                  campos(17),
                  leerCampo(campos(18)),
                  leerCampo(campos(15)),
                  leerCampo(campos(14))
              )
          )
        }
    ).saveAsObjectFile("./object/vuelos")
 
  }
  
  //************************************************************************
  
  generarFicheroDatos()
  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
