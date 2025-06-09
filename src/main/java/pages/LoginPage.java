package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Asli Ekmekci
 */

public class LoginPage extends BasePage {

    @FindBy(css = "[data-test='username']")
    private WebElement usernameInput;
    @FindBy(css = "[data-test='password']")
    private WebElement passwordInput;
    @FindBy(css = "[data-test='login-button']")
    private WebElement loginButton;
    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage(String url) {
        driver.get(url);
    }

    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        waitAndClick(loginButton);
    }

    public boolean isLoginErrorDisplayed() {
        return isElementVisible(errorMessage);
    }

    public String getLoginErrorMessage() {
        WebElement errorElement = driver.findElement(By.cssSelector("h3[data-test='error']"));
        return errorElement.getText();
    }

}
