package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    protected WebDriver getDriver() {
        return driver.get();
    }

    @BeforeSuite(alwaysRun = true)
    public void setupClass() {
        try {
            WebDriverManager.chromedriver().setup();
            WebDriverManager.firefoxdriver().setup();
            WebDriverManager.edgedriver().setup();
        } catch (Exception e) {
            System.out.println("WebDriverManager setup failed, will try direct ChromeDriver setup: " + e.getMessage());
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver.set(createDriver(browser));
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
        }
    }

    private WebDriver createDriver(String browser) {
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments(
                        "--remote-allow-origins=*",
                        "--no-sandbox",
                        "--disable-dev-shm-usage"
                    );
                    return new ChromeDriver(chromeOptions);
                case "firefox":
                    return new FirefoxDriver(new FirefoxOptions());
                case "edge":
                    return new EdgeDriver(new EdgeOptions());
                default:
                    throw new IllegalArgumentException("Browser " + browser + " not supported");
            }
        } catch (Exception e) {
            System.out.println("Failed to create driver for " + browser + ": " + e.getMessage());
            // Fallback to Chrome if specified browser fails
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*", "--no-sandbox", "--disable-dev-shm-usage");
            return new ChromeDriver(options);
        }
    }
}