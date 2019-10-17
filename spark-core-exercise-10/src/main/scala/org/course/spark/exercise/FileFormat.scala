package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.course.spark.exercise.helper.StringHelper._

import org.course.spark.exercise.domain.DatosVuelo

object FileFormat extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Spark Core Exercise v10.0 - File Format")

  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

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
              DatosVuelo(
                "%04d-%02d-%02d".format(leerCampo(campos(0)), leerCampo(campos(1)), leerCampo(campos(2))),
                campos(4),
                campos(5),
                campos(6),
                campos(7),
                campos(8),
                campos(9),
                campos(10),
                leerCampo(campos(11)),
                leerCampo(campos(12)),
                leerCampo(campos(13)),
                leerCampo(campos(14)),
                leerCampo(campos(15)),
                campos(16),
                campos(17),
                leerCampo(campos(18)),
                leerCampo(campos(19)),
                leerCampo(campos(20)),
                campos(21),
                campos(22),
                campos(23),
                leerCampo(campos(24)),
                leerCampo(campos(25)),
                leerCampo(campos(26)),
                leerCampo(campos(27)),
                leerCampo(campos(28))
              )
          )
        }
    ).saveAsSequenceFile("./sequence/vuelos")
 
  }
  
  //************************************************************************
  
  generarFicheroDatos()
  
  //************************************************************************
  
  val datosRDD = sparkCtx.sequenceFile[String, DatosVuelo]("./sequence/vuelos")
  
  val vuelosLargosRDD = datosRDD.filter(_._2.distancia > 600).sample(false, 0.001).foreach {
    case (origen, datos) => {
      println(s"$origen->${datos.destino} - ${datos.distancia}")
    }
  }
  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
