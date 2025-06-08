package pages;

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

}
