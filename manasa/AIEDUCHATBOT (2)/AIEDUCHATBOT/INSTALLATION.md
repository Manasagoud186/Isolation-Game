# 📦 Installation Guide

## Overview
This guide provides detailed instructions for installing and running the AI Educational Chatbot on Windows, macOS, and Linux.

## Table of Contents
1. [System Requirements](#system-requirements)
2. [Installation Steps](#installation-steps)
3. [Verification](#verification)
4. [Troubleshooting](#troubleshooting)

## System Requirements

### Minimum Requirements
| Component | Version |
|-----------|---------|
| **Java Development Kit (JDK)** | 11 or higher |
| **Maven** | 3.6.0 or higher |
| **RAM** | 2 GB |
| **Disk Space** | 500 MB |

### Operating Systems Supported
- ✅ Windows 10/11
- ✅ macOS 10.13+
- ✅ Ubuntu 18.04+
- ✅ Other Linux distributions

## Installation Steps

### Step 1: Install Java Development Kit (JDK)

#### Windows
1. Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [Eclipse Adoptium](https://adoptium.net/)
2. Run the installer (.exe file)
3. Follow installation wizard
4. Add Java to PATH (usually automatic)
5. Verify installation:
   ```cmd
   java -version
   ```

#### macOS
Using Homebrew:
```bash
brew install openjdk@11
# or for latest LTS
brew install openjdk@17
```

Or download from [Oracle](https://www.oracle.com/java/technologies/downloads/)

Verify:
```bash
java -version
```

#### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-11-jdk
java -version
```

#### Linux (Fedora/RHEL)
```bash
sudo dnf install java-11-openjdk-devel
java -version
```

### Step 2: Install Maven

#### Windows
1. Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to a folder (e.g., `C:\Program Files\Maven`)
3. Add Maven `bin` folder to PATH
4. Verify installation:
   ```cmd
   mvn -version
   ```

#### macOS
Using Homebrew:
```bash
brew install maven
mvn -version
```

#### Linux
Ubuntu/Debian:
```bash
sudo apt install maven
mvn -version
```

Fedora/RHEL:
```bash
sudo dnf install maven
mvn -version
```

### Step 3: Download Project

#### Option A: Clone with Git
```bash
git clone https://github.com/your-repo/AIEDUCHATBOT.git
cd AIEDUCHATBOT
```

#### Option B: Download ZIP
1. Download ZIP from GitHub/source
2. Extract to desired location
3. Navigate to folder:
   ```bash
   cd AIEDUCHATBOT
   ```

### Step 4: Build Project

Navigate to project directory and run:

```bash
mvn clean install
```

This will:
- Download all dependencies
- Compile the source code
- Run any tests
- Create JAR files

⏱️ **First build may take 2-5 minutes** (downloading dependencies)

### Step 5: Run Application

Choose one of the methods:

#### Method 1: Maven (Recommended)
```bash
mvn javafx:run
```

#### Method 2: Direct Java Execution
```bash
java -cp target/classes;target/dependency/* com.educhatbot.ui.ChatbotGUI
```
(Windows - use `;` separator, Linux/Mac use `:`)

#### Method 3: Executable JAR
```bash
mvn package
java -jar target/educhatbot-uber.jar
```

#### Method 4: Windows Batch Script
```cmd
run.bat
```

#### Method 5: Linux/Mac Shell Script
```bash
chmod +x run.sh
./run.sh
```

## Verification

### After Installation, Verify:

1. **Java Installation**
   ```bash
   java -version
   # Should show Java 11+
   ```

2. **Maven Installation**
   ```bash
   mvn -version
   # Should show Maven 3.6.0+
   ```

3. **Project Structure**
   ```bash
   ls -la
   # Should see: src/, pom.xml, README.md, etc.
   ```

4. **Build Success**
   ```bash
   mvn verify
   # Should show "BUILD SUCCESS"
   ```

5. **Application Launch**
   ```bash
   mvn javafx:run
   # Should show GUI window
   ```

## Setting up Environment Variables

### Windows (Optional but Recommended)

1. **Set JAVA_HOME**
   ```cmd
   setx JAVA_HOME "C:\Program Files\Java\jdk-11"
   ```

2. **Set MAVEN_HOME**
   ```cmd
   setx MAVEN_HOME "C:\Program Files\Maven"
   ```

3. **Add to PATH**
   ```cmd
   setx PATH "%PATH%;%JAVA_HOME%\bin;%MAVEN_HOME%\bin"
   ```

4. Restart terminal

### Linux/macOS (Optional)

Add to `~/.bashrc`, `~/.zshrc`, or `~/.profile`:

```bash
export JAVA_HOME=/path/to/jdk
export MAVEN_HOME=/path/to/maven
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
```

Then:
```bash
source ~/.bashrc
```

## Troubleshooting

### Issue: "java: command not found"

**Cause**: Java not installed or not in PATH

**Solution**:
1. Install Java (see Step 1)
2. Add Java to PATH environment variable
3. Restart terminal
4. Verify: `java -version`

### Issue: "mvn: command not found"

**Cause**: Maven not installed or not in PATH

**Solution**:
1. Install Maven (see Step 2)
2. Add Maven to PATH
3. Restart terminal
4. Verify: `mvn -version`

### Issue: Build fails with "Cannot find JavaFX"

**Cause**: JavaFX dependencies not found

**Solution**:
```bash
# Use Maven to fetch dependencies
mvn clean install -X

# Or run with specific profile
mvn clean install -Pjavafx
```

### Issue: Application window doesn't appear

**Cause**: Display or graphics driver issue

**Solution**:
1. Try alternative run method
2. Check display settings
3. Run from terminal to see error messages:
   ```bash
   mvn javafx:run > output.log 2>&1
   cat output.log
   ```

### Issue: Port/Window conflicts

**Cause**: Another instance running

**Solution**:
1. Close other instances
2. Kill process on port (if applicable)
3. Try again

### Issue: Out of Memory error

**Cause**: Insufficient heap memory

**Solution**:
```bash
export MAVEN_OPTS=-Xmx2g
mvn javafx:run
```

Or set in Windows:
```cmd
set MAVEN_OPTS=-Xmx2g
mvn javafx:run
```

### Issue: Permission denied (run.sh)

**Cause**: Script not executable

**Solution**:
```bash
chmod +x run.sh
./run.sh
```

### Issue: "No such file or directory" for stylesheet

**Cause**: CSS file not in resources folder

**Solution**:
Verify folder structure:
```
src/main/resources/styles.css
```

If missing, recreate it with proper styling.

## Getting Help

If issues persist:

1. **Check Logs**
   ```bash
   mvn javafx:run > app.log 2>&1
   # Check app.log for errors
   ```

2. **Verify Versions**
   ```bash
   java -version
   mvn -version
   ```

3. **Clean Rebuild**
   ```bash
   mvn clean
   mvn install
   ```

4. **Update Dependencies**
   ```bash
   mvn dependency:resolve-plugins
   mvn install
   ```

5. **Check System Requirements**
   - Verify Java 11+
   - Verify Maven 3.6+
   - Check disk space (500MB)
   - Check RAM (2GB+)

## Uninstallation

### Remove Application
```bash
# Delete project folder
rm -rf AIEDUCHATBOT

# Or on Windows
rmdir /s AIEDUCHATBOT
```

### Optional: Remove Java/Maven
- Windows: Use Control Panel > Programs and Features
- macOS: Use Homebrew or manual deletion
- Linux: Use package manager (`apt remove`, `dnf remove`, etc.)

## Next Steps

After successful installation:

1. Read [QUICKSTART.md](QUICKSTART.md) for usage
2. Check [README.md](README.md) for detailed documentation
3. Start asking academic questions!

## Support

For additional help:
- Review error messages carefully
- Check official documentation:
  - [Java Documentation](https://docs.oracle.com/en/java/)
  - [Maven Documentation](https://maven.apache.org/guides/)
  - [JavaFX Documentation](https://openjfx.io/)

---

**Installation Complete!** 🎉

You're now ready to use the AI Educational Chatbot. Proceed to [QUICKSTART.md](QUICKSTART.md).
