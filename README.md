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

## Allure Report

1. After running tests:
   ```sh
   allure serve target/allure-results
   ```
   or
   ```sh
   allure generate target/allure-results -o target/allure-report --clean
   allure open target/allure-report
   ```

## CI/CD

- Tests run automatically on every push and pull request, and an Allure report is generated.
- After downloading the report, open it with a local server:
  ```sh
  cd allure-report
  python -m http.server 8000
  # Then go to http://localhost:8000/index.html in your browser
  ```

## Contributing

Feel free to fork the repo and submit pull requests.

## License

MIT License
