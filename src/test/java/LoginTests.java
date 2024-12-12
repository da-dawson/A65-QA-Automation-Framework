import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {
    @Test
    public void loginEmptyEmailPassword()  {

        WebElement loginField = driver.findElement(By.cssSelector("[type='email']"));
        loginField.sendKeys("planner@testpro.io");
        WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        passwordField.sendKeys("6JooL8gp");
        submitButton.click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://qa.koel.app/");
    }
}
