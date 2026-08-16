#!/bin/bash

# AI Educational Chatbot - Unix/Linux/Mac Startup Script
# This script builds and runs the chatbot application

echo ""
echo "========================================"
echo "   AI Educational Chatbot"
echo "   Version 1.0.0"
echo "========================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "[ERROR] Maven is not installed"
    echo "Please install Maven from: https://maven.apache.org/"
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "[ERROR] Java is not installed"
    echo "Please install Java 11+ from: https://www.oracle.com/java/"
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | grep -oP 'version "\K.*?(?=")')
echo "[✓] Java version: $JAVA_VERSION"

# Check Maven version
MVN_VERSION=$(mvn -v | head -1)
echo "[✓] $MVN_VERSION"
echo ""

# Clean and build
echo "[*] Building project..."
mvn clean install
if [ $? -ne 0 ]; then
    echo "[ERROR] Build failed"
    exit 1
fi

echo ""
echo "[✓] Build successful"
echo "[*] Launching chatbot..."
echo ""

# Run the application
mvn javafx:run

if [ $? -ne 0 ]; then
    echo "[ERROR] Failed to launch application"
    exit 1
fi

echo ""
echo "[✓] Application closed"
