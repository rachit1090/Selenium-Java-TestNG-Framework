# Selenium Java TestNG Framework
### Automation Exercise Web Application — Test Documentation

**Tester:** Rachit Vashisht  
**Application Under Test:** [Automation Exercise](https://automationexercise.com)  
**Framework:** Selenium Java + TestNG  
**Browsers:** Chrome, Edge (Parallel Execution)  
**Last Report:** Apr 25, 2026 — 28/28 Tests Passed ✅

---

## Table of Contents
1. [Framework Overview](#framework-overview)
2. [Project Structure](#project-structure)
3. [Framework Architecture](#framework-architecture)
4. [Tech Stack](#tech-stack)
5. [Test Plan](#test-plan)
6. [Smoke Test Suite](#smoke-test-suite)
7. [Regression Test Suite](#regression-test-suite)
8. [How to Run](#how-to-run)
9. [Configuration](#configuration)
10. [Reports](#reports)

---

## Framework Overview

This is a **Page Object Model (POM)** based Selenium framework built in Java using TestNG. It supports parallel cross-browser execution, data-driven testing via JSON, automatic retry on flaky tests, and Extent HTML reports with screenshot on failure.

```
Key Features:
✅ Page Object Model (POM)
✅ Cross-browser — Chrome and Edge
✅ Parallel execution via TestNG
✅ Data-driven — JSON test data
✅ ThreadLocal driver — thread-safe parallel runs
✅ Retry analyzer — handles flakiness
✅ Extent Reports — HTML report with screenshots
✅ Dynamic email generation per test run
✅ Ad suppression for stable element detection
✅ Headless execution support
✅ Maven profiles — smoke and regression
```

---

## Project Structure

```
selenium-java-testng-framework/
│
├── src/
│   ├── main/java/
│   │   ├── pages/
│   │   │   ├── BasePage.java              # Common methods (click, sendKeys, wait)
│   │   │   ├── LandingPage.java           # Home page interactions
│   │   │   ├── HeaderPage.java            # Navigation menu interactions
│   │   │   ├── SignUpLoginPage.java        # Login and signup form
│   │   │   ├── RegisterationPage.java     # Registration form (full + mandatory)
│   │   │   ├── AccountCreatedPage.java    # Account created confirmation
│   │   │   ├── CartPage.java              # Shopping cart
│   │   │   ├── CheckoutPage.java          # Checkout page
│   │   │   ├── PaymentPage.java           # Payment details
│   │   │   ├── OrderPlacedPage.java       # Order confirmation
│   │   │   └── DeleteAccountPage.java     # Account deletion confirmation
│   │   │
│   │   └── utility/
│   │       └── DriverFactory.java         # Singleton ThreadLocal WebDriver
│   │
│   └── test/java/
│       ├── commontests/
│       │   ├── BaseTest.java              # Common setup, screenshot, quit
│       │   ├── RegressionBaseTest.java    # @BeforeMethod — new driver per test
│       │   └── SmokeBaseTest.java         # @BeforeClass — shared driver per class
│       │
│       ├── tests/
│       │   ├── SmokeTest.java             # Smoke test suite (sequential flow)
│       │   └── RegressionTest.java        # Regression test suite (independent tests)
│       │
│       ├── utility/
│       │   ├── Constants.java             # URLs, expected strings, test data
│       │   ├── DataKeys.java              # JSON key constants
│       │   ├── DataProviders.java         # TestNG DataProvider with JSON reader
│       │   ├── JsonReader.java            # JSON file reader utility
│       │   ├── TestDataHelper.java        # Helper to extract typed test data
│       │   ├── ExtentReporterUtil.java    # Extent report configuration
│       │   ├── Listeners.java             # TestNG listener for reporting
│       │   ├── Retry.java                 # Retry analyzer — ThreadLocal counter
│       │   └── RetryTransformer.java      # Applies retry to all tests
│       │
│       └── resource/
│           └── testData.json              # Test data for all tests
│
├── reports/
│   └── AutomationReport.html             # Latest Extent report
│
├── testng.xml                            # Smoke suite — Chrome + Edge parallel
├── latesttest.xml                        # Regression suite — Chrome + Edge parallel
└── pom.xml                               # Maven dependencies and profiles
```

---

## Framework Architecture

```
┌─────────────────────────────────────────────────────┐
│                    TEST LAYER                        │
│                                                      │
│   SmokeTest.java         RegressionTest.java         │
│        ↓                        ↓                    │
│   SmokeBaseTest          RegressionBaseTest          │
│  (@BeforeClass)          (@BeforeMethod)             │
│        └──────────┬─────────────┘                    │
│               BaseTest.java                          │
│          (screenshot, quit, reports)                 │
└─────────────────────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────┐
│                    PAGE LAYER                        │
│                                                      │
│   BasePage.java  ←  All page classes extend this     │
│   (click, sendKeys, wait, getText, getCurrentURL)    │
│                                                      │
│   LandingPage  HeaderPage  SignUpLoginPage           │
│   RegisterationPage  CartPage  CheckoutPage          │
│   PaymentPage  OrderPlacedPage  AccountCreatedPage   │
└─────────────────────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────┐
│                  UTILITY LAYER                       │
│                                                      │
│   DriverFactory    → Singleton + ThreadLocal driver  │
│   DataProviders    → JSON test data injection        │
│   Constants        → URLs and expected values        │
│   Retry            → ThreadLocal retry counter       │
│   Listeners        → Extent report integration       │
└─────────────────────────────────────────────────────┘
```

---

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 17 | Programming language |
| Selenium | 4.41.0 | Browser automation |
| TestNG | 7.12.0 | Test framework |
| WebDriverManager | 5.6.3 | Auto driver management |
| ExtentReports | 5.1.2 | HTML test reporting |
| Jackson Databind | 2.15.2 | JSON test data parsing |
| Maven | 3.x | Build and dependency management |
| Chrome | Latest | Primary test browser |
| Edge | Latest | Secondary test browser |

---

## Test Plan

### Application Under Test
**URL:** https://automationexercise.com  
**Type:** E-commerce web application  
**Test Scope:** Functional UI regression and smoke testing

### Test Approach

| Suite | Strategy | Driver Lifecycle | Execution |
|---|---|---|---|
| Smoke | Sequential priority-based flow | Shared per class (@BeforeClass) | Single session end-to-end |
| Regression | Independent tests | New driver per test (@BeforeMethod) | Parallel cross-browser |

### Test Data Strategy
```
Dynamic email   → Generated per run using System.currentTimeMillis()
                  Format: rachit_chrome_1714000000000@gmail.com
                  Prevents duplicate registration failures

Fixed email     → rachit@test.com (pre-registered account)
                  Used for login and existing email tests

JSON file       → testData.json contains all other test data
                  Name, password, address, card details etc.
```

---

## Smoke Test Suite

**File:** `tests/SmokeTest.java`  
**Config:** `testng.xml`  
**Browsers:** Chrome + Edge (parallel)  
**Strategy:** Sequential end-to-end user journey — `@BeforeClass` shared session

```
Complete E2E Flow:
Register → Login → Browse → Add to Cart → Checkout → Pay → Logout → Delete Account
```

| Priority | Test Name | Description | Expected Result |
|---|---|---|---|
| 0 | verifyHomePageLoaded | Verify homepage URL loads correctly | URL = automationexercise.com/ |
| 1 | verifyNavigationMenuItems | Verify all nav menu items present | All 8 menu items visible |
| 2 | verifyFeaturedProductsDisplayed | Verify products show name, price, image | Products visible with details |
| 3 | verifySubscriptionSectionVisible | Verify subscription section exists | Subscribe section visible |
| 4 | verifyNavigationToSignupLogin | Click signup/login and verify redirect | Signup form visible |
| 5 | verifyNewUserRegistration | Register new user with all details | account_created URL confirmed |
| 7 | verifyLoggedInUsernameVisible | Verify username shown after login | Username matches test data |
| 7 | verifyAddToCartFromHomePage | Add product to cart from homepage | "Added!" message shown |
| 8 | verifyProductsInCart | Verify correct product in cart | Product name matches |
| 9 | verifyProceedToCheckout | Click proceed to checkout | Checkout page title confirmed |
| 10 | verifyPaymentPage | Navigate to payment page | Payment page title confirmed |
| 11 | verifyOrderIsPlaced | Submit card details and place order | "ORDER PLACED!" message shown |
| 12 | verifyLogOut | Logout from application | Redirects to login page |
| 13 | verifyDeleteAccount | Login and delete account | Account deleted confirmation |

**Total Smoke Tests: 14** (run on 2 browsers = 28 executions)

---

## Regression Test Suite

**File:** `tests/RegressionTest.java`  
**Config:** `latesttest.xml`  
**Browsers:** Chrome + Edge (parallel)  
**Strategy:** Independent tests — `@BeforeMethod` creates fresh driver per test

| # | Test Name | Type | Description | Expected Result |
|---|---|---|---|---|
| 1 | verifyHomePageLoaded | Smoke | Verify homepage URL | URL = automationexercise.com/ |
| 2 | verifyNavigationMenuItems | Functional | All nav items present and correct | 8 items match expected list |
| 3 | verifyNewUserRegistration | Functional | Register with all form fields | account_created URL |
| 4 | verifyFeaturedProductsDisplayed | Functional | Products visible with name/price/image | All assertions pass |
| 5 | verifySubscriptionSectionVisible | Functional | Subscription section visible on homepage | isDisplayed = true |
| 6 | verifyNewsletterSubscription | Functional | Subscribe email to newsletter | Subscription confirmed |
| 7 | verifyNavigationToSignupLogin | Functional | Navigate to signup/login page | Signup form visible |
| 8 | verifyRegistrationWithExistingEmail | Negative | Register with already registered email | Error message shown |
| 9 | verifyMandatoryFieldsRegistration | Functional | Register with mandatory fields only | account_created URL |
| 10 | verifyAccountDeletion | Functional | Register and delete account | "ACCOUNT DELETED!" shown |
| 11 | verifyLoginWithValidCredentials | Functional | Login with valid email and password | Username shown in header |
| 12 | verifyLogoutFunctionality | Functional | Login then logout | Redirects to login page |
| 13 | verifyLogin | E2E | Login, add product, cart, checkout, pay | ORDER PLACED confirmed |

**Total Regression Tests: 13** (run on 2 browsers = 26 executions)

---

## How to Run

### Prerequisites
```bash
# Java 17 installed
java -version

# Maven installed
mvn -version

# Chrome and Edge browsers installed
```

### Clone Repository
```bash
git clone https://github.com/yourusername/selenium-java-testng-framework.git
cd selenium-java-testng-framework
```

### Run Smoke Tests
```bash
# Both browsers parallel
mvn test -P smoke

# Or directly
mvn test -DsuiteXmlFile=testng.xml
```

### Run Regression Tests
```bash
# Both browsers parallel
mvn test -P regression

# Or directly
mvn test -DsuiteXmlFile=latesttest.xml
```

### Run Headless
```bash
# Headless is configured in testng.xml
# Change headless parameter value:
# <parameter name="headless" value="true"/>
```

### Run Specific Browser Only
```bash
# Edit testng.xml — remove one <test> block
# OR pass parameter:
mvn test -Dbrowser=chrome
```

---

## Configuration

### testng.xml — Smoke Suite
```xml
<suite name="AutomationExercise Suite" parallel="tests" thread-count="3">
    <test name="Chrome Test">
        <parameter name="browser" value="chrome"/>
        <parameter name="headless" value="true"/>
        <classes>
            <class name="tests.SmokeTest"/>
        </classes>
    </test>
    <test name="Edge Test">
        <parameter name="browser" value="edge"/>
        <parameter name="headless" value="true"/>
        <classes>
            <class name="tests.SmokeTest"/>
        </classes>
    </test>
</suite>
```

### Test Data — testData.json
```json
{
    "testData": {
        "username": "Rachit Vashisht",
        "email": "rachit@test.com",
        "password": "Test@1234",
        "firstName": "Rachit",
        "lastName": "Vashisht",
        "addressLine1": "abc",
        "country": "India",
        "state": "Delhi",
        "city": "Delhi",
        "zipcode": "110085",
        "mobile": "1234567890",
        "productName": "Blue Top",
        "cardName": "Rachit Vashisht",
        "cardNumber": "1234567890",
        "cardCvc": "123",
        "cardMonth": "12",
        "cardYear": "2050"
    }
}
```

---

## Reports

### Extent Report
Reports are generated automatically after each test run.

**Location:** `reports/AutomationReport.html`

**Report includes:**
- Pass/Fail status per test
- Execution duration per test
- Browser and platform info
- Screenshot on failure (attached inline)
- Timeline view
- Exception details with stack trace
- Dashboard with charts

### Latest Results

| Suite | Browser | Passed | Failed | Skipped |
|---|---|---|---|---|
| Smoke | Chrome | 14 | 0 | 0 |
| Smoke | Edge | 14 | 0 | 0 |
| **Total** | **Both** | **28** | **0** | **0** |

---

## Key Design Decisions

### ThreadLocal Driver
```
Each thread gets its own WebDriver instance
→ Safe for parallel execution
→ No driver mixing between Chrome and Edge
→ DriverFactory.getWebDriver() always returns 
  correct driver for current thread
```

### Retry Analyzer
```
Handles test flakiness from:
→ Ad overlays blocking elements
→ Network latency on dynamic content
→ Page load timing variations

Config: maxCount = 1 (retries once only)
Only retries on: NoSuchElementException, TimeoutException
Does NOT retry: AssertionError (genuine failures)
```

### Dynamic Email
```java
// Generated per test run
BaseTest.email.set("rachit_" + browser + "_" 
    + System.currentTimeMillis() + "@gmail.com");

// Prevents duplicate registration failures
// Each run gets a unique email address
```

### Ad Suppression
```java
// hideAds() removes Google ad elements
// Called in @BeforeMethod before each test
// Prevents ads from blocking element interactions
```

---

## Author

**Rachit Vashisht**  
QA Automation Engineer  
Framework: Selenium + Java + TestNG  
Application: Automation Exercise (E-commerce)
