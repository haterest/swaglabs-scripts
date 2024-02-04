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
        bikeLightProduct = "Sauce Labs Bike Light";
        paymentInfor = "SauceCard #31337";
        shipInfor = "Free Pony Express Delivery!";

        loginPage = PageObjectManager.getLoginPage(driver);
        inventoryPage = loginPage.loginByUsernameAndPassword(standardUser, password);
    }

    @Test
    public void CheckOut_01_Displayed_Product_Detail_In_Checkout_Overview(){
        itemDetailPage = inventoryPage.clickToProductLinkByName(backpackProduct);
        productInfor = itemDetailPage.getProductInfor();
        productPrice = itemDetailPage.getProductPrice();
        itemDetailPage.clickAddToCartButton();
        shoppingCartPage = itemDetailPage.clickShoppingCartIcon();
        verifyTrue(shoppingCartPage.isDisplayedProductInCartByName(backpackProduct));
        checkoutPage = shoppingCartPage.clickCheckoutButton();
        checkoutPage.inputToFirstNameTextbox(dataHelper.getFirstName());
        checkoutPage.inputToLastNameTextbox(dataHelper.getLastName());
        checkoutPage.inputToZipPostalCodeTextbox(dataHelper.getZipCode());
        checkoutPage.clickToContinueButton();
        totalPrice = checkoutPage.getTotalPriceProduct(productPrice);
        verifyTrue(checkoutPage.isDisplayProductReviewByName(backpackProduct));
        verifyEquals(checkoutPage.getProductReviewDescriptionByName(backpackProduct), productInfor);
        verifyEquals(checkoutPage.getProductReviewPriceByName(backpackProduct), productPrice);
        verifyTrue(checkoutPage.isDisplayPaymentInformation(paymentInfor));
        verifyTrue(checkoutPage.isDisplayShippingInformation(shipInfor));
        verifyEquals(checkoutPage.getItemTotalPrice(), "Item total: " + productPrice);
        verifyEquals(checkoutPage.getTotalPriceBill(), "Total: $" + totalPrice);
    }

    @Test
    public void CheckOut_02_Cancel_Checkout(){
        checkoutPage.clickSideBarMenuButton();
        checkoutPage.clickResetAppStateLink();
        inventoryPage = checkoutPage.clickAllItemsLink();
        itemDetailPage = inventoryPage.clickToProductLinkByName(bikeLightProduct);
        productInfor = itemDetailPage.getProductInfor();
        productPrice = itemDetailPage.getProductPrice();
        itemDetailPage.clickAddToCartButton();
        shoppingCartPage = itemDetailPage.clickShoppingCartIcon();
        verifyTrue(shoppingCartPage.isDisplayedProductInCartByName(bikeLightProduct));
        checkoutPage = shoppingCartPage.clickCheckoutButton();
        checkoutPage.inputToFirstNameTextbox(dataHelper.getFirstName());
        checkoutPage.inputToLastNameTextbox(dataHelper.getLastName());
        checkoutPage.inputToZipPostalCodeTextbox(dataHelper.getZipCode());
        checkoutPage.clickToContinueButton();
        totalPrice = checkoutPage.getTotalPriceProduct(productPrice);
        verifyTrue(checkoutPage.isDisplayProductReviewByName(bikeLightProduct));
        verifyEquals(checkoutPage.getProductReviewDescriptionByName(bikeLightProduct), productInfor);
        verifyEquals(checkoutPage.getProductReviewPriceByName(bikeLightProduct), productPrice);
        verifyTrue(checkoutPage.isDisplayPaymentInformation(paymentInfor));
        verifyTrue(checkoutPage.isDisplayShippingInformation(shipInfor));
        verifyEquals(checkoutPage.getItemTotalPrice(), "Item total: " + productPrice);
        verifyEquals(checkoutPage.getTotalPriceBill(), "Total: $" + totalPrice);
        inventoryPage = checkoutPage.clickCancelButton();
        verifyTrue(inventoryPage.isDisplayProductHeader());
    }

    @Test
    public void CheckOut_03_Order_Successfully(){
        inventoryPage.clickSideBarMenuButton();
        inventoryPage.clickResetAppStateLink();
        inventoryPage = inventoryPage.clickAllItemsLink();
        itemDetailPage = inventoryPage.clickToProductLinkByName(boltTshirtProduct);
        productInfor = itemDetailPage.getProductInfor();
        productPrice = itemDetailPage.getProductPrice();
        itemDetailPage.clickAddToCartButton();
        shoppingCartPage = itemDetailPage.clickShoppingCartIcon();
        verifyTrue(shoppingCartPage.isDisplayedProductInCartByName(boltTshirtProduct));
        checkoutPage = shoppingCartPage.clickCheckoutButton();
        checkoutPage.inputToFirstNameTextbox(dataHelper.getFirstName());
        checkoutPage.inputToLastNameTextbox(dataHelper.getLastName());
        checkoutPage.inputToZipPostalCodeTextbox(dataHelper.getZipCode());
        checkoutPage.clickToContinueButton();
        totalPrice = checkoutPage.getTotalPriceProduct(productPrice);
        verifyTrue(checkoutPage.isDisplayProductReviewByName(boltTshirtProduct));
        verifyEquals(checkoutPage.getProductReviewDescriptionByName(boltTshirtProduct), productInfor);
        verifyEquals(checkoutPage.getProductReviewPriceByName(boltTshirtProduct), productPrice);
        verifyTrue(checkoutPage.isDisplayPaymentInformation(paymentInfor));
        verifyTrue(checkoutPage.isDisplayShippingInformation(shipInfor));
        verifyEquals(checkoutPage.getItemTotalPrice(), "Item total: " + productPrice);
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
        productInfor = itemDetailPage.getProductInfor();
        productPrice = itemDetailPage.getProductPrice();
        itemDetailPage.clickAddToCartButton();
        shoppingCartPage = itemDetailPage.clickShoppingCartIcon();
        verifyTrue(shoppingCartPage.isDisplayedProductInCartByName(backpackProduct));
        checkoutPage = shoppingCartPage.clickCheckoutButton();
        checkoutPage.inputToFirstNameTextbox(dataHelper.getFirstName());
        checkoutPage.inputToLastNameTextbox(dataHelper.getLastName());
        checkoutPage.inputToZipPostalCodeTextbox(dataHelper.getZipCode());
        checkoutPage.clickToContinueButton();
        totalPrice = checkoutPage.getTotalPriceProduct(productPrice);
        verifyTrue(checkoutPage.isDisplayProductReviewByName(backpackProduct));
        verifyEquals(checkoutPage.getProductReviewDescriptionByName(backpackProduct), productInfor);
        verifyEquals(checkoutPage.getProductReviewPriceByName(backpackProduct), productPrice);
        verifyTrue(checkoutPage.isDisplayPaymentInformation(paymentInfor));
        verifyTrue(checkoutPage.isDisplayShippingInformation(shipInfor));
        verifyEquals(checkoutPage.getItemTotalPrice(), "Item total: " + productPrice);
        verifyEquals(checkoutPage.getTotalPriceBill(), "Total: $" + totalPrice);
        checkoutPage.clickToFinishButton();
        verifyEquals(checkoutPage.getSuccessfullOrderMessage(), "Thank you for your order!");
    }

    @AfterClass
    public void afterClass() {
        quitPageURL();
    }

    WebDriver driver;
    private String standardUser, errorUser, password, boltTshirtProduct, backpackProduct, paymentInfor, shipInfor,
            productInfor, productPrice, totalPrice, bikeLightProduct;
    private LoginPO loginPage;
    private InventoryPO inventoryPage;
    private ItemDetailPO itemDetailPage;
    private ShoppingCartPO shoppingCartPage;
    private CheckoutPO checkoutPage;
    private DataHelper dataHelper;
}
