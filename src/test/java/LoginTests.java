import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTests extends BaseTest {
    private static final String VALID_EMAIL = "dominica.dawson@testpro.io";
    private static final String VALID_PASSWORD = "H3lpMe2Te$tPle@se!";
    private static final String BASE_URL = "https://qa.koel.app/";
    
    // Test data for parallel execution
    private static final String[] TEST_EMAILS = {
        "invalid@email.com",
        "test@test.com",
        "",
        "dominica.dawson@testpro.io"
    };
    
    private static final String[] TEST_PASSWORDS = {
        "wrongpass",
        "test123",
        "password123",
        "H3lpMe2Te$tPle@se!"
    };

    @DataProvider(name = "loginData", parallel = true)
    public Object[][] loginData() {
        return new Object[][] {
            {TEST_EMAILS[0], TEST_PASSWORDS[0], "Invalid Credentials Test"},
            {TEST_EMAILS[1], TEST_PASSWORDS[1], "Wrong Credentials Test"},
            {TEST_EMAILS[2], TEST_PASSWORDS[2], "Empty Email Test"},
            {VALID_EMAIL, VALID_PASSWORD, "Valid Credentials Test"}
        };
    }

    private void performLogin(String email, String password, String testName) {
        System.out.println("Starting test: " + testName + " on thread: " + Thread.currentThread().getId());
        
        // Navigate to the application
        getDriver().get(BASE_URL);
        
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        
        // Wait for and fill in the email field
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
        emailField.sendKeys(email);
        
        // Wait for and fill in the password field
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='password']")));
        passwordField.sendKeys(password);
        
        // Wait for and click the login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']")));
        loginButton.click();
        
        // Add debug info
        System.out.println("Current URL after login attempt: " + getDriver().getCurrentUrl());
        
        try {
            // Wait for the login process to complete
            Thread.sleep(2000);
            
            // Check if we're still on the login page (indicating failure)
            if (getDriver().getCurrentUrl().equals(BASE_URL)) {
                if (!email.equals(VALID_EMAIL) || !password.equals(VALID_PASSWORD)) {
                    // For invalid credentials, we expect to stay on login page
                    System.out.println("Invalid login attempt as expected: " + testName);
                } else {
                    Assert.fail("Login failed unexpectedly for valid credentials");
                }
            } else {
                // If we're not on login page, it should have been a valid login
                if (email.equals(VALID_EMAIL) && password.equals(VALID_PASSWORD)) {
                    System.out.println("Successfully logged in: " + testName);
                } else {
                    Assert.fail("Unexpected successful login with invalid credentials");
                }
            }
            
        } catch (InterruptedException e) {
            System.out.println("Test interrupted during wait");
            throw new RuntimeException(e);
        }
        
        System.out.println("Completed test: " + testName + " on thread: " + Thread.currentThread().getId());
    }

    @Test(dataProvider = "loginData")
    public void testMultipleLogins(String email, String password, String testCase) {
        performLogin(email, password, testCase);
    }
}