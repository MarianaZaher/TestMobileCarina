# Mobile Automation EZ Bank

Modern test automation framework powered by **Cucumber BDD** with comprehensive reporting.

## Features

- **API Testing** - REST Assured library for API automation with JSON validation
- **BDD Support** - Cucumber scenarios with Gherkin syntax
- **Triple Reporting** - Extent Spark, Cucumber HTML, and TestNG reports
- **CI/CD Ready** - Jenkins pipeline included

## Prerequisites

- **Java 11+** - JDK 11 or higher
- **Maven 3.6+** - Build automation tool
- **VS Code** - Recommended IDE with Java extension pack

## Quick Start

### 1. Clone the Repository
```bash
git clone https://gitlab.com/abdelrahman-omarr/mobile-automation-ez.git
cd mobile-automation-ez
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run Tests
```bash
# Run API tests
mvn clean test -Dtest=CucumberAPIRunner

# Run all tests
mvn clean test -Dtest=CucumberTestRunner
```

## View Reports

After running tests, open the reports:

**Extent Spark Report** (Recommended)
```bash
start test-output/SparkReport/Spark.html
```

**Cucumber HTML Report**
```bash
start target/cucumber-reports/cucumber.html
```

**TestNG Emailable Report**
```bash
start reports/emailable-report.html
```

**Open latest report in `reports/`**

If you want to open the most-recent report file produced under the `reports/` folder, use this PowerShell snippet. It finds the newest file (by modification time) and opens it with the default application:

```powershell
# Find the newest HTML report in `reports/` and open it (preferred for HTML reports):
$latestHtml = Get-ChildItem -Path .\reports -Filter '*.html' -File -ErrorAction SilentlyContinue | Sort-Object LastWriteTime -Descending | Select-Object -First 1
if ($latestHtml) { Start-Process $latestHtml.FullName } else { Write-Host 'No HTML reports found in .\reports' }

# Or open the newest file of any type in `reports/`:
$latest = Get-ChildItem -Path .\reports -File -ErrorAction SilentlyContinue | Sort-Object LastWriteTime -Descending | Select-Object -First 1
if ($latest) { Start-Process $latest.FullName } else { Write-Host 'No files found in .\reports' }
```

Notes:
- The snippet uses `Start-Process` to open the file with the system's default handler (usually your browser for HTML files).
- Run these commands from the project root (`mobile-automation-ez-bank`) so the relative `reports/` path resolves correctly.

## Project Structure

```
mobile-automation-ez/
├── src/
│   ├── main/
│   │   ├── java/              # Page objects & utilities
│   │   └── resources/         # Configuration files
│   └── test/
│       ├── java/
│       │   ├── runners/       # Test runners
│       │   └── steps/         # Step definitions
│       └── resources/
│           └── features/      # Cucumber BDD scenarios
├── reports/                   # Test reports
├── test-output/              # Extent Spark reports
└── target/                   # Cucumber HTML reports
```

## Test Runners

| Runner | Purpose | Command |
|--------|---------|---------|
| `CucumberAPIRunner` | API tests only | `mvn test -Dtest=CucumberAPIRunner` |
| `CucumberTestRunner` | All BDD scenarios | `mvn test -Dtest=CucumberTestRunner` |

## Reporting Systems

| Report Type | Location | Best For |
|------------|----------|----------|
| **Extent Spark** | `test-output/SparkReport/Spark.html` | Beautiful dashboards & charts |
| **Cucumber HTML** | `target/cucumber-reports/cucumber.html` | BDD scenario details |
| **TestNG Reports** | `reports/emailable-report.html` | Quick email summaries |

## Configuration

Main configuration file: `src/main/resources/_config.properties`

```properties
url=https://www.gsmarena.com
browser=chrome
project_report_directory=./reports
auto_screenshot=true
```

## Android Emulator (AVD)

- **List available AVDs** (PowerShell / Windows):
```powershell
# If `emulator` is on your PATH:
emulator -list-avds

# If `emulator` is not on your PATH, use the Android SDK path:
& "$env:ANDROID_SDK_ROOT\emulator\emulator.exe" -list-avds
```

- **Start an emulator** — replace `emulator_name` with the AVD name you want to run:
```powershell
# Simple start (when `emulator` is on PATH):
emulator -avd emulator_name

# Full path (when not on PATH):
& "$env:ANDROID_SDK_ROOT\emulator\emulator.exe" -avd "emulator_name"

# Start the first available AVD from the list (PowerShell):
$avd = (emulator -list-avds | Select-Object -First 1)
emulator -avd $avd
```

- **Verify the emulator is running**:
```powershell
adb devices
```

- Notes:
	- Replace `emulator_name` with the exact AVD name returned by `-list-avds`.
	- Ensure `ANDROID_SDK_ROOT` (or `ANDROID_HOME`) is set in your environment so the `emulator` and `adb` tools can be found.
