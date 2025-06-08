package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    public CheckoutPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement zipCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public void fillForm(String firstName, String lastName, String zip) {
        waitForVisibility(firstNameInput);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        zipCodeInput.clear();
        zipCodeInput.sendKeys(zip);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public void clickContinueButton() {
        waitAndClick(continueButton);
    }
}
