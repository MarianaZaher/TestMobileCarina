# Automation Testing Framework - Carina + Cucumber

## 🚀 How to Run Tests

### 1️⃣ **Run ALL Features (All Scenarios)**
```powershell
mvn clean test
```

### 2️⃣ **Run Specific Tag (Individual Scenario or Group)**

#### Run only @smoke scenarios:
```powershell
mvn clean test -Dcucumber.filter.tags="@smoke"
```

#### Run only @regression scenarios:
```powershell
mvn clean test -Dcucumber.filter.tags="@regression"
```

#### Run only @post scenarios:
```powershell
mvn clean test -Dcucumber.filter.tags="@post"
```

#### Run only @get scenarios:
```powershell
mvn clean test -Dcucumber.filter.tags="@get"
```

### 3️⃣ **Run Specific Feature File**
```powershell
mvn clean test -Dcucumber.features="src/test/resources/features/user_api.feature"
```

### 4️⃣ **Run Specific Scenario by Line Number**
```powershell
mvn clean test -Dcucumber.features="src/test/resources/features/user_api.feature:8"
```
*(Line 8 is the "Create a new user" scenario)*

### 5️⃣ **Combine Tags (AND/OR/NOT)**

#### Run scenarios with BOTH @smoke AND @post:
```powershell
mvn clean test -Dcucumber.filter.tags="@smoke and @post"
```

#### Run scenarios with @smoke OR @regression:
```powershell
mvn clean test -Dcucumber.filter.tags="@smoke or @regression"
```

#### Run @api scenarios but NOT @smoke:
```powershell
mvn clean test -Dcucumber.filter.tags="@api and not @smoke"
```

---

## 📋 Available Scenarios

### Feature: User API Testing (`user_api.feature`)

| Scenario | Tags | Line |
|----------|------|------|
| Create a new user | `@smoke @post` | 8 |
| Get user by ID | `@regression @get` | 13 |
| Validate user creation response | `@smoke @validation` | 18 |

---

## 📊 Test Reports

After running tests, view reports at:

- **HTML Report**: `target/cucumber-report.html`
- **JSON Report**: `target/cucumber.json`
- **ExtentReports**: `target/extent-reports/`

---

## 🎯 Quick Examples

### Example 1: Run only smoke tests
```powershell
mvn clean test -Dcucumber.filter.tags="@smoke"
```

### Example 2: Run a single scenario (Create user)
```powershell
mvn clean test -Dcucumber.features="src/test/resources/features/user_api.feature:8"
```

### Example 3: Run all API tests
```powershell
mvn clean test -Dcucumber.filter.tags="@api"
```
