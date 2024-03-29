package swaglabs.testscipts;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.*;

public class AddToCart_Function extends BaseTest {
    @BeforeClass
    public void beforeClass(){
        driver = getBrowserDriver();

        standardUser = "standard_user";
        errorUser = "error_user";
        password = "secret_sauce";
        backPackProduct = "Sauce Labs Backpack";
        boltTshirtProduct = "Sauce Labs Bolt T-Shirt";
        jacketProduct = "Sauce Labs Fleece Jacket";

        loginPage = PageObjectManager.getLoginPage(driver);
        inventoryPage = loginPage.loginByUsernameAndPassword(standardUser, password);
    }

    @Test
    public void AddToCart_01_Adding_And_Remove_One_Item_In_The_Cart(){
        itemDetailPage = inventoryPage.clickToProductLinkByName(backPackProduct);
        itemDetailPage.clickAddToCartButton();
        String productInfor = itemDetailPage.getProductInfor();
        String producPrice = itemDetailPage.getProductPrice();
        shoppingCartPage = itemDetailPage.clickShoppingCartIcon();
        verifyTrue(shoppingCartPage.isDisplayedProductInCartByName(backPackProduct));
        verifyEquals(shoppingCartPage.getProductInforByName(backPackProduct), productInfor);
        verifyEquals(shoppingCartPage.getProductPriceByName(backPackProduct), producPrice);
        shoppingCartPage.clickRemoveButtonByName(backPackProduct);
        verifyTrue(shoppingCartPage.isUndisplayedProductInCartByName(backPackProduct));
    }

    @Test
    public void AddToCart_04_Adding_Successfully_Items_To_The_Cart(){
        shoppingCartPage.clickSideBarMenuButton();
        shoppingCartPage.clickResetAppStateLink();
        loginPage = shoppingCartPage.clickToLogoutLink();
        inventoryPage = loginPage.loginByUsernameAndPassword(errorUser, password);

        inventoryPage.clickAddToCartButtonByName(boltTshirtProduct);
        inventoryPage.clickAddToCartButtonByName(jacketProduct);
        shoppingCartPage = inventoryPage.clickShoppingCartIcon();
        verifyTrue(shoppingCartPage.isDisplayedProductInCartByName(boltTshirtProduct));
        verifyTrue(shoppingCartPage.isDisplayedProductInCartByName(jacketProduct));
    }

    @AfterClass
    public void afterClass() {
        quitPageURL();
    }

    private WebDriver driver;
    private String standardUser, errorUser, password, backPackProduct, boltTshirtProduct, jacketProduct;
    private LoginPO loginPage;
    private InventoryPO inventoryPage;
    private ItemDetailPO itemDetailPage;
    private ShoppingCartPO shoppingCartPage;
}
