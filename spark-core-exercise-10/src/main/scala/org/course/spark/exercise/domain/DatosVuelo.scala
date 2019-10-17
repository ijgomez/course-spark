package org.course.spark.exercise.domain

import org.apache.hadoop.io.Writable

case class DatosVuelo(
  var fecha : String = "",
  var horaSalida : String = "",
  var horaSalidaCRS : String = "",
  var horaLlegada : String = "",
  var horaLlegadaCRS : String = "",
  var aeroLinea : String = "",
  var numeroVuelo : String = "",
  var matriculaAvion : String = "",
  var tiempoTranscurrido : Long = 0,
  var tiempoTranscurridoCRS : Long = 0,
  var tiempoDeVuelo : Long = 0,
  var retrasoLlegada : Long = 0,
  var retrasoSalida : Long = 0,
  var origen : String = "",
  var destino : String = "",
  var distancia : Long = 0,
  var tiempoEnPistaLlegada : Long = 0,
  var tiempoEnPistaSalida : Long = 0,
  var cancelado : String = "",
  var codigoCancelacion : String = "",
  var diferido : String = "",
  var retrasoAeroLinea : Long = 0,
  var retrasoMeteo : Long = 0,
  var retrasoNAS : Long = 0,
  var retrasoSeguridad : Long = 0,
  var retrasoLlegadaTarde : Long = 0
) extends Writable {
  
  def this() = {
    this("","","","","","","","", 0,0,0,0,0, "", "", 0,0,0,"","","",0,0,0,0,0)
  }
  
  def readFields(in: java.io.DataInput): Unit = {
    fecha = in.readUTF()
    horaSalida = in.readUTF()
    horaSalidaCRS = in.readUTF()
    horaLlegada = in.readUTF()
    horaLlegadaCRS = in.readUTF()
    aeroLinea = in.readUTF()
    numeroVuelo = in.readUTF()
    matriculaAvion = in.readUTF()
    tiempoTranscurrido = in.readLong()
    tiempoTranscurridoCRS = in.readLong()
    tiempoDeVuelo = in.readLong()
    retrasoLlegada = in.readLong()
    retrasoSalida = in.readLong()
    origen = in.readUTF()
    destino = in.readUTF()
    distancia = in.readLong()
    tiempoEnPistaLlegada = in.readLong()
    tiempoEnPistaSalida = in.readLong()
    cancelado = in.readUTF()
    codigoCancelacion = in.readUTF()
    diferido = in.readUTF()
    retrasoAeroLinea = in.readLong()
    retrasoMeteo = in.readLong()
    retrasoNAS = in.readLong()
    retrasoSeguridad = in.readLong()
    retrasoLlegadaTarde = in.readLong()
    
   
  }
  
  def write(out: java.io.DataOutput): Unit = {
    out.writeUTF(fecha)
    out.writeUTF(horaSalida)
    out.writeUTF(horaSalidaCRS)
    out.writeUTF(horaLlegada)
    out.writeUTF(horaLlegadaCRS)
    out.writeUTF(aeroLinea)
    out.writeUTF(numeroVuelo)
    out.writeUTF(matriculaAvion)
    out.writeLong(tiempoTranscurrido)
    out.writeLong(tiempoTranscurridoCRS)
    out.writeLong(tiempoDeVuelo)
    out.writeLong(retrasoLlegada)
    out.writeLong(retrasoSalida)
    out.writeUTF(origen)
    out.writeUTF(destino)
    out.writeLong(distancia)
    out.writeLong(tiempoEnPistaLlegada)
    out.writeLong(tiempoEnPistaSalida)
    out.writeUTF(cancelado)
    out.writeUTF(codigoCancelacion)
    out.writeUTF(diferido)
    out.writeLong(retrasoAeroLinea)
    out.writeLong(retrasoMeteo)
    out.writeLong(retrasoNAS)
    out.writeLong(retrasoSeguridad)
    out.writeLong(retrasoLlegadaTarde)
    
  }
  
}