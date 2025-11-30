# Automation Project — Confluence-Ready Documentation

This document is intended to be copied into Confluence (or used as a project README). It describes the project skeleton, technologies, how we use the Carina framework, run and reporting instructions, and suggested Confluence page structure.

---

## Summary

- **Purpose:** central documentation for the `mobile-automation-ez-bank` automation project.
- **Audience:** automation engineers, devs, QA, release/CI owners.

## Recommended Confluence Page Title

- Automation > EZ Bank > Automation Overview

Suggested child pages:
- Project Structure
- How To Run Tests
- Carina Framework Usage
- Reporting & CI
- Test Data & Configuration

## Project Skeleton (high level)

Project root (relevant paths):

- `mobile-automation-ez-bank/`
  - `pom.xml` — module POM with Carina & other dependencies
  - `src/main/java/` — reusable code and page objects
  - `src/test/java/` — step definitions, runners, test helpers
  - `src/test/resources/` — Cucumber feature files & test resources
  - `target/` — build artifacts and test output
  - `docs/` — (this file)

Example tree:

```
mobile-automation-ez-bank/
├─ pom.xml
├─ src/
│  ├─ main/java/com/automation/...
│  └─ test/java/com/automation/...
├─ src/test/resources/features/*.feature
└─ docs/automation-confluence.md
```

## Technologies Used

- Java (Configured to `java.version` in `pom.xml`)
- Maven (build and dependency management)
- Carina framework (core, api, webdriver, dataprovider, utils)
- Cucumber (BDD) + `cucumber-java`, `cucumber-testng`
- TestNG (test runner)
- Carina built-in reporting (HTML, attachments) and optional Zebrunner integration
- Optional: Zebrunner integration (agent) for advanced reporting

## Carina Framework — How we use it

- Modules in `pom.xml`:
  - `carina-core` — core test framework building blocks
  - `carina-webdriver` — WebDriver/Mobile driver wrappers and utilities
  - `carina-api` — API testing helpers and HTTP method classes
  - `carina-dataprovider` — data-driven test support
  - `carina-utils` — common helpers

- Patterns followed:
  - Page Object Model for UI/mobile screens (`pages` package)
  - Cucumber feature files drive scenarios; step definitions call page objects and API methods
  - Carina method classes (e.g., API methods) are used to encapsulate request building and response validation
  - Logging: SLF4J (Carina logs) used across stepdefs and pages
  - Assertions: TestNG / Carina assertion helpers and `SoftAssert` used for validations

## How We Work (process)

- Tests: written as Cucumber features with tags for grouping (e.g., `@smoke`, `@api`).
- Branching: feature branches, PR + CI build
- Reviews: PRs should include related feature files and step changes
- Test Data: kept in `src/test/resources` or built inline (no external JSON templates unless required)

## Running Tests — commands and examples

Open a terminal at `mobile-automation-ez-bank/` and run:

```bash
# Full test run
mvn clean test

# Build without running tests
mvn clean install -DskipTests

# Run a single TestNG/Cucumber runner class (Surefire picks up **/*Runner.java by default)
mvn -Dtest=MyRunner test

# Run with suite or custom properties
mvn clean test -Dsuite=apiSuite -Denv=qa

# Cucumber tag filtering (older and newer CLI options exist; example):
# For Cucumber 6+/7+, use cucumber filter system property (depends on runner setup)
mvn test -Dcucumber.filter.tags="@smoke and not @wip"

# Run a single TestNG method (if needed)
mvn -Dtest=MyTestClass#testMethod test
```

Environment / device properties commonly used:

- `-Denv` — environment configuration (qa, staging, prod)
- `-Dsuite` — custom suite name as used in POM/profiles
- `-Ddevice` — mobile device name or target
- `-Dconfig` — path to alternate config file

Mobile-specific notes:

- For local mobile testing use an emulator/simulator or an attached device.
- For cloud/device-farm testing (e.g., Selenoid, BrowserStack, SauceLabs), set remote driver and credentials via env vars or Maven properties.


## Reporting

- Carina reporting: Carina provides built-in reporting listeners that collect test logs, screenshots, and attachments and generate HTML-friendly artifacts. Reports and attachments are produced by Carina listeners during test execution; check `carina.properties` or listener configuration for the exact output directory.
- Zebrunner: if integrated (via `zebrunner-agent` or Carina configs), test results are pushed to Zebrunner; ensure `zebrunner` configuration is provided.
 - Zebrunner: if integrated (via `zebrunner-agent` or Carina configs), test results are pushed to Zebrunner; ensure `zebrunner` configuration is provided.
- TestNG output: `target/surefire-reports` contains TestNG XMLs and results.

Where to look after a run:

