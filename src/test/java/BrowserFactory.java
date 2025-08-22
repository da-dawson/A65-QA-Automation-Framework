import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {
    
    private static final String GRID_URL = "http://localhost:4444";
    
    /**
     * Creates a Chrome WebDriver instance using the local Selenium Grid
     * @return WebDriver instance connected to the Grid
     * @throws MalformedURLException if the Grid URL is malformed
     */
    @SuppressWarnings("deprecation")
    public static WebDriver createChromeDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        
        // Add Chrome options for better stability
        options.addArguments(
                "--remote-allow-origins=*",
                "--disable-notifications",
                "--start-maximized",
                "--disable-blink-features=AutomationControlled",
                "--disable-extensions"
        );
        
        return new RemoteWebDriver(new URL(GRID_URL), options);
    }
    
    /**
     * Creates a WebDriver instance based on browser type using the local Selenium Grid
     * @param browserType The browser type (currently supports "chrome")
     * @return WebDriver instance connected to the Grid
     * @throws MalformedURLException if the Grid URL is malformed
     * @throws IllegalArgumentException if unsupported browser type is provided
     */
    public static WebDriver createDriver(String browserType) throws MalformedURLException {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return createChromeDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }
    
    /**
     * Get the Grid URL
     * @return The Selenium Grid URL
     */
    public static String getGridUrl() {
        return GRID_URL;
    }
}
