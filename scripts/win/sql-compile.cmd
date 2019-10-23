@ECHO OFF
SETLOCAL

if exist ..\spark-sql-exercise-%~1 (
	cd ..\spark-sql-exercise-%~1
	
	call mvn clean install

	cd ..\workspace
)
ENDLOCAL
exit /B %ERRORLEVEL%