	@ECHO OFF
SETLOCAL

call :compile ..\spark-exercise-base
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-exercise-1
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-exercise-2
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-exercise-3
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-exercise-4
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-exercise-5
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-exercise-6
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

goto fin

:: a function to write to a log file and write to stdout
:compile

if exist %* (
	cd %*
	
	call mvn clean install

	cd ..\workspace
)
exit /B %ERRORLEVEL%


:fin
ENDLOCAL
exit /B %ERRORLEVEL%