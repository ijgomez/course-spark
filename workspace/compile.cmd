@ECHO OFF
SETLOCAL

if exist ..\spark-exercise-%~1 (
	cd ..\spark-exercise-%~1
	
	call mvn clean install

	cd ..\workspace
)
ENDLOCAL
exit /B %ERRORLEVEL%