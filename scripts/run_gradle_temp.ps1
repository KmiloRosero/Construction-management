$cwd = Convert-Path .
$zip = Join-Path $cwd '..\gradle-8.6-full.tmp.zip'
Write-Output "Downloading gradle to $zip..."
Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.6-bin.zip' -OutFile $zip -UseBasicParsing
$dest = Join-Path $cwd '..\gradle-8.6-full'
Write-Output "Extracting to $dest..."
Expand-Archive -Path $zip -DestinationPath $dest -Force
$gradDir = Get-ChildItem -Path $dest -Directory | Select-Object -First 1
$gradBin = Join-Path $gradDir.FullName 'bin\gradle.bat'
Write-Output "Gradle bin: $gradBin"
if(Test-Path $gradBin){ & $gradBin 'test' '--no-daemon' '--info' } else { Write-Error 'gradle bin not found' }
Write-Output 'Cleaning up temp files...'
Remove-Item -Path $zip -Force
Remove-Item -Path $dest -Recurse -Force
Write-Output 'Done'