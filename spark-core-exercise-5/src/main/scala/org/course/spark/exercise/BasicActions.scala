package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object BasicActions extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Spark Core Exercise v5.0 - Basic Actions")

  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  // Read a compress file into Resilient Distributed Dataset (RDD).
  val fileRDD = sparkCtx.textFile("./data/1987.csv.bz2")

  // Duplas
  def generarTuplaOrigenYMetricas(cadena : String) : (String, (Long, Long, Long)) = {
    
    val campos = cadena.split(",")
    
    try {
      (campos(16),(
         campos(18).toLong, 
         campos(15).toLong, 
         campos(14).toLong)
      )
    } catch {
      case _ => ("ERROR", (0, 0, 0))
    }
  }
  
  fileRDD.map(generarTuplaOrigenYMetricas)
    .reduceByKey((tuplaA, tuplaB) => {

        (tuplaA._1 + tuplaB._1, tuplaA._2 + tuplaB._2, tuplaA._3 + tuplaB._3)

      })
    .sortByKey()
    .saveAsTextFile("./reports/" + System.currentTimeMillis())
  
  // Configurar las particiones
  fileRDD.map(generarTuplaOrigenYMetricas)
    .reduceByKey((tuplaA, tuplaB) => {

        (tuplaA._1 + tuplaB._1, tuplaA._2 + tuplaB._2, tuplaA._3 + tuplaB._3)

      })
    .sortByKey()
    .map(

        (tuplaDatos) => {
          val (origen, (distanciaTotal, retrasoSalida, retrasoLlegada)) = tuplaDatos
          
          s"$origen;$distanciaTotal;$retrasoSalida;$retrasoLlegada"
        } 
           
      )
    .coalesce(1) //Se disminuye a una particion. CUIDADO!!
    .saveAsTextFile("./reports/" + System.currentTimeMillis())
  
  
  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
