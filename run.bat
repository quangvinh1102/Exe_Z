@echo off
cd /d "%~dp0"
"%~dp0jdk-21\jdk-21.0.7+6\bin\java.exe" -server -Dfile.encoding=UTF-8 -Xms2G -Xmx2G -jar "%~dp0target\Nso-jar-with-dependencies.jar"
pause
