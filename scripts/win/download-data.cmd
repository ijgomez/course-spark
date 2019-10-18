@ECHO OFF
SETLOCAL

echo Downdoad Data for course...

echo Figth Data 1987...
call :download http://stat-computing.org/dataexpo/2009/1987.csv.bz2 data/1987.csv.bz2
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

echo Figth Data 1988...
call :download http://stat-computing.org/dataexpo/2009/1988.csv.bz2 data/1988.csv.bz2
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

echo Figth Data 2007...
call :download http://stat-computing.org/dataexpo/2009/2007.csv.bz2 data/2007.csv.bz2
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

echo Students Data...
call :download https://github.com/ozlerhakan/mongodb-json-files/blob/master/datasets/students.json data/students.json
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

echo Companies Data...
call :download https://github.com/ozlerhakan/mongodb-json-files/blob/master/datasets/companies.json data/companies.json
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

echo NASA Access Log August 1995...
call :download ftp://ita.ee.lbl.gov/traces/NASA_access_log_Aug95.gz data/NASA_access_log_Aug95.gz
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

echo Nasa Api Meteorites...
call :download https://data.nasa.gov/resource/y77d-th95.json data/y77d-th95.json
if not "%ERRORLEVEL%" == "0" ( goto fin ) 

echo Downdoad Data finnished!

goto fin

:: a function to download a file
:download

if not exist %2 (
	
	powershell -command "& { (New-Object Net.WebClient).DownloadFile('%1', '%2') }"
	
)
exit /B %ERRORLEVEL%

:fin
ENDLOCAL
exit /B %ERRORLEVEL%