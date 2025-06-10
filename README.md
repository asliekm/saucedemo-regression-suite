Saucedemo Regression Suite
Automated regression testing suite for SauceDemo, built with Java, Selenium WebDriver, and Cucumber.
Allure provides comprehensive reporting. GitHub Actions handles CI/CD.

🚀 Features
End-to-end coverage: login, product catalog, cart, and checkout flows

Cucumber-based BDD scenarios with @regression tagging

Detailed Allure reports with test execution insights

CI/CD integration via GitHub Actions

🛠️ Getting Started
1. Clone the repository
bash
Kopieren
Bearbeiten
git clone https://github.com/asliekm/saucedemo-regression-suite.git
cd saucedemo-regression-suite
2. Install dependencies
nginx
Kopieren
Bearbeiten
mvn clean install
✅ Running Tests
Run all tests
bash
Kopieren
Bearbeiten
mvn test
Run only regression suite
nginx
Kopieren
Bearbeiten
mvn test -Dcucumber.filter.tags="@regression"
📊 Allure Reporting
Generate and view report
Option 1 (serve report automatically):

bash
Kopieren
Bearbeiten
allure serve target/allure-results
Option 2 (generate and open manually):

bash
Kopieren
Bearbeiten
allure generate target/allure-results -o target/allure-report --clean
allure open target/allure-report
⚙️ CI/CD Pipeline
Automated tests run on every push and pull request

Allure report is generated as a CI artifact

To view the downloaded report locally:

bash
Kopieren
Bearbeiten
cd allure-report
python -m http.server 8000
Then visit: http://localhost:8000/index.html

📄 License
This project is licensed under the MIT License.
