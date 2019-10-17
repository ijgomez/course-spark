package org.course.spark.exercise.partitioners

import org.apache.spark.Partitioner

class ParticionadorPorMes(var contador : Int = 12) extends Partitioner {

  def getPartition(key: Any): Int = {
    
    val mes = key.asInstanceOf[Int]
    
   // contador = if (mes > contador) mes else contador
    (mes - 1) % numPartitions
  }

  def numPartitions: Int = {
    contador
  }

}