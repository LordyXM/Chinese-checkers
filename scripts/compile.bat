@echo off
setlocal enabledelayedexpansion
set ROOT=%~dp0..
set OUT=%ROOT%\out
if exist "%OUT%" rmdir /s /q "%OUT%"
mkdir "%OUT%"

dir /s /b "%ROOT%\engine\src\main\java\*.java" > "%OUT%\sources_engine.txt"
javac -d "%OUT%\engine" @"%OUT%\sources_engine.txt"
if errorlevel 1 exit /b 1

dir /s /b "%ROOT%\graphics\src\main\java\*.java" > "%OUT%\sources_graphics.txt"
javac -cp "%OUT%\engine" -d "%OUT%\graphics" @"%OUT%\sources_graphics.txt"
if errorlevel 1 exit /b 1

dir /s /b "%ROOT%\demo-console\src\main\java\*.java" > "%OUT%\sources_demo_console.txt"
javac -cp "%OUT%\engine;%OUT%\graphics" -d "%OUT%\demo-console" @"%OUT%\sources_demo_console.txt"
if errorlevel 1 exit /b 1

dir /s /b "%ROOT%\demo-gui\src\main\java\*.java" > "%OUT%\sources_demo_gui.txt"
javac -cp "%OUT%\engine;%OUT%\graphics" -d "%OUT%\demo-gui" @"%OUT%\sources_demo_gui.txt"
if errorlevel 1 exit /b 1

dir /s /b "%ROOT%\tests\src\main\java\*.java" > "%OUT%\sources_tests.txt"
javac -cp "%OUT%\engine;%OUT%\graphics" -d "%OUT%\tests" @"%OUT%\sources_tests.txt"
if errorlevel 1 exit /b 1

echo OK: compiled to %OUT%
