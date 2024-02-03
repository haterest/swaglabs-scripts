package swaglabs.testscipts;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddToCart_Function extends BaseTest {

    @BeforeClass
    public void beforeClass(){
        driver = getBrowserDriver();
    }

    @Test
    public void AddToCart_01_Adding_And_Remove_One_Item_In_The_Cart(){
    }

    @Test
    public void AddToCart_02_Checking_The_Count_Of_Shopping_Cart(){
    }

    @Test
    public void AddToCart_03_Delete_Items_In_Shopping_Cart(){
    }

    @Test
    public void AddToCart_04_Adding_Unsuccessfully_Items_To_The_Cart(){
    }

    @AfterClass
    public void afterClass() {
        quitPageURL();
    }
    private WebDriver driver;
    private String standardUser, errorUser, password;
}
