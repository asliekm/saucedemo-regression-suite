package utilities;

import pages.CartPage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.ProductsPage;

import java.util.HashMap;
import java.util.Map;

public class ButtonActionManager {

    private final Map<String, Runnable> buttonActions = new HashMap<>();

    public ButtonActionManager(ProductsPage productsPage, CartPage cartPage, CheckoutInformationPage checkoutInformationPage, CheckoutOverviewPage checkoutOverviewPage) {

        buttonActions.put("cart", productsPage::clickCartButton);
        buttonActions.put("checkout", cartPage::clickCheckoutButton);
        buttonActions.put("continue", checkoutInformationPage::clickContinueButton);
        buttonActions.put("finish", checkoutOverviewPage::clickFinishButton);
    }

    public void clickButton(String buttonName) {
        Runnable action = buttonActions.get(buttonName.toLowerCase());
        if (action != null) {
            action.run();
        } else {
            throw new IllegalArgumentException("Unknown button: " + buttonName);
        }
    }
}

