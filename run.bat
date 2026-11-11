@echo off
cd /d "%~dp0"

where java >nul 2>nul
if errorlevel 1 (
    echo.
    echo Java not found!
    echo Install Java 17 or newer from https://adoptium.net
    echo.
    pause
    exit /b 1
)

java -version 2>&1 | findstr /R "version.\"1[7-9]\|version.\"2[0-9]" >nul
if errorlevel 1 (
    echo.
    echo Your Java is too old. Java 17+ is required.
    echo Download it from https://adoptium.net
    echo.
    pause
    exit /b 1
)

java -jar CaveDiver.jar

pause