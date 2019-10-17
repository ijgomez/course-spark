package org.course.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

object BasicActions extends App {

  // Variable to configure the Spark runtime
  val sparkConf = new SparkConf()
  
  // Define spark job name
  sparkConf.setAppName("Basic Action Exercise v7.0")

  // Get the context of spark
  val sparkCtx = new SparkContext(sparkConf)

  // Read a compress file into Resilient Distributed Dataset (RDD).
  val fileRDD = sparkCtx.textFile("./data/1987.csv.bz2")

  // Podemos indicar que se almacene un RDD, si falta memoria.
  fileRDD.persist(StorageLevel.MEMORY_ONLY)
    
  // ficheroRDD.cache() // Hace lo mismo que el anterior
  
  fileRDD.persist(StorageLevel.DISK_ONLY)
  fileRDD.persist() // hace lo mismo que el anterior
  
  fileRDD.persist(StorageLevel.MEMORY_AND_DISK) // Primero almacen en memoria, sino cabe va al disco.
  fileRDD.persist(StorageLevel.MEMORY_AND_DISK_2) // (_2) indica que se Replicarlo en otra maquina (copia en la maquina local y en otra maquina).
  
  fileRDD.unpersist() // Indicamos que no queremos seguir manteniendo el RDD almacenado.
  
  val datosRDD = fileRDD.filter((cadena) => {
        val campos = cadena.split(",")
        
        try {
          campos(12).toLong
          campos(18).toLong
          campos(14).toLong
          campos(15).toLong
          true
        } catch {
          case _ => false;
        }
        
    }).map(
        (cadena) => {
          val campos = cadena.split(",")
          
          (
              (campos(1), campos(16)),
              (campos(12).toLong, campos(18).toLong, campos(14).toLong, campos(15).toLong)
          )
        }
    )
  
    val datosGrupo1RDD = datosRDD.groupByKey()
    
    val informeGrupo1 = datosGrupo1RDD.map((datosVuelo) => {
      
      var datos = (0, 0.0, 0.0, 0.0)
      var avgTV = 0L
      var avgDI = 0L
      var avgRS = 0L
      var avgRL = 0L
      var contador = 1L
      for (dato <- datosVuelo._2) {
        
        avgTV += dato._1
        avgDI += dato._2
        avgRS += dato._3
        avgRL += dato._4
        
        contador+=1;
      }
      
      (datosVuelo._1, (avgTV / contador, avgDI / contador, avgRS / contador, avgRL / contador))
    })
    .sortByKey()
    .take(4)
  
    
    println("Informe Grupo 1")
    println("#"*50)
    for (d <- informeGrupo1) {
      println(d)
    }
    println("#"*50)
    
    // Acumuladores
    //permite implementar algoritmos donde necesitamos contadores
    
    var contador = 1;
    
    var contadorVuelos = sparkCtx.longAccumulator("ContadorVuelos")
    //Ponerlo a cero
    contadorVuelos.reset()
    
    //Incremetal contador
    contadorVuelos.add(1L)
    
    val informeGrupo2 = datosRDD.map((datosVuelo) => {
      (datosVuelo._1, (datosVuelo._2._1, datosVuelo._2._2, datosVuelo._2._3, datosVuelo._2._4, 1))
    }).reduceByKey((d1, d2) => {
      
      (d1._1 + d2._1, d1._2 + d2._2, d1._3 + d2._3, d1._4 + d2._4, d1._5 + d2._5)
      
    }).map((datosVuelo) => {
      (datosVuelo._1, (
          datosVuelo._2._1 / datosVuelo._2._5, 
          datosVuelo._2._2 / datosVuelo._2._5, 
          datosVuelo._2._3 / datosVuelo._2._5, 
          datosVuelo._2._4 / datosVuelo._2._5
       ))
       
    })
    .sortByKey()
    .take(4)
    
    
    println("Informe Grupo 2")
    println("#"*50)
    for (d <- informeGrupo2) {
      println(d)
    }
    println("#"*50)
    
    val informe3 = datosRDD.aggregateByKey(
        (0L, 0L, 0L, 0L, 0L) //Valor inicial - acumulado
     )(
         // reductor a nivel de particion
         (acumulado, datosVuelo) => {
           (
               acumulado._1 + datosVuelo._1,
               acumulado._2 + datosVuelo._2,
               acumulado._3 + datosVuelo._3,
               acumulado._4 + datosVuelo._4,
               acumulado._5 + 1
            )
         }, 
         
         // reductor global (se aplica a los parciales)
         (a, b) => {
           (a._1 + b._1, a._2 + b._2, a._3 + b._3, a._4 + b._4, a._5 + b._5)           
         }
     ).map((datosVuelo) => {
      (datosVuelo._1, (
          datosVuelo._2._1 / datosVuelo._2._5, 
          datosVuelo._2._2 / datosVuelo._2._5, 
          datosVuelo._2._3 / datosVuelo._2._5, 
          datosVuelo._2._4 / datosVuelo._2._5
       ))
       
      })
      .sortByKey()
      .take(4)
    
      
      println("Informe Grupo 3")
    println("#"*50)
    for (d <- informe3) {
      println(d)
    }
    println("#"*50)
  
  // indicate that we stop using the spark runtime. Resource is released.
  sparkCtx.stop()
}
