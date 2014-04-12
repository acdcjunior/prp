cls

call mvn test install
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" exit /b

call mvn -f prp-mvc/pom.xml clean tomcat7:run
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" exit /b