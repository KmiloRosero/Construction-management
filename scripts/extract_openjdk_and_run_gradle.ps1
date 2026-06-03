$parent = 'C:\Users\juanc\Downloads\obras-backend'
$zip = Join-Path $parent 'openjdk17-temp.zip'
if(Test-Path $zip){
    Write-Output "Extracting $zip to $parent\openjdk17..."
    $dest = Join-Path $parent 'openjdk17'
    if(Test-Path $dest){ Remove-Item -Path $dest -Recurse -Force }
    Expand-Archive -Path $zip -DestinationPath $dest -Force
    $jdkDir = Get-ChildItem -Path $dest -Directory | Select-Object -First 1
    if($jdkDir){
        $javaHome = $jdkDir.FullName
        Write-Output "Detected JDK dir: $javaHome"
        $env:JAVA_HOME = $javaHome
        $env:PATH = "$($javaHome)\bin;" + $env:PATH
    }
} else {
    Write-Output "openjdk17-temp.zip not found at $zip"
}
Write-Output "JAVA_HOME=$env:JAVA_HOME"

# Download Gradle
$gZip = Join-Path $parent 'gradle-8.6-bin.zip'
$gDest = Join-Path $parent 'gradle-8.6-temp'
if(Test-Path $gZip){ Remove-Item -Path $gZip -Force }
Write-Output "Downloading Gradle to $gZip..."
Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.6-bin.zip' -OutFile $gZip -UseBasicParsing
if(Test-Path $gDest){ Remove-Item -Path $gDest -Recurse -Force }
Write-Output "Extracting Gradle to $gDest..."
Expand-Archive -Path $gZip -DestinationPath $gDest -Force
$gradDir = Get-ChildItem -Path $gDest -Directory | Select-Object -First 1
$gradBin = Join-Path $gradDir.FullName 'bin\gradle.bat'
Write-Output "Gradle bin: $gradBin"

if(Test-Path $gradBin){
    & $gradBin 'test' '--no-daemon' '--info' '--stacktrace'
} else {
    Write-Error 'gradle bin not found'
}

# cleanup
Remove-Item -Path $gZip -Force
Remove-Item -Path $gDest -Recurse -Force
Write-Output 'Done'
