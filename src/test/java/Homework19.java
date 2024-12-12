import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework19 extends BaseTest {
    @Test
    public void deletePlaylist() throws InterruptedException {
        String expectedPlayListDeletedMessage = "Deleted playlist \"Homework17.\"";
        navigateToPage();
        enterEmail("dominica.dawson@testpro.io");
        enterPassword("H3lpMe2Te$tPle@se!");
        clickSubmit();
        choosePlayList();
        clickDeletePlaylistBtn();
        clickOkButton();
        Assert.assertEquals(getDeletedPlayListMsg(), expectedPlayListDeletedMessage);
    }

    private void choosePlayList() throws InterruptedException {
        WebElement playListElement = driver.findElement(By.cssSelector(".playlist:nth-child(3)"));
        playListElement.click();
        Thread.sleep(2000);
    }
    private void clickDeletePlaylistBtn() {
        WebElement deletePlaylist = driver.findElement(By.cssSelector(".btn-delete-playlist"));
        deletePlaylist.click();
    }
    private void clickOkButton() {
        WebElement clickOkButton = driver.findElement(By.cssSelector(".ok"));
        clickOkButton.click();
    }
    public String getDeletedPlayListMsg() {
        WebElement notificationMsg = driver.findElement(By.cssSelector("div.success.show"));
        return notificationMsg.getText();
    }

}