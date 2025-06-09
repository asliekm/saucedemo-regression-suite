package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BasePage{
    public CheckoutCompletePage() {
        super();
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".complete-header")
    private WebElement confirmationMessage;

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }
}
