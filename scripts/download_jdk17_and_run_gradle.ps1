$parent = 'C:\Users\juanc\Downloads\obras-backend'

# Download Temurin JDK 17
$jdkUrl = 'https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.19+10/OpenJDK17U-jdk_x64_windows_hotspot_17.0.19_10.zip'
$jdkZip = Join-Path $parent 'openjdk17.zip'
if(Test-Path $jdkZip){ Remove-Item -Path $jdkZip -Force }
Write-Output "Downloading JDK 17 from $jdkUrl to $jdkZip..."
Invoke-WebRequest -Uri $jdkUrl -OutFile $jdkZip -UseBasicParsing

# Extract
$dest = Join-Path $parent 'openjdk17'
if(Test-Path $dest){ Remove-Item -Path $dest -Recurse -Force }
Write-Output "Extracting $jdkZip to $dest..."
Expand-Archive -Path $jdkZip -DestinationPath $dest -Force
$jdkDir = Get-ChildItem -Path $dest -Directory | Select-Object -First 1
if(-not $jdkDir){ Write-Error 'Unable to find JDK directory after extract'; exit 1 }
$javaHome = $jdkDir.FullName
Write-Output "Extracted JDK to $javaHome"

# Set JAVA_HOME for this process
$env:JAVA_HOME = $javaHome
$env:PATH = "$($javaHome)\bin;" + $env:PATH
Write-Output "Using JAVA_HOME=$env:JAVA_HOME"

# Force Gradle to use this JDK
$env:GRADLE_JAVA_HOME = $javaHome
Write-Output "Set GRADLE_JAVA_HOME=$env:GRADLE_JAVA_HOME"

# Download Gradle
$gUrl = 'https://services.gradle.org/distributions/gradle-8.6-bin.zip'
$gZip = Join-Path $parent 'gradle-8.6-bin.zip'
if(Test-Path $gZip){ Remove-Item -Path $gZip -Force }
Write-Output "Downloading Gradle to $gZip..."
Invoke-WebRequest -Uri $gUrl -OutFile $gZip -UseBasicParsing
$gDest = Join-Path $parent 'gradle-8.6-temp'
if(Test-Path $gDest){ Remove-Item -Path $gDest -Recurse -Force }
Write-Output "Extracting Gradle to $gDest..."
Expand-Archive -Path $gZip -DestinationPath $gDest -Force
$gradDir = Get-ChildItem -Path $gDest -Directory | Select-Object -First 1
$gradBin = Join-Path $gradDir.FullName 'bin\gradle.bat'
Write-Output "Gradle bin: $gradBin"

# Stop any running Gradle daemons (use the downloaded gradle)
if(Test-Path $gradBin){
    & $gradBin '--stop'
    Start-Sleep -Seconds 1
    Write-Output 'Gradle daemons stop requested'
    & $gradBin 'test' '--no-daemon' '--info' '--stacktrace'
} else {
    Write-Error 'gradle bin not found'
}

# Cleanup zip files (keep extracted jdk)
if(Test-Path $gZip){ Remove-Item -Path $gZip -Force }
if(Test-Path $jdkZip){ Remove-Item -Path $jdkZip -Force }
Write-Output 'Done'
