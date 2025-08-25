package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.example.BaseTest;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginSteps extends BaseTest {
    
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        getDriver().get("https://qa.koel.app");
        
        // Wait for the login form to be visible
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")));
    }

    @When("I enter valid username and password")
    public void iEnterValidCredentials() {
        // Implementation using your page objects
        // This is a placeholder implementation
        getDriver().findElement(By.cssSelector("[type='email']")).sendKeys("dominica.dawson@testpro.io");
        getDriver().findElement(By.cssSelector("[type='password']")).sendKeys("H3lpMe2Te$tPle@se!");
    }

    @And("I click the login button")
    public void iClickLoginButton() {
        // Implementation using your page objects
        // This is a placeholder implementation
        getDriver().findElement(By.cssSelector("[type='submit']")).click();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        // Wait for the inventory page to load
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        // Wait for the main app screen to be visible after login
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#mainWrapper")));
        
        // Verify we're logged in by checking for main elements
        Assert.assertTrue(getDriver().findElement(By.cssSelector("#mainWrapper")).isDisplayed(), 
            "Main wrapper should be visible after successful login");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("#sidebar")).isDisplayed(), 
            "Sidebar should be visible after successful login");
    }

    @When("I click the logout button")
    public void iClickLogoutButton() {
        // Click the user menu to open it
        getDriver().findElement(By.cssSelector(".profile")).click();
        
        // Wait for and click the logout option
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".logout"))).click();
    }

    @Then("I should be logged out successfully")
    public void iShouldBeLoggedOutSuccessfully() {
        // Wait for the login form to be visible again
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")));
        
        // Verify we're back on the login page
        Assert.assertTrue(getDriver().findElement(By.cssSelector("[type='email']")).isDisplayed(), 
            "Email input should be visible on login page");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("[type='password']")).isDisplayed(), 
            "Password input should be visible on login page");
    }
}
