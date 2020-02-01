package org.course.spark.exercise.helper

object StringHelper {
  
  def leerCampo(valor: String) : Long = {
    try {
      valor.toLong
    } catch {
      case _ => 0L
    }
  }
  
}