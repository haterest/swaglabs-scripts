package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginUI;

public class LoginPO extends BasePage {
    public LoginPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public InventoryPO loginByUsernameAndPassword(String username, String password) {
        waitForElementVisible(LoginUI.USERNAME_TEXTBOX);
        sendKeyToElement(LoginUI.USERNAME_TEXTBOX, username);
        waitForElementVisible(LoginUI.PASSWORD_TEXTBOX);
        sendKeyToElement(LoginUI.PASSWORD_TEXTBOX, password);
        waitForElementClickable(LoginUI.LOGIN_BUTTON);
        clickToElement(LoginUI.LOGIN_BUTTON);
        return PageObjectManager.getInventoryPage(driver);
    }
}
