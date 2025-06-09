package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Asli Ekmekci
 */
public class CartPage extends BasePage {
    public CartPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-test='continue-shopping']")
    private WebElement continueShoppingButton;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;


    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public void clickCheckoutButton() {
        waitAndClick(checkoutButton);

    }
    public boolean isProductInCart(String productName) {
        return driver.findElements(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']")).size() > 0;
    }

    public double getProductPrice(String productName) {
        WebElement product = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[contains(@class,'cart_item')]"));
        String priceText = product.findElement(By.className("inventory_item_price")).getText();
        return Double.parseDouble(priceText.replace("$", ""));
    }

    public boolean isRemoveButtonVisible(String productName) {
        WebElement product = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[contains(@class,'cart_item')]"));
        return product.findElement(By.xpath(".//button[contains(text(),'Remove')]")).isDisplayed();
    }

    public void clickRemoveButton(String productName) {
        WebElement product = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[contains(@class,'cart_item')]"));
        product.findElement(By.xpath(".//button[contains(text(),'Remove')]")).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.className("cart_item")).isEmpty();
    }

}
