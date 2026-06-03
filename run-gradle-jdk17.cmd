@echo off
set "JAVA_HOME=C:\Users\juanc\Downloads\obras-backend\obras-backend\openjdk17\jdk-17.0.19+10"
set "PATH=%JAVA_HOME%\bin;%PATH%"
cd /d %~dp0
call .\gradle-8.6\bin\gradle.bat clean build -x test --stacktrace
