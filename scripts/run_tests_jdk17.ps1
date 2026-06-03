$cwd = Convert-Path .
$jdkZip = Join-Path $cwd '..\openjdk17-temp.zip'
$jdkUrl = 'https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.19+10/OpenJDK17U-jdk_x64_windows_hotspot_17.0.19_10.zip'
Write-Output "Downloading JDK 17 from $jdkUrl to $jdkZip..."
Invoke-WebRequest -Uri $jdkUrl -OutFile $jdkZip -UseBasicParsing
$jdkDest = Join-Path $cwd '..\openjdk17'
Write-Output "Extracting JDK to $jdkDest..."
Expand-Archive -Path $jdkZip -DestinationPath $jdkDest -Force
$jdkDir = Get-ChildItem -Path $jdkDest -Directory | Select-Object -First 1
$javaHome = $jdkDir.FullName
Write-Output "JAVA_HOME = $javaHome"
$env:JAVA_HOME = $javaHome
$env:PATH = Join-Path $javaHome 'bin' + ';' + $env:PATH
# Download gradle distribution and run tests
$zip = Join-Path $cwd '..\gradle-8.6-temp.zip'
Write-Output "Downloading Gradle..."
Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.6-bin.zip' -OutFile $zip -UseBasicParsing
$dest = Join-Path $cwd '..\gradle-8.6-temp'
Write-Output "Extracting Gradle to $dest..."
Expand-Archive -Path $zip -DestinationPath $dest -Force
$gradDir = Get-ChildItem -Path $dest -Directory | Select-Object -First 1
$gradBin = Join-Path $gradDir.FullName 'bin\gradle.bat'
Write-Output "Using Gradle at $gradBin"
if(Test-Path $gradBin){ & $gradBin 'test' '--no-daemon' '--info' '--stacktrace' } else { Write-Error 'gradle bin not found' }
Write-Output 'Cleaning up gradle temp files...'
Remove-Item -Path $zip -Force
Remove-Item -Path $dest -Recurse -Force
Write-Output 'Removing jdk zip (keeping extracted jdk)'
Remove-Item -Path $jdkZip -Force
Write-Output 'Done'