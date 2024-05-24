# verify vulnerable code project
Set-Location vulnerable-code
.\mvnw verify -B
Set-Location ..

# verify fixed code project
Set-Location fixed-code
.\mvnw verify -B
Set-Location ..