package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object JoinOperations extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Spark Core Exercise v13.0 - Join Operations")

  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  //************************************************************************
  
   val peliculas = List(
      raw"tt0000001;short;Carmencita;Carmencita;0;1894;\N      1       Documentary,Short",
      raw"tt0000002;short;Le clown et ses chiens;Le clown et ses chiens;0;1892;    \N    5Animation,Short",
      raw"tt0000003;short;Pauvre Pierrot;Pauvre Pi	errot;0;1892;    \N      4       Animation,Comedy,Romance",
      raw"tt0000004;short;Pauvre Pierrot;Pauvre Pierrot;0;1892;    \N      4       Animation,Comedy,Romance"      
  )

  val autores = List(
      raw"tt0000001;nm0005690;\N",  
      raw"tt0000002;nm0721526;\N",
      raw"tt0000003;nm0000003;\N"      
  )
  
  val datosPersonales = List(
      raw"nm0005690;Fred Astaire;1899;1987    soundtrack,actor,miscellaneous  tt0043044,tt0053137,tt0072308,tt0050419",
      raw"nm0721526;Lauren Bacall;1924;2014    actress,soundtrack      tt0038355,tt0117057,tt0037382,tt0071877",
      raw"nm0000003;Brigitte Bardot;1934;\N      actress,soundtrack,producer     tt0054452,tt0049189,tt0059956,tt0057345"      
  )

  val peliculasRDD = sparkCtx.parallelize(peliculas).map((linea) => {
    val campos = linea.split(";")
    
    (campos(0), (campos(2), campos(5)))
  })
  
  val autoresRDD = sparkCtx.parallelize(autores).map((linea) => {
    val campos = linea.split(";")
    
    (campos(0), campos(1))
  })
  
  val datosPersonalesRDD = sparkCtx.parallelize(datosPersonales).map((linea) => {
    val campos = linea.split(";")
    
    (campos(0), (campos(1), campos(2)))
  })
  
  
  
  
  val peliculasAutoresRDD = peliculasRDD.join(autoresRDD)
  
  
  val datosCompletosRDD = peliculasAutoresRDD.map{
    case (peliculaId, ((titulo, year), autorId)) => {
      (autorId, (peliculaId, titulo, year))
    }
    
  }.join(datosPersonalesRDD)
  
  
  println("Joins")
  
  
  for ( (autorId, ((peliculaId, titulo, year), (nombre, roles))) <- datosCompletosRDD.collect()) {
    println(s"$peliculaId $titulo $year $nombre $roles")
  }

  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
