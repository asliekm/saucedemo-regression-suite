# Saucedemo Regression Suite

This project contains automated regression tests for [SauceDemo](https://www.saucedemo.com/).
Tests are written in Java using Cucumber and Selenium WebDriver.
Allure is used for rich test reporting.

## Features

- Comprehensive tests for login, product, cart, and checkout flows
- Run regression suite with the `@regression` tag
- Rich reporting with Allure
- CI/CD integration with GitHub Actions

## Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/asliekm/saucedemo-regression-suite.git
   cd saucedemo-regression-suite
   ```

2. **Install dependencies:**
   ```sh
   mvn clean install
   ```

## Running Tests

- **To run all tests:**
  ```sh
  mvn test
  ```

- **To run only the regression suite:**
  ```sh
  mvn test -Dcucumber.filter.tags="@regression"
  ```

## 📊 Allure Report

1. After running tests:
   ```sh
   allure serve target/allure-results
   ```
   or
   ```sh
   allure generate target/allure-results -o target/allure-report --clean
   allure open target/allure-report
   ```

You can view the latest Allure test report online here:  
👉 [https://asliekm.github.io/saucedemo-regression-suite/](https://asliekm.github.io/saucedemo-regression-suite/)


> If the report does not display correctly, please refresh the page or try a different browser.
![Test Result 1](https://github.com/asliekm/saucedemo-regression-suite/blob/dev/images/%7B41E93C20-909A-4D0D-847A-EB61AECEE64C%7D.png?raw=true)


![Test Result 2](https://github.com/asliekm/saucedemo-regression-suite/blob/dev/images/%7B214C1CFB-E233-4A23-B23E-2727BBEE7A51%7D.png?raw=true)
## CI/CD

- Tests run automatically on every push and pull request, and an Allure report is generated.
- After downloading the report, open it with a local server:
  ```sh
  cd allure-report
  python -m http.server 8000
  # Then go to http://localhost:8000/index.html in your browser
  ```

## License

MIT License
