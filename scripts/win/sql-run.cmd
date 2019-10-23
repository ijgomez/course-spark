@ECHO OFF
SETLOCAL

echo 

if exist ..\spark-sql-exercise-%~1 (

	call ..\spark-sql-exercise-%~1\src\main\cmd\run.cmd

)

ENDLOCAL
exit /B %ERRORLEVEL%
