
# Course: Apache Spark for Developers


## Software needed for the course
- Java JDK 1.8: [Link](https://www.oracle.com/technetwork/java/javase/downloads/java-archive-javase8-2177648.html)
- Apache Hadoop 2.7.7: [Link](https://hadoop.apache.org/)
- Apache Spark 2.4.4: [Link](https://spark.apache.org/)
- Apache Maven 3.6.0: [Link] (https://maven.apache.org/)
- Apache Kafka 2.3.0: [Link] (https://kafka.apache.org/)
- PostgreSQL: [Link] (https://www.postgresql.org/)

## Environment installation

### Windows

1. Java JDK 1.8
	* Descargar e Instalar. NOTA: Instalar en una ruta sin espacios.
	* Definir variable de entorno `%JAVA_HOME%`
	
2. hadoop-2.7.7.tar.gz
	* Descargar y Descomprimir.
	* Definir variable de entorno `%HADOOP_HOME%`
	* Para sistemas windows, hay que descargar el ejecutable [winutils.exe](https://github.com/steveloughran/winutils), y ubicarlo en el %HADOOP_HOME%\bin

3. spark-2.4.4-bin-hadoop2.7.tgz
	* Descargar y Descomprimir.
	* Definir variable de entorno `%SPARK_HOME%`
	
4. Apache Maven 3.6	(Opcional - Para compilar los ejemplos y ejercicios del curso)
	* Descargar y Descomprimir.
	* Definir variable de entorno `%MAVEN_HOME%`

5. Configurar las variables de entorno en el PATH del sistema: `PATH=%JAVA_HOME%\bin;%HADOOP_HOME%\bin;%SPARK_HOME%\bin;%PATH%`

### Linux/Unix

<PENDIENTE>

## Workspace
Directory where the scripts are found to execute the different exercises of the course.

## Exercises / Examples
Description of the executions of which the course is composed:

### Spark Core:
- Ejecicio 1: Usando el contexto de spark
- Ejecicio 2: Leyendo un fichero plano de texto
- Ejecicio 2: Leyendo un fichero plano de texto comprimido

### Spark SQL:

### Spark Streaming:

### Cluster: