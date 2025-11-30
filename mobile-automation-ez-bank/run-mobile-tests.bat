@echo off
REM Set environment variables
set PATH=D:\files\apache-maven-3.9.11-bin\apache-maven-3.9.11\bin;%PATH%
set ANDROID_SDK_ROOT=C:\Users\Abdelrhman\AppData\Local\Android\Sdk
set ANDROID_HOME=C:\Users\Abdelrhman\AppData\Local\Android\Sdk

REM Run tests with @mobile and @android 
REM Run tests with @mobile and @android tags

mvn clean test "-Dcucumber.filter.tags=@mobile and @android"
mvn clean test "-Dcucumber.filter.tags=@api-ez"

pause
