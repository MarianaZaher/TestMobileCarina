# Local Setup Guide for Mobile Automation Tests

## Prerequisites
- **Git** installed
- **Java 21+** installed (or Java 17+ minimum)
- **Maven 3.8+** installed
- **Android Studio** installed
- **Node.js + npm** installed (for Appium)
- **macOS/Linux/Windows** with hardware virtualization support (KVM on Linux, Hypervisor on Windows)

## Step 1: Clone the Repository

```bash
git clone https://github.com/MarianaZaher/TestMobileCarina.git
cd TestMobileCarina/mobile-automation-ez-bank
```

## Step 2: Install Dependencies Locally

### A. Install Java (if not already installed)
Use SDKMAN to install Java 21:
```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 21.0.9-ms
sdk default java 21.0.9-ms
```

### B. Install Maven (if not already installed)
```bash
# macOS
brew install maven

# Ubuntu/Linux
sudo apt-get install maven

# Or download from: https://maven.apache.org/download.cgi
```

### C. Install Node.js and Appium
```bash
# Install Node.js (if not already installed)
# Download from: https://nodejs.org/ or use a package manager

# Install Appium globally
npm install -g appium appium-doctor

# Verify installation
appium --version
appium-doctor
```

### D. Install Android SDK and Emulator (via Android Studio)
1. Open **Android Studio**
2. Go to **Tools → SDK Manager**
3. Install:
   - **Android SDK Platform 33** (or your desired Android version)
   - **Android Emulator**
   - **Google APIs** system image
4. Note the SDK installation path (typically `~/Android/Sdk`)

## Step 3: Create and Start an Android Emulator

### A. Create an AVD (Android Virtual Device)
1. Open Android Studio
2. Go to **Tools → Device Manager**
3. Click **"Create Device"**
4. Select **"Pixel"** device profile
5. Select **Android 13.0** (API Level 33) or higher
6. Click **"Next"** → **"Finish"**

### B. Start the Emulator
1. In Device Manager, click the **Play** button next to your AVD
2. Wait for the emulator to fully boot (check for Android home screen)

### C. Verify ADB Connection
```bash
# List connected devices
adb devices

# Output should show:
# List of attached devices
# emulator-5554          device
```

## Step 4: Build and Configure the Project

### A. Set Environment Variables
```bash
# Set JAVA_HOME
export JAVA_HOME=/path/to/java/21

# Set ANDROID_HOME
export ANDROID_HOME=~/Android/Sdk

# Add to PATH
export PATH=$JAVA_HOME/bin:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator:$PATH
```

### B. Build the Project
```bash
cd /path/to/TestMobileCarina/mobile-automation-ez-bank

# Clean and compile
mvn clean compile -DskipTests

# Verify compilation
mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@api-ez"
```

## Step 5: Start Appium Server

Open a new terminal and run:
```bash
# Start Appium on default port 4723
appium --address 127.0.0.1 --port 4723

# You should see output like:
# [Appium] Server running on http://127.0.0.1:4723
```

## Step 6: Run Mobile Tests

In another terminal, run:
```bash
cd /path/to/TestMobileCarina/mobile-automation-ez-bank

# Run all mobile tests
mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@mobile"

# Or run API tests
mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@api-ez"
```

## Configuration File

The mobile test configuration is in:
```
src/test/resources/_config.properties
```

**Key settings:**
- `selenium_url=http://127.0.0.1:4723/wd/hub` - Appium server URL
- `capabilities.deviceName=emulator-5554` - Match your emulator name
- `capabilities.app=https://github.com/saucelabs/my-demo-app-android/releases/download/v2.2.0/mda-2.2.0-25.apk` - App URL

## Troubleshooting

### Issue: "Could not start a new session. Response code 404"
**Solution:** Make sure Appium is running and accessible at `http://127.0.0.1:4723`

### Issue: "emulator-5554 not found"
**Solution:** Start the emulator from Android Studio and verify with `adb devices`

### Issue: "JAVA_HOME not set"
**Solution:** Set `JAVA_HOME` to your Java 21 installation directory

### Issue: "Maven command not found"
**Solution:** Install Maven or add it to your PATH

### Issue: App installation fails
**Solution:** The app is downloaded from GitHub during test execution. Ensure internet connectivity.

## Test Execution Summary

### API Tests (No Emulator Required)
```bash
mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@api-ez"
```
**Expected Result:** 6 PASSED, 1 FAILED (expected cookie validation failure)

### Mobile Tests (Emulator Required)
```bash
mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@mobile"
```
**Expected Result:** Tests execute on emulator with Appium

## Test Reports

After execution, reports are generated in:
```
reports/[timestamp]/
```

View the test summary:
```bash
ls -la reports/
cat reports/[timestamp]/test.log
```

## Additional Resources

- [Carina Framework Documentation](https://zebrunner.github.io/carina/)
- [Appium Documentation](https://appium.io/)
- [Android Studio Emulator Guide](https://developer.android.com/studio/run/emulator)
- [Maven Documentation](https://maven.apache.org/)

## Next Steps

1. Clone the repository locally
2. Install all dependencies
3. Create and start an emulator
4. Start Appium server
5. Run tests with Maven

For questions, check the project README.md or documentation in `docs/`
