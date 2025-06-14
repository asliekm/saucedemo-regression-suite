package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Constants;

import java.time.Duration;
import java.util.List;

/**
 * Created by Asli Ekmekci
 */

public class ProductsPage extends BasePage {

    @FindBy(css = "div.inventory_item")
    private List<WebElement> productCards;
    @FindBy(css = "[data-test='shopping-cart-badge']")
    private WebElement cartBadge;
    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement cartIcon;

    public ProductsPage() {
        super();
        PageFactory.initElements(driver, this);
    }


    public List<WebElement> getAllProductCards() {
        return productCards;
    }

    public void addToCart(String productName) {
        for (WebElement card : productCards) {
            List<WebElement> nameElements = card.findElements(By.cssSelector("[data-test='inventory-item-name']"));
            if (nameElements.isEmpty()) continue;

            WebElement nameElement = nameElements.get(0);
            String actualName = nameElement.getText().trim();
            
            if (actualName.equalsIgnoreCase(productName.trim())) {
                WebElement addButton = card.findElement(By.cssSelector("button"));
                waitAndClick(addButton);
                break;
            }
        }
    }

    public boolean hasAttribute(WebElement product, String attributeName) {
        return switch (attributeName.toLowerCase()) {
            case "title" -> !product.findElements(By.cssSelector("[data-test='inventory-item-name']")).isEmpty();
            case "price" -> !product.findElements(By.cssSelector("[data-test='inventory-item-price']")).isEmpty();
            case "addtocartbutton" ->
                    !product.findElements(By.cssSelector("button[data-test^='add-to-cart']")).isEmpty();
            default -> false;
        };
    }


    public void removeFromCart(String productName) {
        for (WebElement card : productCards) {
            WebElement nameElement = card.findElement(org.openqa.selenium.By.cssSelector(".inventory_item_name"));
            if (nameElement.getText().trim().equalsIgnoreCase(productName.trim())) {
                WebElement removeButton = card.findElement(org.openqa.selenium.By.xpath(".//button[contains(text(),'Remove')]"));
                waitAndClick(removeButton);
                break;
            }
        }
    }

    public void waitForProductsToBeVisible() {
        waitUntilAllVisible(productCards);
    }

    /**
     * Returns cart badge count as string, or "0" if not displayed
     */
    public String getCartBadgeCount() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return shortWait.until(driver -> cartBadge.isDisplayed() ? cartBadge.getText() : null);
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCartButton() {
        waitAndClick(cartIcon);
    }

    private String toIdFormat(String productName) {
        return productName.toLowerCase().replaceAll(" ", "-");
    }

    public void clickRemoveButton(String productName) {
        String dataTest = String.format(Constants.REMOVE_BUTTON_DATA_TEST_PATTERN, toIdFormat(productName));
        WebElement removeButton = driver.findElement(By.cssSelector("button[data-test='" + dataTest + "']"));
        waitAndClick(removeButton);
    }
}
