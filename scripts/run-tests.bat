@echo off
set ROOT=%~dp0..
set OUT=%ROOT%\out
java -ea -cp "%OUT%\engine;%OUT%\graphics;%OUT%\tests" com.example.tests.TestRunner
