import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.*;
import java.time.Duration;


public class BaseTest {

        static WebDriver driver;
        public WebDriverWait wait;
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
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
            WebElement enterEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']")));
            enterEmail.clear();
            enterEmail.sendKeys("dominica.dawson@testpro.io");
        }
        public void enterPassword(String Password) {
            WebElement enterPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']")));
            enterPassword.clear();
            enterPassword.sendKeys("H3lpMe2Te$tPle@se!");
        }

        public void clickSubmit(){
            WebElement clickSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']")));
            clickSubmit.click();
        }

        public String getRandomString(int lettersAmount){
            String randomString = RandomStringUtils.randomAlphabetic(lettersAmount);
            return randomString;
        }

    }