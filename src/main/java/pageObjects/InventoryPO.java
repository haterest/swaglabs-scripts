package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.InventoryUI;

public class InventoryPO extends BasePage {
    public InventoryPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public ItemDetailPO clickToProductLinkByName(String productName) {
        waitForElementClickable(InventoryUI.PRODUCT_LINK_BY_NAME, productName);
        clickToElement(InventoryUI.PRODUCT_LINK_BY_NAME, productName);
        return PageObjectManager.getItemDetailPage(driver);
    }

    public void clickAddToCartButtonByName(String productName) {
        waitForElementClickable(InventoryUI.ADD_TO_CART_BUTTON_BY_NAME, productName);
        clickToElement(InventoryUI.ADD_TO_CART_BUTTON_BY_NAME, productName);
    }
}
