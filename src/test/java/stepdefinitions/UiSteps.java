package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.data.ProductsData;
import org.junit.jupiter.api.Assertions;
import pages.*;
import utilities.ButtonActionManager;
import utilities.Constants;
import utilities.Driver;
import utilities.PropManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilities.Constants.ORDER_CONFIRMATION;

public class UiSteps extends BasePage {
    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();
    CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage();
    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();
    ButtonActionManager buttonActionManager = new ButtonActionManager(productsPage, cartPage, checkoutInformationPage, checkoutOverviewPage);

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

    @When("I fill in the checkout form with valid data")
    public void iFillInTheCheckoutFormWithValidData() {
        String firstName = PropManager.get("checkout.firstname");
        String lastName = PropManager.get("checkout.lastname");
        String zip = PropManager.get("checkout.zip");
        checkoutInformationPage.fillForm(firstName, lastName, zip);
    }

    @Then("I should see {string} in the product summary")
    public void i_should_see_product_in_summary(String productName) {
        boolean isVisible = checkoutOverviewPage.isProductVisible(productName);
        assertTrue(isVisible, productName + " is not visible in product summary!");
    }

    @Then("The payment information should be {string}")
    public void the_payment_information_should_be(String expectedPayment) {
        String actualPayment = checkoutOverviewPage.getPaymentInfo();
        Assertions.assertEquals(expectedPayment, actualPayment, "Payment info mismatch!");
    }

    @Then("The shipping information should be {string}")
    public void the_shipping_information_should_be(String expectedShipping) {
        String actualShipping = checkoutOverviewPage.getShippingInfo();
        Assertions.assertEquals(expectedShipping, actualShipping, "Shipping info mismatch!");
    }


    @Then("The price for {string} should match the product data")
    public void thePriceForShouldMatchTheProductData(String productName) {
        double expectedPrice = ProductsData.fromName(productName).getPrice();
        double actualPrice = checkoutOverviewPage.getProductPrice(productName);
        Assertions.assertEquals(expectedPrice, actualPrice, 0.01, productName + " price mismatch!");
    }

    @Then("I should see a confirmation message")
    public void i_should_see_a_confirmation_message() {
        String actualMessage = checkoutCompletePage.getConfirmationMessage();
        Assertions.assertEquals(ORDER_CONFIRMATION, actualMessage, "Confirmation message mismatch!");
    }

    @And("I go to the checkout overview page")
    public void iGoToTheCheckoutOverviewPage() {
        productsPage.clickCartButton();
        cartPage.clickCheckoutButton();
        String firstName = PropManager.get("checkout.firstname");
        String lastName = PropManager.get("checkout.lastname");
        String zip = PropManager.get("checkout.zip");
        checkoutInformationPage.fillForm(firstName, lastName, zip);
        checkoutInformationPage.clickContinueButton();
    }

    @Then("The item total should match the sum of product data")
    public void theItemTotalShouldMatchTheSumOfProductData() {
        List<Double> cartProductPrices = checkoutOverviewPage.getCartProductPrices();

        double expectedItemTotal = cartProductPrices.stream().mapToDouble(Double::doubleValue).sum();

        double actualItemTotal = checkoutOverviewPage.getDisplayedItemTotal();

        Assertions.assertEquals(expectedItemTotal, actualItemTotal, 0.01, "Item total mismatch!");
    }

    @And("The tax should be calculated correctly")
    public void theTaxShouldBeCalculatedCorrectly() {
        double itemTotal = checkoutOverviewPage.getDisplayedItemTotal();

        double expectedTax = Math.round(itemTotal * Constants.TAX_RATE * 100.0) / 100.0;

        double actualTax = checkoutOverviewPage.getDisplayedTax();

        Assertions.assertEquals(expectedTax, actualTax, 0.01, "Tax calculation mismatch!");
    }

    @And("The total price should match the sum of product data and tax")
    public void theTotalPriceShouldMatchTheSumOfProductDataAndTax() {
        double itemTotal = checkoutOverviewPage.getDisplayedItemTotal();
        double tax = checkoutOverviewPage.getDisplayedTax();
        double expectedTotal = itemTotal + tax;
        double actualTotal = checkoutOverviewPage.getDisplayedTotalPrice();
        Assertions.assertEquals(expectedTotal, actualTotal, 0.01, "Total price mismatch!");
    }


    @Given("I am logged in as a standard user")
    public void iAmLoggedInAsAStandardUser() {
        loginPage.navigateToLoginPage(PropManager.get("baseUrl"));
        String username = PropManager.get("user");
        String password = PropManager.get("password");
        loginPage.login(username, password);
    }

    @Then("I should see the {string} error message")
    public void iShouldSeeTheErrorMessage(String errorType) {
        String actualError = loginPage.getLoginErrorMessage();
        String expectedError = switch (errorType) {
            case "locked out user" -> Constants.LOCKED_OUT_ERROR;
            case "invalid credentials" -> Constants.INVALID_CREDENTIALS_ERROR;
            case "username required" -> Constants.USERNAME_REQUIRED_ERROR;
            case "password required" -> Constants.PASSWORD_REQUIRED_ERROR;
            default -> throw new IllegalArgumentException("Unknown error type: " + errorType);
        };

        Assertions.assertEquals(expectedError, actualError, "Error message mismatch!");
    }


    @Then("I should see {string} in the cart")
    public void iShouldSeeInTheCart(String productName) {
        Assertions.assertTrue(cartPage.isProductInCart(productName),
                "Product not found in cart: " + productName);
    }

    @And("The price for {string} should be displayed correctly")
    public void thePriceForShouldBeDisplayedCorrectly(String productName) {
        double expectedPrice = ProductsData.getProductPrice(productName);
        double actualPrice = cartPage.getProductPrice(productName);
        Assertions.assertEquals(expectedPrice, actualPrice, 0.01, "Product price mismatch!");
    }

    @And("The \"Remove\" button should be visible for {string}")
    public void theRemoveButtonShouldBeVisibleFor(String productName) {
        Assertions.assertTrue(cartPage.isRemoveButtonVisible(productName),
                "Remove button is not visible for: " + productName);
    }

    @And("I remove the product {string} from the cart")
    public void iRemoveTheProductFromTheCart(String productName) {
        cartPage.clickRemoveButton(productName);
    }

    @Then("The cart should be empty")
    public void theCartShouldBeEmpty() {
        Assertions.assertTrue(cartPage.isCartEmpty(), "Cart is not empty!");
    }

    @When("I remove {string} from my cart via the products page")
    public void iRemoveFromMyCartViaTheProductsPage(String productName) {
        productsPage.clickRemoveButton(productName);
    }
};

