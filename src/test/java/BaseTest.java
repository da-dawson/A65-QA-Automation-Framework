import Pages.BasePage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.PlaylistPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    BasePage basePage;
    LoginPage loginPage;
    HomePage homePage;
    Pages.PlaylistPage playlistPage;
    public WebDriver driver;
    public String url = "https://qa.koel.app/";

    @BeforeSuite

    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void launchBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        basePage = new BasePage(driver);
        basePage.navigateToPage(url);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        playlistPage = new PlaylistPage(driver);
    }

    @AfterClass
    public void closeBrowser() {
        basePage.quitBrowser();
    }



}