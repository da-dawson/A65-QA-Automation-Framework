import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() {
        // WebDriverManager setup - can be used for local driver fallback
        WebDriverManager.chromedriver().setup();
        System.out.println("WebDriverManager setup completed");
    }

    @BeforeClass
    public void setUpBrowser() {
        try {
            // Use Browser Factory to create driver connected to Selenium Grid
            driver = BrowserFactory.createChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            System.out.println("Browser initialized using Selenium Grid at: " + BrowserFactory.getGridUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to connect to Selenium Grid: " + e.getMessage(), e);
        }
    }

    @AfterClass
    public void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Helper method to get the driver instance
     * @return WebDriver instance
     */
    protected WebDriver getDriver() {
        return driver;
    }
}