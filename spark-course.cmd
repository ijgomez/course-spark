@echo off

set COURSE_WORKSPACE=.\workspace


@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT" setlocal

if "%OS%" == "Windows_NT" (
  set "DIRNAME=%~dp0%"
) else (
  set DIRNAME=.\
)

if "x%STANDALONE_CONF%" == "x" (
   set "STANDALONE_CONF=%DIRNAME%spark-environment.cmd"
)
if exist "%STANDALONE_CONF%" (
	call "%STANDALONE_CONF%" %*
) else (
   echo Config file not found "%STANDALONE_CONF%"
)
set COURSE_SCRIPTS=%DIRNAME%\scripts\win
set M2_HOME=%MAVEN_HOME%
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%HADOOP_HOME%\bin;%SPARK_HOME%\bin;%COURSE_SCRIPTS%;%PATH%

java -version

cmd /K "cd /d %COURSE_WORKSPACE%"


