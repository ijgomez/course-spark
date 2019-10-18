	@ECHO OFF
SETLOCAL

call :compile ..\spark-exercise-parent
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-1
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-2
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-3
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-4
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-5
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-6
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-7
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-8
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-9
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-10
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-11
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-12
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

call :compile ..\spark-core-exercise-13
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