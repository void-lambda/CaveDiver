@echo off
cd /d "%~dp0"
if not exist build mkdir build
dir /s /b src\*.java > sources.txt
javac -d build -cp "lib\jlayer-1.0.1.jar" @sources.txt
del sources.txt
if errorlevel 1 pause