package org.course.spark.exercise

import org.apache.spark.sql.SparkSession
import java.util.Properties
import org.apache.spark.sql.SaveMode

object JdbcOperations extends App {
  
  val sparkSession = SparkSession.builder().getOrCreate()
  
  val jdbcConfig = new Properties()
  jdbcConfig.put("user", "cursos")
  jdbcConfig.put("password", "cursos")
  
  // para pareliliza la carga de datos
  jdbcConfig.put("numPartitions", "4")
  jdbcConfig.put("partitionColumn", "customer_id")
  jdbcConfig.put("lowerBound", "1")
  jdbcConfig.put("upperBound", "" + Long.MaxValue)
  
  val clientesDF = sparkSession.read.jdbc(
      "jdbc:postgresql://localhost:5432/cursos",
      """
        (select customer_id, first_name, last_name, 
              address, postal_code
                from customer
                  join store
                    on customer.store_id = store.store_id
                  join address
                    on customer.address_id = address.address_id) consulta
      """,  
      
      jdbcConfig)
  
      clientesDF.printSchema()
      
  clientesDF.select("first_name", "last_name").show()   
      
  clientesDF.write.mode(SaveMode.Overwrite).jdbc("jdbc:postgresql://localhost:5432/cursos", "informe1", jdbcConfig)
  
  
   sparkSession.stop()
}
