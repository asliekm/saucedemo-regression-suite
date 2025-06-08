package utilities;

import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.HashMap;
import java.util.Map;

public class ButtonActionManager {

    private final Map<String, Runnable> buttonActions = new HashMap<>();

    public ButtonActionManager(ProductsPage productsPage, CartPage cartPage, LoginPage loginPage) {

        buttonActions.put("cart", productsPage::clickCartButton);
        buttonActions.put("checkout", cartPage::clickCheckoutButton);

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

