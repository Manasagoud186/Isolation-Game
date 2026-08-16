@echo off
REM AI Educational Chatbot - Windows Startup Script
REM This script builds and runs the chatbot application

echo.
echo ========================================
echo   AI Educational Chatbot
echo   Version 1.0.0
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Maven is not installed or not in PATH
    echo Please install Maven from: https://maven.apache.org/
    pause
    exit /b 1
)

REM Check if Java is installed
where java >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Java is not installed or not in PATH
    echo Please install Java 11+ from: https://www.oracle.com/java/
    pause
    exit /b 1
)

echo [✓] Prerequisites verified
echo.

REM Clean and build
echo [*] Building project...
call mvn clean install
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Build failed
    pause
    exit /b 1
)

echo.
echo [✓] Build successful
echo [*] Launching chatbot...
echo.

REM Run the application
call mvn javafx:run

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Failed to launch application
    pause
    exit /b 1
)

echo.
echo [✓] Application closed
pause
