import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test
    public void loginEmptyEmailPassword() throws InterruptedException {

//      Added ChromeOptions argument below to fix websocket error
        String url = "https://qa.koel.app/";
        getDriver().get(url);
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
        Thread.sleep(2000); //Added a temporary Thread.sleep for demo purposes
    }
}
