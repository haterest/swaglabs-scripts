package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.ItemDetailUI;

public class ItemDetailPO extends BasePage {
    public ItemDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickAddToCartButton() {
        waitForElementClickable(ItemDetailUI.ADD_TO_CART_BUTTON);
        clickToElement(ItemDetailUI.ADD_TO_CART_BUTTON);
    }

    public String getProductInfor() {
        waitForElementVisible(ItemDetailUI.PRODUCT_DESCRIPTION);
        return getElementText(ItemDetailUI.PRODUCT_DESCRIPTION);
    }

    public String getProductPrice() {
        waitForElementVisible(ItemDetailUI.PRODUCT_PRICE);
        return getElementText(ItemDetailUI.PRODUCT_PRICE);
    }
}
