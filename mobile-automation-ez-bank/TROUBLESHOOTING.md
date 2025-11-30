# 🔍 Troubleshooting: Run Buttons Not Appearing

## ✅ Extensions Installed (Confirmed)
- ✓ **Language Support for Java** (Red Hat)
- ✓ **Debugger for Java** 
- ✓ **Test Runner for Java**
- ✓ **Maven for Java**
- ✓ **Extension Pack for Java**
- ✓ **Cucumber (Gherkin) Full Support**
- ✓ **Cucumber Official**

---

## 🔧 **Solution: 3 Methods to Run Tests**

### **Method 1: Use Testing Panel** 🧪 (RECOMMENDED)

1. Click the **Testing icon** (flask/beaker) in the left sidebar
2. You should see your test runner listed
3. Click the **refresh button** if tests don't appear
4. Run tests from there

**If tests don't appear in Testing panel:**
- Run: `Ctrl+Shift+P` → Type: `Java: Clean Java Language Server Workspace`
- Then reload VS Code

---

### **Method 2: Use Run Configurations** ▶️

1. Press `F5` or click **Run → Start Debugging**
2. Select one of these configurations:
   - **Run Cucumber Feature** - Runs entire feature file
   - **Run Current Scenario** - Runs scenario at cursor
   - **Run with TestNG Runner** - Runs all tests

**Quick Steps:**
```
1. Open user_api.feature
2. Place cursor on a scenario
3. Press F5
4. Select "Run Current Scenario"
```

---

### **Method 3: Use Maven Commands** 💻 (ALWAYS WORKS)

Open Terminal in VS Code (`Ctrl+`` `) and run:

```powershell
# Run all tests
mvn test

# Run specific feature
mvn test -Dcucumber.features="src/test/resources/features/user_api.feature"

# Run specific scenario (by line number)
mvn test -Dcucumber.features="src/test/resources/features/user_api.feature:8"

# Run by tag
mvn test -Dcucumber.filter.tags="@smoke"
```

---

## 🎯 **Quick Test Right Now**

Run this command to verify everything works:

```powershell
mvn test -Dcucumber.features="src/test/resources/features/user_api.feature:8"
```

This will run ONLY the "Create a new user" scenario (line 8).

---

## 🔄 **Steps to Enable Run/Debug Buttons**

If Run/Debug links still don't appear above scenarios:

### Step 1: Reload VS Code
```
Ctrl+Shift+P → "Developer: Reload Window"
```

### Step 2: Clean Java Workspace
```
Ctrl+Shift+P → "Java: Clean Java Language Server Workspace"
```

### Step 3: Recompile Project
```powershell
mvn clean test-compile
```

### Step 4: Check Java Extension
Make sure "Test Runner for Java" extension is enabled:
- Go to Extensions (`Ctrl+Shift+X`)
- Search for "Test Runner for Java"
- Make sure it's enabled

---

## 📋 **Alternative: Create TestNG XML**

If you prefer TestNG XML-based execution:

Create `testng.xml`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="API Test Suite">
    <test name="User API Tests">
        <classes>
            <class name="com.automation.runners.CucumberTestRunner"/>
        </classes>
    </test>
</suite>
```

Then right-click on `testng.xml` → **Run**

---

## ✨ **What SHOULD Work**

The **Test Runner for Java** extension should automatically detect:
- ✅ TestNG classes (CucumberTestRunner)
- ✅ Individual scenarios in feature files
- ✅ Show play buttons in the Testing panel

---

## 🚨 **If Nothing Works**

**Use Maven (100% reliable):**

```powershell
# This WILL work - it's what Maven does behind the scenes
mvn clean test
```

---

## 📞 **Current Status**

Your project IS working - we successfully ran tests earlier with:
```powershell
mvn clean test
```

The Run buttons are a VS Code UI convenience. Maven commands are the guaranteed way to run tests.

---

## 🎬 **Try This Now:**

1. Open integrated terminal: `Ctrl+`` `
2. Run: `mvn test`
3. Watch your tests execute ✅

That's the most reliable method!
