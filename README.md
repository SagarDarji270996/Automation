
# TASKEYE WEB AUTOMATION

This project is a Test Automation Framework for the Taskeye web application. The framework utilizes Java, Selenium, and TestNG for automating test cases, and Allure Reports for reporting test results.

## Project Structure

The project follows a modular structure for better maintainability and scalability. Below is an overview of the project structure:

```bash

UffizioAutomation/
├── .idea/
├── extent-reports/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   ├── TestBase.java
│   │   │   ├── common/
│   │   │   │   ├── CommonMethods.java
│   │   │   │   ├── DatePickerUtils.java
│   │   │   │   ├── IframesOfApplication.java
│   │   │   │   ├── MobileActions.java
│   │   │   │   ├── MobileBrowserWait.java
│   │   │   │   ├── Utils.java
│   │   │   │   ├── WaitUtils.java
│   │   │   ├── config/
│   │   │   │   ├── ConfigLoader.java
│   │   │   ├── drivers/
│   │   │   │   ├── chromedriver
│   │   │   ├── apps/
│   │   │   │   ├── taskeye-debug.apk
│   │   │   ├── projects/
│   │   │   │   ├── taskeye/
│   │   │   │   │   ├── mobile/
│   │   │   │   │   │   ├── locators/
│   │   │   │   │   │   │   ├── MobileLoginPageLocators.java
│   │   │   │   │   │   ├── pages/
│   │   │   │   │   │       ├── MobileLoginPage.java
│   │   │   │   │   ├── web/
│   │   │   │   │       ├── locators/
│   │   │   │   │       │   ├── AdminPageLocators.java
│   │   │   │   │       ├── pages/
│   │   │   │   │           ├── AdminPage.java
│   │   │   │   ├── trakzee/
│   │   │   │   │   ├── web/
│   │   │   │   │       ├── locators/
│   │   │   │   │       │   ├── HomePageLocators.java
│   │   │   │   │       ├── pages/
│   │   │   │   │           ├── HomePage.java
│   │   │   │   ├── smartbus/
│   │   │   │       ├── web/
│   │   │   │           ├── locators/
│   │   │   │           │   ├── LoginPageLocators.java
│   │   │   │           ├── pages/
│   │   │   │               ├── LoginPage.java
│   │   ├── resources/
│       ├── config.properties
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── listeners/
│   │   │   │   ├── CustomTestListener.java
│   │   │   ├── projects/
│   │   │       ├── taskeye/
│   │   │       │   ├── tests/
│   │   │       │       ├── AdminTest.java
│   │   │       ├── trakzee/
│   │   │       │   ├── tests/
│   │   │       │       │   ├── LoginTest.java
│   │   │       ├── smartbus/
│   │   │           ├── tests/
│   │   │               ├── LoginTest.java
├── target/
├── pom.xml
├── README.md
├── smartbus-testng.xml
├── taskeye-testng.xml
├── trakzee-testng.xml



```
## Technology Stack

- Programming Language: Java

- Automation Tools: Selenium, TestNG

- Reporting Tool: Allure Reports

- Build Tool: Maven

- Browser Support: Chrome (configurable)


## Pre-requisites

  1. Java Development Kit (JDK): Version 11 or higher.

  2. Maven: Ensure Maven is installed and configured in your system.

  3. Google Chrome and ChromeDriver (compatible version).

  4. Allure CLI: Install Allure for generating reports.

To install Allure:

- Linux (Ubuntu):

      sudo apt-add-repository ppa:qameta/allure
      sudo apt-get update
      sudo apt-get install allure
  
- Verify Installation:

      allure --version
    
5. TestNG Plugin: Install TestNG for IDE (Eclipse/IntelliJ) for easier execution.
  

## Configuration Setup

- Update the config.properties file located at:

      src/test/java/resources/config.properties

- Sample config.properties:

      superAdminUserName=admin
      superAdminPassword=Test@123
      websiteUrl=https://example.com/admin


## Test Execution

You can execute the test cases via Maven commands or directly from the IDE.

### 1. Execute All Test Cases
Run the following Maven command to execute all test cases:

      mvn clean test

### 2. Execute a Specific Test Class
To run a specific test class (e.g., AdminTest.java):

      mvn clean test -Dtest=AdminTest

### 3. Execute a Specific Test Method
To execute a single test method (e.g., testCreateAdminSuccessfully), use:

      mvn clean test -Dtest=AdminTest#testCreateAdminSuccessfully


## Generating Allure Reports

### Step 1: Execute Test Cases
Before generating reports, ensure the tests are executed using Maven:
      
      mvn clean test

### Step 2: Generate Allure Report
Run the following command to generate the Allure report:

      allure generate target/allure-results --clean -o allure-report

- target/allure-results - Contains the raw test results after execution.
- allure-report - Directory where the generated HTML report will be stored.

### Step 3: Serve Allure Report
To view the report in your browser:

      allure serve target/allure-results
This command will launch the report on a local server and open it in the default web browser.

## Additional Notes

### 1. Screenshots:
Screenshots for failed tests will be saved in the screenshots folder under the project root.

### 2. Listeners:
The project uses a custom TestNG listener (CustomTestListener) to capture test lifecycle events and attach screenshots for failed tests.

### 3. Allure Integration:
Annotations like @Feature, @Story, and @Description are used in test classes for enhanced reporting in Allure.

