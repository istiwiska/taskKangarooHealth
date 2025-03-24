Kangaroo Health Automation Testing

Overview

This project is an automated testing task for Kangaroo Health, developed using Selenium and Cucumber frameworks. The project is fully integrated with GitHub Actions for continuous integration and automated test execution.

Features

- Automated UI testing using Selenium WebDriver and Cucumber.

- Continuous Integration (CI) with GitHub Actions.

- Auto-generated test reports using Cucumber Report and Extent Report.

- Automated email notifications upon test completion.


Prerequisites

- Ensure you have the following installed before running the project:

- Java (JDK 11 or higher)

- Maven

- Selenium WebDriver

- Cucumber

- Extent Reports

- Git

Setup Instructions

1. Clone the repository:

    git clone https://github.com/istiwiska/taskKangarooHealth.git
    cd kangaroo-health-automation

2. Install dependencies:
   mvn clean install

3. Run tests locally: mvn test

GitHub Actions Integration

- The project is configured to run tests automatically via GitHub Actions.

- Test results are generated and uploaded as artifacts.

- If any test fails, an automated email notification is sent.

Reports & Logs

- After execution, reports are auto-generated:

    Cucumber Report: target/cucumber-reports/index.html

    Extent Report: reports/TestReport.html

- Email notifications include a summary of test execution.

For more details, check out the test execution video recordings.
