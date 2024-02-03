package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CheckoutUI;

public class CheckoutPO extends BasePage {
    public CheckoutPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(CheckoutUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(CheckoutUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(CheckoutUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(CheckoutUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void inputToZipPostalCodeTextbox(String zipCode) {
        waitForElementVisible(CheckoutUI.ZIP_POSTAL_CODE_TEXTBOX);
        sendKeyToElement(CheckoutUI.ZIP_POSTAL_CODE_TEXTBOX, zipCode);
    }

    public void clickToContinueButton() {
        waitForElementClickable(CheckoutUI.CONTINUE_BUTTON);
        clickToElement(CheckoutUI.CONTINUE_BUTTON);
    }

    public String getTotalPriceProduct(String producPrice) {
        Double productPrice = Double.parseDouble(producPrice.replace("$", ""));
        Double taxPrice = Double.parseDouble(getElementText(CheckoutUI.TAX_PRICE).substring(6));
        return String.valueOf(productPrice + taxPrice);
    }

    public boolean isDisplayProductReviewByName(String productName) {
        waitForElementVisible(CheckoutUI.INFORMATION_VALUE_BY_NAME, productName);
        return isElementDisplayed(CheckoutUI.INFORMATION_VALUE_BY_NAME, productName);
    }

    public String getProductReviewDescriptionByName(String productName) {
        waitForElementVisible(CheckoutUI.PRODUCT_DESCRIPTION_BY_NAME, productName);
        return getElementText(CheckoutUI.PRODUCT_DESCRIPTION_BY_NAME, productName);
    }

    public String getProductReviewPriceByName(String productName) {
        waitForElementVisible(CheckoutUI.PRODUCT_PRICE_BY_NAME, productName);
        return getElementText(CheckoutUI.PRODUCT_PRICE_BY_NAME, productName);
    }

    public String getItemTotalPrice() {
        waitForElementVisible(CheckoutUI.ITEM_TOTAL_PRICE);
        return getElementText(CheckoutUI.ITEM_TOTAL_PRICE);
    }

    public String getTotalPriceBill() {
        waitForElementVisible(CheckoutUI.TOTAL_PRICE_BILL);
        return getElementText(CheckoutUI.TOTAL_PRICE_BILL);
    }

    public void clickToFinishButton() {
        waitForElementClickable(CheckoutUI.FINISH_BUTTON);
        clickToElement(CheckoutUI.FINISH_BUTTON);
    }

    public String getSuccessfullOrderMessage() {
        waitForElementVisible(CheckoutUI.SUCCESSFULL_ORDER_MESSAGE);
        return getElementText(CheckoutUI.SUCCESSFULL_ORDER_MESSAGE);
    }

    public boolean isDisplayPaymentInformation(String paymentInfor) {
        waitForElementVisible(CheckoutUI.INFORMATION_VALUE_BY_NAME, paymentInfor);
        return isElementDisplayed(CheckoutUI.INFORMATION_VALUE_BY_NAME, paymentInfor);
    }

    public boolean isDisplayShippingInformation(String shippingInfor) {
        waitForElementVisible(CheckoutUI.INFORMATION_VALUE_BY_NAME, shippingInfor);
        return isElementDisplayed(CheckoutUI.INFORMATION_VALUE_BY_NAME, shippingInfor);
    }
}
