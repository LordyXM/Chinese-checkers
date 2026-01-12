@echo off
set ROOT=%~dp0..
set OUT=%ROOT%\out
java -cp "%OUT%\engine;%OUT%\graphics;%OUT%\demo-console" com.example.democonsole.ConsoleDemoMain