- `target/` — test artifacts and HTML reports (Carina listener output)
- `target/surefire-reports` — TestNG logs & reports
- `target/cucumber-reports` or other adapter-specific folders if additional adapters are used

Tip: screenshots and attachments are added by Carina listeners; to change behavior, update `carina.properties` or the listener configuration in your test bootstrap.

### Example `carina.properties` listener keys (example)

Below is a safe example snippet you can add to `carina.properties` to control Carina reporting/listener behavior. This is a generic example — please verify exact keys/class names against the Carina version you use (this repo does not contain a `carina.properties` file currently).

```
# Where Carina will write report artifacts
carina.reports.dir=target/carina-reports

# Listener classes (comma-separated). Replace with concrete listener class names from your Carina build
# Example: carina.listeners=com.zebrunner.carina.core.listeners.CarinaTestListener,com.zebrunner.carina.core.listeners.ScreenshotListener
carina.listeners=

# Screenshots: whether to capture screenshots on failure
carina.screenshots.on_failure=true
carina.screenshots.path=${carina.reports.dir}/screenshots

# Zebrunner integration (optional)
zebrunner.enabled=false
zebrunner.url=
zebrunner.token=

# Logging level (optional)
carina.log.level=INFO
```

Notes:
- If you want exact listener class names for `carina.listeners`, I can search the Carina JARs or your local Carina distribution; otherwise, use the listener classes provided by your `carina-core` / `carina-webdriver` packages.
- After updating `carina.properties`, run a test and inspect `target/` to confirm the report output location.

### Zebrunner configuration example

Zebrunner integration in Carina uses an `agent.properties` file (standard Carina approach). The `zebrunner-agent` capability is transitively provided by `carina-core`, so no separate dependency is required.

#### Using agent.properties (recommended)

Example `agent.properties` (we provided one at `src/main/resources/agent.properties`):

```
reporting.enabled=true
reporting.server.hostname=https://your-zebrunner-instance.zebrunner.com
reporting.server.access-token=YOUR_ZEBRUNNER_ACCESS_TOKEN
reporting.run.display-name=EZBank Automation Suite
reporting.run.build=${env.BUILD_NUMBER}
reporting.run.environment=qa
```

Notes:
- Set `reporting.enabled=false` to disable Zebrunner reporting.
- Store `reporting.server.access-token` securely in CI secrets (not in the repo).
- The file is automatically loaded from `src/main/resources/agent.properties` by Carina at runtime.

## Test Artifacts and Logs

- Console logs: build output
- Carina driver logs: check `target/` for driver logs depending on configuration
- Attachments/screenshots: configured in Carina listeners (verify `carina.properties` or listener configs)

## Test Data & Configuration

- `src/test/resources/config.properties` or `config.properties` in `target/classes` used by Carina — list important keys and where to set them.
- Secrets and credentials: store in secure vaults or CI secret store; avoid committing credentials to VCS.

## CI Integration (recommended)

- Jenkins / GitHub Actions / GitLab CI steps:
  - Checkout
  - Set environment variables and secrets (devices, cloud credentials)
  - Install required SDKs (Android SDK for mobile builds, emulators)
  - Start emulator or connect device/cloud driver
  - Run `mvn clean test` with appropriate properties
  - Publish reports (HTML/Extent) and artifacts
  - Optional: push results to Zebrunner

## Confluence Page Skeleton (copyable)

Use the following sections as separate Confluence pages or page sections.

1) Overview

```
Summary: Short summary of the automation purpose.
Owner: <team or person>
Contact: <email/Slack>
```

2) Project Structure

```
- repo layout
- responsibilities of each folder
- example runner names
```

3) How to Run

```
Commands:
- mvn clean test
- mvn -Dtest=MyRunner test
Environment properties:
- -Denv=qa
- -Dsuite=apiSuite
```

4) Carina Usage

```
Explain modules used, where to find page objects, step defs, API method classes.
```

5) Reporting

```
Location of HTML reports, how to attach to CI job artifacts, Zebrunner integration notes.
```

6) Troubleshooting

```
Common issues (missing emulator, wrong Java version, dependency problems) and fixes.
```

## Maintenance & Contacts

- Keep dependencies (Carina versions) updated in `pom.xml`.
- Add owners for automation components.

## Next Steps / To Do

- Review this document and copy into Confluence.
- Fill in project-specific keys (exact report directory, any custom listeners, current Zebrunner project tokens).
- Optionally: script Confluence page creation using Confluence REST API or CLI.

---

If you want, I can:

- Convert this markdown to Confluence storage format and create a CURL snippet to publish it via Confluence REST API.
- Split this into separate `docs/*.md` pages (HowToRun.md, Carina-Usage.md, Reporting.md).
- Create a small `README.md` in the root that links to this doc.

**File:** `docs/automation-confluence.md` (created)
