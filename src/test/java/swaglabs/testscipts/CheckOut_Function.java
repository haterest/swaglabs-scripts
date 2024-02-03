package swaglabs.testscipts;

import commons.BaseTest;
import commons.DataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.*;

public class CheckOut_Function extends BaseTest {
    @BeforeClass
    public void beforeClass() {
        driver = getBrowserDriver();
        dataHelper = DataHelper.getData();

        standardUser = "standard_user";
        errorUser = "error_user";
        password = "secret_sauce";
        boltTshirtProduct = "Sauce Labs Bolt T-Shirt";
        backpackProduct = "Sauce Labs Backpack";
        paymentInfor = "SauceCard #31337";
        shipInfor = "Free Pony Express Delivery!";

        loginPage = PageObjectManager.getLoginPage(driver);
        inventoryPage = loginPage.loginByUsernameAndPassword(standardUser, password);
    }

    @Test
    public void CheckOut_03_Order_Successfully(){
        inventoryPage.clickSideBarMenuButton();
        inventoryPage.clickResetAppStateLink();
        inventoryPage = inventoryPage.clickAllItemsLink();
        itemDetailPage = inventoryPage.clickToProductLinkByName(boltTshirtProduct);
        String productInfor = itemDetailPage.getProductInfor();
        String producPrice = itemDetailPage.getProductPrice();
        itemDetailPage.clickAddToCartButton();
        shoppingCartPage = itemDetailPage.clickShoppingCartIcon();
        verifyTrue(shoppingCartPage.isDisplayedProductInCartByName(boltTshirtProduct));
        checkoutPage = shoppingCartPage.clickCheckoutButton();
        checkoutPage.inputToFirstNameTextbox(dataHelper.getFirstName());
        checkoutPage.inputToLastNameTextbox(dataHelper.getLastName());
        checkoutPage.inputToZipPostalCodeTextbox(dataHelper.getZipCode());
        checkoutPage.clickToContinueButton();
        String totalPrice = checkoutPage.getTotalPriceProduct(producPrice);
        verifyTrue(checkoutPage.isDisplayProductReviewByName(boltTshirtProduct));
        verifyEquals(checkoutPage.getProductReviewDescriptionByName(boltTshirtProduct), productInfor);
        verifyEquals(checkoutPage.getProductReviewPriceByName(boltTshirtProduct), producPrice);
        verifyTrue(checkoutPage.isDisplayPaymentInformation(paymentInfor));
        verifyTrue(checkoutPage.isDisplayShippingInformation(shipInfor));
        verifyEquals(checkoutPage.getItemTotalPrice(), "Item total: " + producPrice);
        verifyEquals(checkoutPage.getTotalPriceBill(), "Total: $" + totalPrice);
        checkoutPage.clickToFinishButton();
        verifyEquals(checkoutPage.getSuccessfullOrderMessage(), "Thank you for your order!");
    }

    @Test
    public void CheckOut_04_Order_Unsuccessfully(){
        checkoutPage.clickSideBarMenuButton();
        checkoutPage.clickResetAppStateLink();
        loginPage = checkoutPage.clickToLogoutLink();
        inventoryPage = loginPage.loginByUsernameAndPassword(errorUser, password);

        itemDetailPage = inventoryPage.clickToProductLinkByName(backpackProduct);
        String productInfor = itemDetailPage.getProductInfor();
        String producPrice = itemDetailPage.getProductPrice();
        itemDetailPage.clickAddToCartButton();
        shoppingCartPage = itemDetailPage.clickShoppingCartIcon();
        verifyTrue(shoppingCartPage.isDisplayedProductInCartByName(backpackProduct));
        checkoutPage = shoppingCartPage.clickCheckoutButton();
        checkoutPage.inputToFirstNameTextbox(dataHelper.getFirstName());
        checkoutPage.inputToLastNameTextbox(dataHelper.getLastName());
        checkoutPage.inputToZipPostalCodeTextbox(dataHelper.getZipCode());
        checkoutPage.clickToContinueButton();
        String totalPrice = checkoutPage.getTotalPriceProduct(producPrice);
        verifyTrue(checkoutPage.isDisplayProductReviewByName(backpackProduct));
        verifyEquals(checkoutPage.getProductReviewDescriptionByName(backpackProduct), productInfor);
        verifyEquals(checkoutPage.getProductReviewPriceByName(backpackProduct), producPrice);
        verifyTrue(checkoutPage.isDisplayPaymentInformation(paymentInfor));
        verifyTrue(checkoutPage.isDisplayShippingInformation(shipInfor));
        verifyEquals(checkoutPage.getItemTotalPrice(), "Item total: " + producPrice);
        verifyEquals(checkoutPage.getTotalPriceBill(), "Total: $" + totalPrice);
        checkoutPage.clickToFinishButton();
        verifyEquals(checkoutPage.getSuccessfullOrderMessage(), "Thank you for your order!");
    }

    @AfterClass
    public void afterClass() {
        quitPageURL();
    }

    WebDriver driver;
    private String standardUser, errorUser, password, boltTshirtProduct, backpackProduct, paymentInfor, shipInfor;
    private LoginPO loginPage;
    private InventoryPO inventoryPage;
    private ItemDetailPO itemDetailPage;
    private ShoppingCartPO shoppingCartPage;
    private CheckoutPO checkoutPage;
    private DataHelper dataHelper;
}
