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
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected PlaylistPage playlistPage;
    private static final String BASE_URL = "https://qa.koel.app/";

    @BeforeSuite
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setUpBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*",
                "--disable-notifications",
                "--start-maximized"
        );

        driver = new ChromeDriver(options);
        driver.get(BASE_URL);

        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        playlistPage = new PlaylistPage(driver);
    }

    @AfterClass
    public void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

