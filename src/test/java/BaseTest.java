import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    protected WebDriver getDriver() {
        return driver.get();
    }

    private static final String browser = System.getProperty("browser", "chrome");
    private static final String GRID_URL = "http://localhost:4444/wd/hub";
    private static final String CLOUD_URL = "https://hub.lambdatest.com/wd/hub";
    private static final String LT_USERNAME = "dominica.dawson";
    private static final String LT_ACCESS_KEY = "LT_bVB1uT6niL3xTmOMSls5Ghh18BQVwOnm7jVWXsxHv73g1Vd";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    private WebDriver pickBrowser(String browserType) throws MalformedURLException {
        System.out.println("Setting up " + browserType + " on thread: " + Thread.currentThread().getId());
        
        WebDriver selectedDriver;
        switch (browserType.toLowerCase()) {
            // Local browsers
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                selectedDriver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                selectedDriver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                selectedDriver = new EdgeDriver(edgeOptions);
                break;
                
            // Grid browsers
            case "grid-chrome":
                ChromeOptions gridChromeOptions = new ChromeOptions();
                selectedDriver = new RemoteWebDriver(new URL(GRID_URL), gridChromeOptions);
                break;
                
            case "grid-firefox":
                FirefoxOptions gridFirefoxOptions = new FirefoxOptions();
                selectedDriver = new RemoteWebDriver(new URL(GRID_URL), gridFirefoxOptions);
                break;
                
            case "grid-edge":
                EdgeOptions gridEdgeOptions = new EdgeOptions();
                selectedDriver = new RemoteWebDriver(new URL(GRID_URL), gridEdgeOptions);
                break;
                
            // LambdaTest cloud
            case "cloud":
                ChromeOptions cloudOptions = new ChromeOptions();
                cloudOptions.setPlatformName("Windows 10");
                cloudOptions.setBrowserVersion("120.0");
                
                HashMap<String, Object> ltOptions = new HashMap<>();
                ltOptions.put("username", LT_USERNAME);
                ltOptions.put("accessKey", LT_ACCESS_KEY);
                ltOptions.put("project", "Koel App Testing");
                ltOptions.put("build", "Jenkins Build");
                ltOptions.put("name", "Test on " + Thread.currentThread().getId());
                ltOptions.put("w3c", true);
                ltOptions.put("plugin", "java-testNG");
                
                cloudOptions.setCapability("LT:Options", ltOptions);
                selectedDriver = new RemoteWebDriver(new URL(CLOUD_URL), cloudOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Browser " + browserType + 
                    " not supported. Use: chrome, firefox, edge, grid-chrome, grid-firefox, grid-edge, or cloud");
        }
        
        selectedDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        selectedDriver.manage().window().maximize();
        return selectedDriver;
    }

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver.set(pickBrowser(browser));
    }

    @AfterMethod
    public void tearDown() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
        }
    }
}