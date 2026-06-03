# Detener procesos java/gradle que puedan bloquear archivos
Get-Process java,javaw,gradle -ErrorAction SilentlyContinue | Select-Object Id,ProcessName | Format-Table -AutoSize
Stop-Process -Name gradle -Force -ErrorAction SilentlyContinue
Stop-Process -Name java -Force -ErrorAction SilentlyContinue
Stop-Process -Name javaw -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 1
Get-Process java,javaw,gradle -ErrorAction SilentlyContinue | Select-Object Id,ProcessName | Format-Table -AutoSize

# Intentar detectar JDK17 extraído en ..\openjdk17
Set-Location -Path (Join-Path $PSScriptRoot '..')
$openjdkRoot = Join-Path (Get-Location) 'openjdk17'
$javaHome = $null
if(Test-Path $openjdkRoot){
    $jdkDir = Get-ChildItem -Path $openjdkRoot -Directory | Select-Object -First 1
    if($jdkDir){ $javaHome = $jdkDir.FullName }
}
Write-Output "Detected JAVA_HOME candidate: $javaHome"

# Descargar Gradle y extraer gradle-wrapper.jar
$zip = Join-Path (Get-Location).Parent 'gradle-8.6-bin.tmp.zip'
$tmpDest = Join-Path (Get-Location).Parent 'gradle-8.6-tmp'
Write-Output "Downloading Gradle to $zip..."
Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.6-bin.zip' -OutFile $zip -UseBasicParsing
Write-Output "Extracting Gradle to $tmpDest..."
Expand-Archive -Path $zip -DestinationPath $tmpDest -Force
$src = Get-ChildItem -Path $tmpDest -Recurse -Filter 'gradle-wrapper*.jar' | Select-Object -First 1 -ExpandProperty FullName
if($src){
    New-Item -ItemType Directory -Path 'gradle\wrapper' -Force | Out-Null
    Copy-Item -Path $src -Destination 'gradle\wrapper\gradle-wrapper.jar' -Force
    Write-Output 'gradle-wrapper.jar copied'
} else {
    Write-Output 'gradle-wrapper.jar not found in downloaded distribution'
}

# Limpiar temporales
Remove-Item -Path $zip -Force
Remove-Item -Path $tmpDest -Recurse -Force

# Establecer JAVA_HOME si detectado
if($javaHome){
    $env:JAVA_HOME = $javaHome
    $env:PATH = Join-Path $javaHome 'bin' + ';' + $env:PATH
    Write-Output ("Using JAVA_HOME=$env:JAVA_HOME")
} else {
    Write-Output 'No local JDK17 found under ..\openjdk17 - running with system Java'
}

# Ejecutar tests con el wrapper
Write-Output 'Running: .\gradlew.bat test --no-daemon --info --stacktrace'
& .\gradlew.bat test --no-daemon --info --stacktrace

Write-Output 'Finished'
