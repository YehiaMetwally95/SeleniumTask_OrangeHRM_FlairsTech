## Overveiw
- Test Automation Project for OrangeHRM with Selenium 4 written by Java and based on Maven
- Using My Own Test Automation Engine as Testing Framework, Check Engine Details https://github.com/YehiaMetwally95/YehiaEngine
- Using TestNG as the Testing Framework and Page Object Model as Design Pattern
- Using Fluent Page Object Model Design Pattern in writing Test script and Page actions, thus chaining the Scenario steps and validations in one line of code
- Test Data Management such that store All Test data in Json File for each Test Class
- Generating Very Detailed Allure Reports with All Scenario Steps And Screenshots for Passed/Hard-Assertion-Failed/Soft-Assertion-Failed Tests and for API Requests and Responses
- Perform Test Execution On Local / Headless / Remotely using Selenium Grid with Docker Containers
- Performing Parallel Execution from CI/CD Pipeline with GitHub Actions, Supplied by Selenium Grid with Docker, To Run Parallel Tests at same time, thus Reducing Execution time
- Implementing the Test Automation Pyramid such that Run Tests Over API besides the UI, Thus Reduce the full Dependancy on Element Identifications and Allow Data Validations on API, Thus Reduce the possibility of Script failure, Reduce execution time, Reduce dependency on all system features to be working and Provide better test coverage
- Using Dynamic Locators for Identifying Tabular data instead of complicated xpath expressions
- Bypassing UI Login, Thus Reducing Execution time "It doesn't work properly here as session expiry period is very short"

## Application Under Test
- https://opensource-demo.orangehrmlive.com/

## Features
#### Structure of "main folder"
- Using the HomePage as Parent of all pages that inherit locators and actions of Header & Footer from Homepage also for defining common variables that are commonly used across all pages, Thus achieving the right purpose of Inheritance
- Finding Elements using simple Techniques with ID, CSS Selectors & XPath & Advanced Techniques like XPath Axis and Relative Locators

#### Structure of "test folder"
- Using Base Test Class for defining Annotations to Open and Close App, such that all Test Classes inherit from it
- Start each Test from a clean state by Setting and Tearing down App for Every Test Case
- "Using Assertions as follows:
    - All Assertions are implemented in Page Class to allow the Fluency of Scenarios Steps with Validations like (Navigate.Writesteps..SoftAssertions.HardAssertions)
    - Using Hard assertions & Soft assertions for doing verifications within the test

#### Implementing Tests over API layer besides UI Tests to achieve the Automation Test Pyramid
- Login is done over GUI only once, then Bypass login for all next Tests
- Add New Employee and Validation on his Info are done over API, After that Create new User is done Over UI

#### Test Data Management
- Reading Global Variables and Configurations from Properties file, like selecting browser type, execution type, setting capabilities of every browser like headless execution
- Test Data Preparation for Static Data like "UserCredentials, Page Headers, ApiResponseCodes" by Filling it Manually on Json Files for every Test & User this data for Validations
- Test Data Generation for User Data like "NewUserInfo" using Time Stamp for Unique values & Data faker for more Descriptive values
- Test Data Execution by reading test data from Json files whether Json data is represend as Simple Json Object or Array of Json Objects

#### Create CI/CD Pipeline with GitHub Actions
- Workflow that run Tests Remotely In Parallel
- Workflow that run Tests Remotely Sequentially
- Workflow that run Tests Remotely Across Different Browsers In Parallel
- Run Workflows on Different Triggers: after every Push, after every Pull Request and Manually
- Generate the Allure Reports after every Workflow run such that
    - Separate Reports for Tests related to a Single Job
    - Combined Report that Include All Tests that runs on all Jobs

## Installation
##### 1- Docker Must be Installed and Run on your machine
##### 2- To Setup Selenium Grid with Docker Container in order to Run Tests Remotely, Just run the following command in Intellij Terminal
```bash
docker compose -f src/main/resources/dockerFiles/docker-compose-grid-v3.yml up --scale chrome=2 --scale edge=0 --scale firefox=0 -d 
```
##### 3- After The Setup, To be Able to Run Remotely, The flags "executionType" & "isHeadless" in Configuration.properties shall be set with "Remote" & "true"
##### 4- After Finish the Test Execution, Its Better to CleanUp and Stop running the Docker Containers by running the following command in Terminal
```bash
docker compose -f src/main/resources/dockerFiles/docker-compose-grid-v3.yml down
```  