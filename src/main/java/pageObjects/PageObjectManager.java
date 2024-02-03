package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    public static LoginPO getLoginPage(WebDriver driver){
        return new LoginPO(driver);
    }

      public static InventoryPO getInventoryPage(WebDriver driver){
        return new InventoryPO(driver);
    }

      public static ShoppingCartPO getShoppingCartPage(WebDriver driver){
        return new ShoppingCartPO(driver);
    }

      public static CheckoutPO getCheckoutPage(WebDriver driver){
        return new CheckoutPO(driver);
    }

      public static ItemDetailPO getItemDetailPage(WebDriver driver){
        return new ItemDetailPO(driver);
    }

}
