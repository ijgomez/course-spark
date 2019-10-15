
# Curso de Spark para Desarrolladores.


## Software necesario
- Java JDK 1.8
- Apache Hadoop 2.7.7
- Apache Spark 2.4.4

## Instalacion del entorno (Windows)

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

5. Configurar las variables de entorno en el PATH del sistema:
	* `PATH=%JAVA_HOME%\bin;%HADOOP_HOME%\bin;%SPARK_HOME%\bin;%PATH%`

	
## Instalacion del entorno (Linux)
<PENDIENTE>

## Workspace
En el directorio workspace se encuentran los scripts para ejecutar los diferentes ejercicios del curso.

## Ejercicios
Descripcion de los ejecicios de que se compone el curso:
- Ejecicio 1: Usando el contexto de spark
- Ejecicio 2: Leyendo un fichero plano de texto
- Ejecicio 2: Leyendo un fichero plano de texto comprimido
	
## URLs:
https://dev.to/awwsmm/installing-and-running-hadoop-and-spark-on-windows-33kc	

https://medium.com/big-data-engineering/how-to-install-apache-spark-2-x-in-your-pc-e2047246ffc3

https://github.com/cdarlint/winutils

https://dzone.com/articles/working-on-apache-spark-on-windows