# CuraHealth Automation Framework

## 📌 Project Overview

This framework automates test scenarios for the Cura Healthcare demo application using Selenium WebDriver with a structured Page Object Model (POM) design.

It ensures scalability, maintainability, and reusability by separating test logic, page actions, and utilities.

---

## 🎯 Objectives

- Implement Selenium WebDriver automation
- Follow Page Object Model (POM)
- Use TestNG for execution
- Implement Data-Driven Testing using Excel
- Capture screenshots on failure
- Generate Extent Reports
- Maintain clean and reusable code

---

## ⚙️ Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- Extent Reports

---

## 📁 Project Structure

```
CuraHealth_Automation/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       │   └── BasePage.java -> Common reusable page methods (driver, waits, actions)
│   │       │
│   │       ├── pages/
│   │       │   ├── HistoryPage.java -> Handles appointment history page actions and validations
│   │       │   ├── HomePage.java -> Manages homepage navigation and interactions
│   │       │   └── LoginPage.java -> Contains login functionality and validations
│   │       │
│   │       └── utils/
│   │           ├── ConfigReader.java -> Reads values from config.properties file
│   │           ├── ExcelUtil.java -> Utility to read test data from Excel files
│   │           ├── ExtentManager.java -> Initializes and configures Extent Reports
│   │           ├── ExtentTestManager.java -> Manages test logs for reporting
│   │           └── ScreenshotUtil.java -> Captures screenshots on test execution/failure
│   │
│   ├── test/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java -> Sets up WebDriver, browser config, and test initialization
│   │   │   │
│   │   │   ├── listeners/
│   │   │   │   └── TestListener.java -> TestNG listener for logging, reporting, screenshots
│   │   │   │
│   │   │   └── tests/
│   │   │       ├── AppointmentBookingTest.java -> Tests for booking appointments
│   │   │       ├── AppointmentHistoryTest.java -> Verifies appointment history functionality
│   │   │       ├── FormValidationsTest.java -> Validates form input fields and error messages
│   │   │       ├── LoginTest.java -> Covers login scenarios (valid & invalid)
│   │   │       └── MultipleAppointmentsTest.java -> Tests multiple appointment booking flows
│   │   │
│   │   └── resources/
│   │       ├── config.properties -> Configuration file (URL, browser, credentials)
│   │       └── testdata/
│   │           └── LoginData.xlsx -> Stores login test data
│
├── reports/ -> Stores generated Extent Reports
├── screenshots/ -> Stores captured screenshots during test execution
│
├── pom.xml -> Maven project configuration and dependencies
└── testng.xml -> TestNG suite configuration file
```

---

## 🧪 Test Modules Covered

### 1. User Authentication
- Verify successful login with valid credentials
- Verify login failure with invalid credentials and validate error messages
- Verify logout functionality
- Validate protected page access

### 2. Appointment Booking
- Select facility, date, and comment
- Verify confirmation details
- Apply for hospital admission
- Reject past dates

### 3. Appointment History
- Verify history page loads
- Validate latest appointment details
- Verify history content

### 4. Multiple Appointments
- Book multiple appointments
- Verify entries in history
- Validate sorting by date

### 5. Form Validations
- Empty field validation
- Invalid input validation
- Long text handling

---

## ⚠️ Known Observations / Limitations

The following test scenarios were implemented, but the current application behavior does not fully support the expected validation:

### 1. Verify appointment date field rejects past dates
- **Expected:** System should not allow selecting or submitting past dates  
- **Actual:** Application accepts past dates without validation  
- **Handling in Framework:** Logged as `WARNING` in Extent Report using `test.warning()` instead of failing the test

---

### 2. Verify appointments are sorted by date in the history view
- **Expected:** Appointments should be displayed in chronological order  
- **Actual:** No sorting functionality is implemented in the application  
- **Handling in Framework:** Logged as `WARNING` in Extent Report using `test.warning()` to highlight missing feature

---

### 📌 Note
These are not automation failures but application limitations.  
Using `WARNING` helps distinguish:
- ❌ Actual test failures (defects in implemented features)  
- ⚠️ Missing validations or features in the application  

---

## ▶️ How to Run

### Maven

```bash
mvn test
```

### TestNG

Right click → `testng.xml` → Run

---

## ⚙️ Configuration

```properties
browser=chrome
baseUrl=https://katalon-demo-cura.herokuapp.com/
timeout=10
username=John Doe
password=ThisIsNotAPassword
```

---

## 📊 Reports

* Extent → `/reports/`
* Screenshots → `/screenshots/`

---

## 👤 Author

Vaishnavi

---

## 📌 Conclusion

This project demonstrates a robust and scalable test automation framework built using Selenium WebDriver, Java, TestNG, and Maven.  By following the Page Object Model and best practices like reusable components, proper test separation, it ensures maintainability and reliability.
---
