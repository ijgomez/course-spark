@ECHO OFF
SETLOCAL

if exist ..\spark-core-exercise-%~1 (
	cd ..\spark-core-exercise-%~1
	
	call mvn clean install

	cd ..\workspace
)
ENDLOCAL
exit /B %ERRORLEVEL%