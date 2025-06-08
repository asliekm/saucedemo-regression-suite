package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import pages.ProductsPage;

/**
 * Created by Asli Ekmekci
 */
@Epic("Product Management")
@Feature("Cart Operations")
public class ProductsSteps {

    ProductsPage productsPage = new ProductsPage();

    @Step("Verify number of products displayed")
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

    @When("I remove the product {string} from the cart")
    public void i_remove_the_product_from_the_cart(String product) {
        productsPage.removeFromCart(product);
    }

    @Then("the cart icon should show {string}")
    public void the_cart_icon_should_show(String expectedCount) {
        String actual = productsPage.getCartBadgeCount();
        Assertions.assertEquals(expectedCount, actual, "Cart badge count mismatch");
    }

    @When("I add the following products to the cart:")
    public void i_add_the_following_products_to_the_cart(DataTable products) {
        products.asList().forEach(productsPage::addToCart);
    }
}
