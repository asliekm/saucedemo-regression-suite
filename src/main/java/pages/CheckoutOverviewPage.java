package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {
    public CheckoutOverviewPage() {
        super();
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "finish")
    private WebElement finishButton;

    public boolean isProductVisible(String productName) {
        try {
            WebElement element = driver.findElement(By.xpath(
                    "//div[@class='inventory_item_name' and text()='" + productName + "']"
            ));
            return isElementVisible(element);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public double getProductPrice(String productName) {
        String xpath = "//div[@class='cart_item'][.//div[@class='inventory_item_name' and text()='" + productName + "']]//div[@class='inventory_item_price']";
        WebElement priceElement = driver.findElement(By.xpath(xpath));
        String priceText = priceElement.getText();
        return Double.parseDouble(priceText.replace("$", ""));
    }

    public double getItemTotal() {
        List<WebElement> priceElements = driver.findElements(By.cssSelector("div.inventory_item_price"));
        double total = 0.0;
        for (WebElement priceEl : priceElements) {
            String priceText = priceEl.getText();
            total += Double.parseDouble(priceText.replace("$", ""));
        }
        return total;
    }


    public String getPaymentInfo() {
        String xpath = "//div[text()='Payment Information:']/following-sibling::div[@class='summary_value_label']";
        WebElement paymentElement = driver.findElement(By.xpath(xpath));
        return paymentElement.getText();
    }

    public String getShippingInfo() {
        String xpath = "//div[text()='Shipping Information:']/following-sibling::div[@class='summary_value_label']";
        WebElement shippingElement = driver.findElement(By.xpath(xpath));
        return shippingElement.getText();
    }

    public void clickFinishButton() {
        waitAndClick(finishButton);
    }

    public List<Double> getCartProductPrices() {
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".cart_item .inventory_item_price"));
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElem : priceElements) {
            prices.add(parsePrice(priceElem.getText()));
        }
        return prices;
    }

    public double getDisplayedItemTotal() {
        String text = driver.findElement(By.cssSelector(".summary_subtotal_label")).getText();
        return parsePrice(text);
    }

    public double getDisplayedTax() {
        String text = driver.findElement(By.cssSelector(".summary_tax_label")).getText();
        return parsePrice(text);
    }

    public double getDisplayedTotalPrice() {
        String text = driver.findElement(By.cssSelector(".summary_total_label")).getText();
        return parsePrice(text);
    }

    private double parsePrice(String text) {
        String num = text.replaceAll("[^\\d.]", "");
        return Double.parseDouble(num);
    }

}
