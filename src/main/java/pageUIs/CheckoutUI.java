package pageUIs;

public class CheckoutUI {
    public static final String FIRST_NAME_TEXTBOX = "css=#first-name";
    public static final String LAST_NAME_TEXTBOX = "css=#last-name";
    public static final String ZIP_POSTAL_CODE_TEXTBOX = "css=#postal-code";
    public static final String CONTINUE_BUTTON = "css=#continue";
    public static final String TAX_PRICE = "css=div.summary_tax_label";
    public static final String INFORMATION_VALUE_BY_NAME = "xpath=//div[text()='%s']";
    public static final String PRODUCT_DESCRIPTION_BY_NAME = "xpath=//div[text()='%s']/parent::a/following-sibling::div[@class='inventory_item_desc']";
    public static final String PRODUCT_PRICE_BY_NAME = "xpath=//div[text()='%s']/parent::a/following-sibling::div/div";
    public static final String ITEM_TOTAL_PRICE = "css=div.summary_subtotal_label";
    public static final String TOTAL_PRICE_BILL = "css=div.summary_info_label.summary_total_label";
    public static final String FINISH_BUTTON = "css=#finish";
    public static final String SUCCESSFULL_ORDER_MESSAGE = "css=h2.complete-header";
}
