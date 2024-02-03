package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.InventoryPO;
import pageObjects.LoginPO;
import pageObjects.PageObjectManager;
import pageObjects.ShoppingCartPO;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public BasePage (WebDriver driver){
        this.driver = driver;
    }

    protected void refreshCurrentPage( ) {
        driver.navigate().refresh();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private By getByLocator(String locatorType) {
        By by;
        if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=")
                || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator type is not supported");
        }
        return by;
    }

    private String getDynamicXpath(String locator, String... values) {
        if (locator.startsWith("xpath=") || locator.startsWith("Xpath=") || locator.startsWith("XPATH=")
                || locator.startsWith("XPath=")) {
            locator = String.format(locator, (Object[]) values);
        }
        return locator;
    }

    private WebElement getWebElement(String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    private List<WebElement> getListWebElement(String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    protected void clickToElement(String locator) {
        getWebElement(locator).click();
    }

    protected void clickToElement(String locator, String... dynamicValues) {
        getWebElement(getDynamicXpath(locator, dynamicValues)).click();
    }

    protected void sendKeyToElement(String locator, String textValue) {
        WebElement element = getWebElement(locator);
        element.clear();
        element.sendKeys(textValue);
    }
    protected String getElementText(String locator) {
        return getWebElement(locator).getText();
    }

    protected String getElementText(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).getText();
    }

    protected boolean isElementDisplayed(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).isDisplayed();
    }

    protected boolean isElementUndisplayed(String locator, String... dynamicValues) {
        overrideGlobalTimeout(shortTimeout);
        List<WebElement> elements = getListWebElement(getDynamicXpath(locator, dynamicValues));
        overrideGlobalTimeout(longTimeout);
        if (elements.isEmpty()) {
            return true;
        } else return !elements.get(0).isDisplayed();
    }

    private void overrideGlobalTimeout(Duration timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut);
    }

    protected void waitForElementVisible(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementVisible(String locator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    protected void waitForElementClickable(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    protected void waitForElementClickable(String locator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait
                .until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    public ShoppingCartPO clickShoppingCartIcon() {
        waitForElementClickable(BasePageUI.SHOPPING_CART_ICON);
        clickToElement(BasePageUI.SHOPPING_CART_ICON);
        return PageObjectManager.getShoppingCartPage(driver);
    }

    public void clickSideBarMenuButton() {
        waitForElementClickable(BasePageUI.SIDE_BAR_MENU_BUTTON);
        clickToElement(BasePageUI.SIDE_BAR_MENU_BUTTON);
        sleepInSecond(1);
    }

    public void clickResetAppStateLink() {
        waitForElementClickable(BasePageUI.RESET_APP_STATE_LINK);
        clickToElement(BasePageUI.RESET_APP_STATE_LINK);
        sleepInSecond(1);
    }

    public LoginPO clickToLogoutLink() {
        waitForElementClickable(BasePageUI.LOGOUT_LINK);
        clickToElement(BasePageUI.LOGOUT_LINK);
        return PageObjectManager.getLoginPage(driver);
    }

    public InventoryPO clickAllItemsLink() {
        waitForElementClickable(BasePageUI.ALL_ITEMS_LINK);
        clickToElement(BasePageUI.ALL_ITEMS_LINK);
        refreshCurrentPage();
        return PageObjectManager.getInventoryPage(driver);
    }

    protected WebDriver driver;
    protected Duration longTimeout = Duration.ofSeconds(10);
    protected Duration shortTimeout = Duration.ofSeconds(3);

}
