package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.ShoppingCartUI;

public class ShoppingCartPO extends BasePage {
    public ShoppingCartPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isDisplayedProductInCartByName(String productName) {
        waitForElementVisible(ShoppingCartUI.PRODUCT_BY_NAME, productName);
        return isElementDisplayed(ShoppingCartUI.PRODUCT_BY_NAME, productName);
    }

    public String getProductInforByName(String productName) {
        waitForElementVisible(ShoppingCartUI.PRODUCT_DESC_BY_NAME, productName);
        return getElementText(ShoppingCartUI.PRODUCT_DESC_BY_NAME, productName);
    }

    public String getProductPriceByName(String productName) {
        waitForElementVisible(ShoppingCartUI.PRODUCT_PRICE_BY_NAME, productName);
        return getElementText(ShoppingCartUI.PRODUCT_PRICE_BY_NAME, productName);
    }

    public void clickRemoveButtonByName(String productName) {
        waitForElementClickable(ShoppingCartUI.REMOVE_BUTTON_BY_NAME, productName);
        clickToElement(ShoppingCartUI.REMOVE_BUTTON_BY_NAME, productName);
    }

    public boolean isUndisplayedProductInCartByName(String productName) {
        return isElementUndisplayed(ShoppingCartUI.PRODUCT_BY_NAME, productName);
    }

    public CheckoutPO clickCheckoutButton() {
        waitForElementClickable(ShoppingCartUI.CHECKOUT_BUTTON);
        clickToElement(ShoppingCartUI.CHECKOUT_BUTTON);
        return PageObjectManager.getCheckoutPage(driver);
    }
}
