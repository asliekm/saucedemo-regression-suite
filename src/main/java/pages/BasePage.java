package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.PropManager;

import java.time.Duration;
import java.util.List;

/**
 * Created by Asli Ekmekci
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = Driver.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
        element.click();
    }

    public void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element != null && element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }


    public String getElementText(WebElement element) {
        try {
            return element.getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public void waitUntilAllVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForVisibility(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibility(WebElement element) {
        waitForVisibility(element, 10);
    }

    public String getExpectedUrlForPage(String pageName) {
        switch (pageName.trim().toLowerCase()) {
            case "products":
                return PropManager.get("products");
            case "cart":
                return PropManager.get("cart");
            case "login":
                return PropManager.get("login");
            case "checkout: your information":
                return PropManager.get("checkout_your_information");
            case "checkout: overview":
                return PropManager.get("checkout_overview");
            case "checkout: complete":
                return PropManager.get("checkout_complete");
            default:
                throw new IllegalArgumentException("Unknown page: " + pageName);
        }
    }


}
