@echo off
REM Cucumber Runner Script for Windows
cd /d "%~dp0"
mvn test -Dcucumber.features="src/test/resources/features/%1" -Dcucumber.filter.tags="%2"
