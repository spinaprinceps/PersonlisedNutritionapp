$wrapperUrl = "https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar"
$outputPath = "gradle/wrapper/gradle-wrapper.jar"

# Create the directory if it doesn't exist
New-Item -ItemType Directory -Force -Path "gradle/wrapper"

# Download the file
Invoke-WebRequest -Uri $wrapperUrl -OutFile $outputPath

Write-Host "Gradle wrapper JAR downloaded successfully to $outputPath" 