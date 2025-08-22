import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BaseTest {
    // ThreadLocal to store WebDriver instances
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupDriver() throws MalformedURLException {
        driver.set(createLambdaTestDriver());
    }

    @AfterMethod
    public void tearDown() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
        }
    }

    // Get the driver instance for the current thread
    protected WebDriver getDriver() {
        return driver.get();
    }

    // Create a new LambdaTest driver instance
    private WebDriver createLambdaTestDriver() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("120.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "dominicadawson");
        ltOptions.put("accessKey", "LT_bVB1uT6niL3xTmOMSls5Ghh18BQVwOnm7jVWXsxHv73g1Vd");
        ltOptions.put("project", "Koel App Testing");
        ltOptions.put("name", "Login Test");
        ltOptions.put("build", "1.0");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("console", true);
        ltOptions.put("network", true);
        browserOptions.setCapability("LT:Options", ltOptions);
        
        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }
}