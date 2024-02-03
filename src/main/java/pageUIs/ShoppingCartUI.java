package pageUIs;

public class ShoppingCartUI {
    public static final String PRODUCT_BY_NAME = "xpath=//div[text()='%s']";
    public static final String PRODUCT_DESC_BY_NAME = "xpath=//div[text()='%s']/parent::a/following-sibling::div" +
            "[@class='inventory_item_desc']";
    public static final String PRODUCT_PRICE_BY_NAME = "xpath=//div[text()='%s']/parent::a/following-sibling::div/div";
    public static final String REMOVE_BUTTON_BY_NAME = "xpath=//div[text()='%s']/parent::a/following-sibling::div/button";
    public static final String CHECKOUT_BUTTON = "css=#checkout";
}
