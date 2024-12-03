import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;


public class BaseTest {

    public static WebDriver driver = null;
    public static String url = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void launchBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    protected static void navigateToPage() {
        String url = ("https://qa.koel.app/");
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
    public void searchSong(String songTitleKeyword) throws InterruptedException {
        WebElement searchField = driver.findElement(By.cssSelector(("div#searchForm input[type=search]")));
        searchField.clear();
        searchField.sendKeys(songTitleKeyword);
        Thread.sleep(2000);
    }

    public void clickViewAllBtn() throws InterruptedException {
        WebElement viewAll = driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
        viewAll.click();
        Thread.sleep(2000);
    }

    public void selectFirstSongResult() throws InterruptedException {
        WebElement FirstSongResult = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//tr[@class='song-item'][1]"));
        FirstSongResult.click();
        Thread.sleep(2000);
    }

    public void clickAddToBtn() throws InterruptedException {
        WebElement addToBtn = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//button[@data-test='add-to-btn']"));
        addToBtn.click();
        Thread.sleep(2000);
    }
    public void choosePlayList() throws InterruptedException {
        WebElement playListElement = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'Homework17')]"));
        playListElement.click();
        Thread.sleep(2000);
    }

    public String getNotificationText() {
        WebElement notification = driver.findElement(By.cssSelector("div.success.show"));
        System.out.println(notification.getText());
        return notification.getText();

    }
}