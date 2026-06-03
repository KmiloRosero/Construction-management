@echo off
set DIRNAME=%~dp0
set CLASSPATH=%DIRNAME%\gradle\wrapper\gradle-wrapper.jar
if not exist "%CLASSPATH%" (
  echo Missing %CLASSPATH%. Run 'gradle wrapper' or download the jar.
  exit /b 1
)
java -jar "%CLASSPATH%" %*
