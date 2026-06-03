$cwd = Convert-Path .
$zip = Join-Path $cwd '..\gradle-8.6-bin.tmp.zip'
Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.6-bin.zip' -OutFile $zip -UseBasicParsing
$dest = Join-Path $cwd '..\gradle-8.6-tmp'
Expand-Archive -Path $zip -DestinationPath $dest -Force
$src = Get-ChildItem -Path $dest -Recurse -Filter 'gradle-wrapper*.jar' | Select-Object -First 1 -ExpandProperty FullName
if($src){ New-Item -ItemType Directory -Path 'gradle\wrapper' -Force | Out-Null; Copy-Item -Path $src -Destination 'gradle\wrapper\gradle-wrapper.jar' -Force }
Remove-Item -Path $zip -Force
Remove-Item -Path $dest -Recurse -Force
if(Test-Path 'gradle\wrapper\gradle-wrapper.jar'){ Write-Output 'OK' } else { Write-Output 'MISSING' }