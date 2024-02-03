package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public BasePage (WebDriver driver){
        this.driver = driver;
    }

    public void openPageURL(String pageURL) {
        driver.get(pageURL);
    }

    public void refreshCurrentPage( ) {
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
        if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=")
                || locatorType.startsWith("CLASS=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=")
                || locatorType.startsWith("NAME=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
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

    protected String getElementAttribute(String locator, String attributeName) {
        return getWebElement(locator).getAttribute(attributeName);
    }

    protected String getElementAttribute(String locator, String attributeName, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).getAttribute(attributeName);
    }

    protected String getElementText(String locator) {
        return getWebElement(locator).getText();
    }

    protected String getElementText(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).getText();
    }

    protected int getElementsSize(String locator) {
        return getListWebElement(locator).size();
    }

    protected int getElementsSize(String locator, String... dynamicValues) {
        return getListWebElement(getDynamicXpath(locator, dynamicValues)).size();
    }
    protected boolean isElementDisplayed(String locator) {
        return getWebElement(locator).isDisplayed();
    }

    protected boolean isElementDisplayed(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).isDisplayed();
    }

    protected boolean isElementUndisplayed(String locator) {
        overrideGlobalTimeout(shortTimeout);
        List<WebElement> elements = getListWebElement(locator);
        overrideGlobalTimeout(longTimeout);
        if (elements.size() == 0) {
            return true;
        } else return !elements.get(0).isDisplayed();
    }

    protected boolean isElementUndisplayed(String locator, String... dynamicValues) {
        overrideGlobalTimeout(shortTimeout);
        List<WebElement> elements = getListWebElement(getDynamicXpath(locator, dynamicValues));
        overrideGlobalTimeout(longTimeout);
        if (elements.size() == 0) {
            return true;
        } else return !elements.get(0).isDisplayed();
    }

    /*
     * Wait for element undisplayed in DOM or not in DOM and override implicit Timeout
     */
    protected void waitForElementUndisplayed(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideGlobalTimeout(shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
        overrideGlobalTimeout(longTimeout);
    }

    protected void waitForAllElementsInvisible(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(locator)));
    }

    protected void waitForAllElementsInvisible(String locator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions
                .invisibilityOfAllElements(getListWebElement(getDynamicXpath(locator, dynamicValues))));
    }

    private void overrideGlobalTimeout(Duration timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut);
    }

    protected boolean isElementSelected(String locator) {
        return getWebElement(locator).isSelected();
    }

    protected boolean isElementSelected(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).isSelected();
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

    protected void waitForAllElementsVisible(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    protected void waitForAllElementsVisible(String locator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    protected void waitForElementInvisible(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementInvisible(String locator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(
                ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
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

    protected WebDriver driver;
    protected Duration longTimeout = Duration.ofSeconds(20);
    protected Duration shortTimeout = Duration.ofSeconds(5);
}
