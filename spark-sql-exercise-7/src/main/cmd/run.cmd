
SET LIBRARY=%M2_REPO%\org\postgresql\postgresql\42.2.8\postgresql-42.2.8.jar

spark-submit --master local[*] --class org.course.spark.exercise.JdbcOperations --jars %LIBRARY% --driver-class-path %LIBRARY%  ..\spark-sql-exercise-7\target\spark-sql-exercise-07.jar