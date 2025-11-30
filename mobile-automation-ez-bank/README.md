# EZ Bank Mobile Automation — Quick Start Guide

Welcome to the **mobile-automation-ez-bank** test automation project. This is a Carina Framework–based automation suite for API and mobile UI testing of the EZ Bank application.

## 📚 Documentation

Complete documentation (including project structure, Carina usage, how to run tests, and CI/reporting) is available in:

- **[Confluence-Ready Full Documentation](./docs/automation-confluence.md)** — copy into Confluence or use as reference

## 🚀 Quick Start

### Prerequisites

- Java 21+ installed
- Maven 3.8+
- For mobile testing: Android SDK / emulator or connected device

### Devcontainer / Codespaces Maven registry

This repository contains a `devcontainer` configuration that copies a template `settings.xml` to the container's Maven settings on create. The template uses environment variables for your internal Maven repository and credentials.

Set the following environment variables in your Codespaces/devcontainer or CI secrets before opening the devcontainer:

- `MAVEN_INTERNAL_REPO_URL` — URL to your internal Maven repository (e.g. https://nexus.company.com/repository/maven-group/)
- `MAVEN_REPO_SERVER_ID` — server id used in `settings.xml` (e.g. `internal-repo`)
- `MAVEN_REPO_USER` — username or service account for the repository
- `MAVEN_REPO_PASSWORD` — password or token for the repository

The devcontainer `postCreateCommand` copies `.devcontainer/settings.xml` to `~/.m2/settings.xml` on container creation.

### Install & Build

```bash
cd mobile-automation-ez-bank
mvn clean install -DskipTests
```

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Suite

```bash
# Run API tests only
mvn -Dtest=*ApiRunner test

# Run with custom environment
mvn clean test -Denv=qa

# Run Cucumber features with tag filter
mvn test -Dcucumber.filter.tags="@smoke"
```

## 📦 What's Inside

| Component | Purpose |
|-----------|---------|
| `src/main/java/` | Page objects, API methods, utilities |
| `src/test/java/` | Step definitions, test runners |
| `src/test/resources/` | Cucumber feature files (.feature), test data |
| `pom.xml` | Maven configuration, dependencies (Carina, Cucumber, TestNG) |
| `docs/` | Full documentation |

## 🧪 Test Organization

- **Features:** `src/test/resources/features/*.feature`
- **Step Definitions:** `src/test/java/com/automation/stepdefinitions/`
- **Page Objects:** `src/main/java/com/automation/pages/`
- **API Methods:** `src/main/java/com/automation/api/`

## 🔧 Technologies

- **Framework:** [Carina 1.2.4](https://zebrunner.com/carina/) (API, WebDriver, Core)
- **BDD:** Cucumber 7.18.1
- **Test Runner:** TestNG 7.10.2
- **Build:** Maven 3.x
- **Reporting:** Carina built-in reporting (HTML, attachments) + optional Zebrunner integration

## 📊 Reports

After running tests, find reports in:

```
target/
  ├── surefire-reports/          # TestNG reports
  ├── cucumber-reports/          # Cucumber/Extent reports (if configured)
  └── [other artifacts]
```

## 🛠️ Common Commands

```bash
# Compile without running tests
mvn clean compile

# Run tests with verbose output
mvn clean test -X

# Skip tests and just build JAR
mvn clean package -DskipTests

# Run a single test class
mvn -Dtest=LoginPageStepsTest test

# Run tests and generate Surefire report
mvn clean test surefire-report:report
```

## 💡 Key Patterns

- **Page Object Model (POM):** All UI interactions encapsulated in page classes
- **Carina API Methods:** API requests/responses built and validated using Carina method classes
- **Step Definitions:** Cucumber steps delegate to page objects and API methods
- **Logging:** SLF4J used throughout for detailed test logs
- **Assertions:** TestNG assertions + `SoftAssert` for multi-step validations

## 📖 Full Documentation

For detailed guides on:
- Project structure and folder organization
- All available Maven commands and environment properties
- How to use Carina framework (Page Objects, API, Mobile)
- Reporting and CI/CD integration
- Troubleshooting and FAQs

→ **See [docs/automation-confluence.md](./docs/automation-confluence.md)**

## 🤝 Contributing

1. Create a feature branch: `git checkout -b feature/add-login-tests`
2. Add feature files and step definitions
3. Run tests locally: `mvn clean test`
4. Push and open a pull request

## 📞 Support & Contacts

- Framework Issues: Check Carina documentation or project wiki
- Test Failures: Review test logs in `target/surefire-reports/` and Extent reports
- Configuration: See `src/test/resources/config.properties`

---

**Last Updated:** November 2025  
**Framework Version:** Carina 1.2.4-local  
**Java Version:** 21+
