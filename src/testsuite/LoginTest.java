package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginTest extends BaseTest {

    String BaseUrl = "https://www.saucedemo.com/";

    @Before
    public void setUp() {
        openBrowser(BaseUrl);
    }

    // Verifying user should be able to log in with valid credentials
    @Test
    public void userShouldLoginSuccessfullyWithValid() {
        // This is from requirement
        String expectedText = "PRODUCTS";
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement actualTextElement = driver.findElement(By.xpath("//span[contains(text(),'Products')]"));
        String actualText = actualTextElement.getText();
        // Verifying actual text matching with expected text
        Assert.assertEquals("PRODUCTS text not displayed", expectedText, actualText);
    }

    // Verifying 6 products are displayed after log in
    @Test
    public void verifyThatSixProductsAreDisplayedOnPage() {
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        List<WebElement> productNo = driver.findElements(By.className("inventory_item"));
        int totalProducts = productNo.size();
        // This is from requirement
        int expectedNumber = 6;
        // Verifying actual number matching with expected number
        Assert.assertEquals("Products numbers not matching", expectedNumber, totalProducts);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}

