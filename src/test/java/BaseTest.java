import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.*;
import java.time.Duration;


public class BaseTest {

        public WebDriver driver = null;
        public String url;
        @BeforeSuite
        static void setupClass() {
            WebDriverManager.chromedriver().setup();
        }

        @BeforeMethod
        @Parameters({"BaseURL"})
        public void launchBrowser(String baseURL) {
            //Added ChromeOptions argument below to fix websocket error
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            url = baseURL;
            navigateToPage();
        }

        @AfterMethod
        public void closeBrowser() {
            driver.quit();
        }

        public void navigateToPage() {
            driver.get(url);
        }
        public void enterEmail(String Email) {
            WebElement enterEmail = driver.findElement(By.cssSelector("[type='email']"));
            enterEmail.click();
            enterEmail.clear();
            enterEmail.sendKeys("dominica.dawson@testpro.io");
        }
        public void enterPassword(String Password) {
            WebElement enterPassword = driver.findElement(By.cssSelector("[type='password']"));
            enterPassword.click();
            enterPassword.clear();
            enterPassword.sendKeys("H3lpMe2Te$tPle@se!");
        }

        public void clickSubmit(){
            WebElement clickSubmit = driver.findElement(By.cssSelector("[type='submit']"));
            clickSubmit.click();
        }

    }