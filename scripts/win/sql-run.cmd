@ECHO OFF
SETLOCAL

echo 

if exist ..\spark-core-exercise-%~1 (

	call ..\spark-core-exercise-%~1\src\main\cmd\run.cmd

)

ENDLOCAL
exit /B %ERRORLEVEL%
