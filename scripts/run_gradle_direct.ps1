# Stop java/gradle processes
Get-Process java,javaw,gradle -ErrorAction SilentlyContinue | Select-Object Id,ProcessName | Format-Table -AutoSize
Stop-Process -Name gradle -Force -ErrorAction SilentlyContinue
Stop-Process -Name java -Force -ErrorAction SilentlyContinue
Stop-Process -Name javaw -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 2
Get-Process java,javaw,gradle -ErrorAction SilentlyContinue | Select-Object Id,ProcessName | Format-Table -AutoSize

# Detect extracted JDK17 under parent openjdk17
Set-Location -Path (Join-Path $PSScriptRoot '..')
$openjdkRoot = Join-Path (Get-Location) 'openjdk17'
$javaHome = $null
if(Test-Path $openjdkRoot){
    $jdkDir = Get-ChildItem -Path $openjdkRoot -Directory | Select-Object -First 1
    if($jdkDir){ $javaHome = $jdkDir.FullName }
}
Write-Output "Detected JAVA_HOME candidate: $javaHome"

# Download and extract Gradle
$zip = Join-Path (Get-Location) '..\gradle-8.6-bin.zip'
$dest = Join-Path (Get-Location) '..\gradle-8.6-temp'
if(Test-Path $zip){ Remove-Item -Path $zip -Force }
Write-Output "Downloading Gradle to $zip..."
Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.6-bin.zip' -OutFile $zip -UseBasicParsing
Write-Output "Extracting Gradle to $dest..."
if(Test-Path $dest){ Remove-Item -Path $dest -Recurse -Force }
Expand-Archive -Path $zip -DestinationPath $dest -Force
$gradDir = Get-ChildItem -Path $dest -Directory | Select-Object -First 1
$gradBin = Join-Path $gradDir.FullName 'bin\gradle.bat'
Write-Output "Gradle bin: $gradBin"

# Set JAVA_HOME if available
if($javaHome){
    $env:JAVA_HOME = $javaHome
    $env:PATH = Join-Path $javaHome 'bin' + ';' + $env:PATH
    Write-Output ("Using JAVA_HOME=$env:JAVA_HOME")
} else {
    Write-Output 'No local JDK17 found under ..\openjdk17 - running with system Java'
}

# Run gradle directly
if(Test-Path $gradBin){
    & $gradBin 'test' '--no-daemon' '--info' '--stacktrace'
} else {
    Write-Error 'gradle bin not found'
}

# Cleanup gradle temp zip and folder
Remove-Item -Path $zip -Force
Remove-Item -Path $dest -Recurse -Force
Write-Output 'Done'
