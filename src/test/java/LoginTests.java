import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void loginEmptyEmailPassword() throws InterruptedException {
        // Using the driver from BaseTest which is connected to Selenium Grid
        driver.get(BASE_URL);
        
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        System.out.println("Successfully navigated to: " + driver.getCurrentUrl());
        System.out.println("Using Selenium Grid driver via Browser Factory");
        
        Thread.sleep(2000); // Added a temporary Thread.sleep for demo purposes
        
        // Note: driver.quit() is handled in BaseTest tearDownBrowser method
    }
}
