package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTests extends BaseTest {
    private static final String BASE_URL = "https://qa.koel.app/";
    private static final String VALID_EMAIL = "dominica.dawson@testpro.io";
    private static final String VALID_PASSWORD = "H3lpMe2Te$tPle@se!";
    private static final String INVALID_EMAIL = "invalid@example.com";
    private static final String INVALID_PASSWORD = "wrongpassword";

    @Test(groups = {"smoke"})
    public void testSuccessfulLogin() {
        // Navigate to login page
        getDriver().get(BASE_URL);
        
        // Enter valid credentials
        getDriver().findElement(By.cssSelector("[type='email']")).sendKeys(VALID_EMAIL);
        getDriver().findElement(By.cssSelector("[type='password']")).sendKeys(VALID_PASSWORD);
        getDriver().findElement(By.cssSelector("[type='submit']")).click();
        
        // Verify successful login
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#mainWrapper")));
        Assert.assertTrue(getDriver().findElement(By.cssSelector("#mainWrapper")).isDisplayed(), 
            "Main wrapper should be visible after successful login");
    }

    @Test(groups = {"regression"})
    public void testLoginWithInvalidEmail() {
        // Navigate to login page
        getDriver().get(BASE_URL);
        
        // Enter invalid email with valid password
        getDriver().findElement(By.cssSelector("[type='email']")).sendKeys(INVALID_EMAIL);
        getDriver().findElement(By.cssSelector("[type='password']")).sendKeys(VALID_PASSWORD);
        getDriver().findElement(By.cssSelector("[type='submit']")).click();
        
        // Verify error message
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error")));
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".error")).isDisplayed(),
            "Error message should be displayed for invalid email");
    }

    @Test(groups = {"regression"})
    public void testLoginWithInvalidPassword() {
        // Navigate to login page
        getDriver().get(BASE_URL);
        
        // Enter valid email with invalid password
        getDriver().findElement(By.cssSelector("[type='email']")).sendKeys(VALID_EMAIL);
        getDriver().findElement(By.cssSelector("[type='password']")).sendKeys(INVALID_PASSWORD);
        getDriver().findElement(By.cssSelector("[type='submit']")).click();
        
        // Verify error message
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error")));
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".error")).isDisplayed(),
            "Error message should be displayed for invalid password");
    }

    @Test(groups = {"regression"})
    public void testLoginWithEmptyCredentials() {
        // Navigate to login page
        getDriver().get(BASE_URL);
        
        // Click login without entering credentials
        getDriver().findElement(By.cssSelector("[type='submit']")).click();
        
        // Verify form validation messages
        Assert.assertTrue(getDriver().findElement(By.cssSelector("[type='email']:invalid")).isDisplayed(),
            "Email validation message should be displayed");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("[type='password']:invalid")).isDisplayed(),
            "Password validation message should be displayed");
    }
}