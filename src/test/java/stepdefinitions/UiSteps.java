package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import pages.BasePage;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.ButtonActionManager;
import utilities.Driver;
import utilities.PropManager;

public class UiSteps extends BasePage {
    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();
    ButtonActionManager buttonActionManager = new ButtonActionManager(productsPage, cartPage, loginPage);

    @Step("Navigate to login page: {baseUrl}")
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage.navigateToLoginPage(PropManager.get("baseUrl"));
    }

    @Step("Enter username: {username} and password: {password}")
    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Step("Verify user is on the '{pageName}' page")
    @Then("I should see the {string} page")
    public void i_should_see_the_page(String pageName) {
        String expectedUrl = getExpectedUrlForPage(pageName);
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl, "User should land on the " + pageName + " page");
    }

    @Step("Verify error message is displayed")
    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        Assertions.assertTrue(loginPage.isLoginErrorDisplayed(), "Expected error message was not displayed");
    }

    @Step("Verify error message text: {expectedMessage}")
    @Then("I should see the following error message:")
    public void i_should_see_the_following_error_message(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessageText();
        Assertions.assertEquals(expectedMessage.trim(), actualMessage.trim(), "Error message text mismatch");
    }

    @Step("Verify number of products displayed is {expected}")
    @Then("I should see the following number of products:")
    public void i_should_see_the_following_number_of_products(DataTable table) {
        int expected = table.asMaps().stream().mapToInt(row -> Integer.parseInt(row.get("count"))).findFirst().orElseThrow(() -> new IllegalArgumentException("Missing 'count' column"));
        productsPage.waitForProductsToBeVisible();
        int actual = productsPage.getAllProductCards().size();
        Assertions.assertEquals(expected, actual, "Product count mismatch");
    }

    @Step("Add product to cart: {product}")
    @When("I add the product {string} to the cart")
    public void i_add_the_product_to_the_cart(String product) {
        productsPage.addToCart(product);
    }

    @Step("Remove product from cart: {product}")
    @When("I remove the product {string} from the cart")
    public void i_remove_the_product_from_the_cart(String product) {
        productsPage.removeFromCart(product);
    }

    @Step("Cart icon should show: {expectedCount}")
    @Then("the cart icon should show {string}")
    public void the_cart_icon_should_show(String expectedCount) {
        String actual = productsPage.getCartBadgeCount();
        Assertions.assertEquals(expectedCount, actual, "Cart badge count mismatch");
    }

    @Step("Add the following products to the cart: {products}")
    @When("I add the following products to the cart:")
    public void i_add_the_following_products_to_the_cart(DataTable products) {
        products.asList().forEach(productsPage::addToCart);
    }

    @Step("Click the '{buttonName}' button")
    @When("I click the {string} button")
    public void i_click_the_button(String buttonName) {
        buttonActionManager.clickButton(buttonName);
    }


}
