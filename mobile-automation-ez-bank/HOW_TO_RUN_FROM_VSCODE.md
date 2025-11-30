# 🎯 How to Run Scenarios from VS Code

## ✅ **You're All Set!**

Your VS Code is now configured to run Cucumber scenarios directly from the editor.

---

## 🚀 **3 Ways to Run Tests**

### **Method 1: Run/Debug Buttons** ▶️
1. Open any `.feature` file (e.g., `user_api.feature`)
2. You'll see **Run | Debug** links above each scenario
3. Click **Run** to execute that specific scenario
4. Click **Debug** to debug that scenario

**Example:**
```gherkin
@smoke @post
Scenario: Create a new user    ← Click "Run" or "Debug" above this line
  Given the API base URL is configured
  When I send a POST request to create a user
  Then the response status code should be 201 test
```

---

### **Method 2: Right-Click Context Menu** 🖱️
1. **Right-click** anywhere in the `.feature` file
2. Select **"Run Java"** or **"Debug Java"**
3. Choose:
   - **Run Current Scenario** (runs scenario at cursor position)
   - **Run Cucumber Feature** (runs entire feature file)
   - **Run with TestNG Runner** (runs all features)

---

### **Method 3: Testing Panel** 🧪
1. Open **Testing** view (beaker icon in left sidebar)
2. You'll see all scenarios listed
3. Click the **▶️ play button** next to:
   - Individual scenario (runs one)
   - Feature file (runs all scenarios in that feature)
   - Test folder (runs all features)

---

## 📋 **Current Scenarios Available**

Open `src/test/resources/features/user_api.feature`:

| Line | Scenario | Tags |
|------|----------|------|
| 8 | Create a new user | `@smoke @post` |
| 13 | Get user by ID | `@regression @get` |
| 18 | Validate user creation response | `@smoke @validation` |

---

## 🎬 **Quick Demo**

1. **Open** `user_api.feature`
2. **Look above line 8** (Create a new user scenario)
3. **Click the "Run"** link
4. **Watch** the test execute in the integrated terminal!

---

## 🔧 **Alternative: Command Line**

If buttons don't appear, you can still run from terminal:

```powershell
# Run all tests
mvn clean test

# Run specific feature file
mvn test -Dcucumber.features="src/test/resources/features/user_api.feature"

# Run specific scenario by line number
mvn test -Dcucumber.features="src/test/resources/features/user_api.feature:8"
```

---

## 📊 **View Reports**

After running tests:
- **HTML Report**: `target/cucumber-report.html`
- **JSON Report**: `target/cucumber.json`

---

## ❓ **Troubleshooting**

If Run/Debug buttons don't appear:
1. Make sure Java extension pack is installed
2. Reload VS Code window (Ctrl+Shift+P → "Reload Window")
3. Run `mvn clean test-compile` first
4. Check that `.vscode/launch.json` and `.vscode/settings.json` exist

---

## ✨ **That's It!**

You can now run individual scenarios with a single click! 🎉
