package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import utilities.PropManager;

/**
 * Created by Asli Ekmekci
 */
@Epic("Login Feature")
@Feature("Positive Login")
public class LoginSteps {

    private LoginPage loginPage;

    @Step("Navigate to login page")
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage = new LoginPage();
        loginPage.navigateToLoginPage(PropManager.get("baseUrl"));
    }

    @Step("Enter username: {username} and password: {password}")
    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Step("Verify user is on the products page")
    @Then("I should see the products page")
    public void i_should_see_the_products_page() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = loginPage.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl, "User should land on the products page");
    }

    @Step("Verify error message is displayed")
    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        Assertions.assertTrue(loginPage.isLoginErrorDisplayed(), "Expected error message was not displayed");
    }

    @Step("Verify specific error message: {expectedMessage}")
    @Then("I should see the following error message:")
    public void i_should_see_the_following_error_message(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessageText();
        Assertions.assertEquals(expectedMessage.trim(), actualMessage.trim(), "Error message text mismatch");
    }
}
