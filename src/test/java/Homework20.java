import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20 extends BaseTest{
    @Test
    public void deletePlaylist() throws InterruptedException {
        navigateToPage();
        enterEmail("dominica.dawson@testpro.io");
        enterPassword("H3lpMe2Te$tPle@se!");
        clickSubmit();
        clickPlusIcon();
        clickNewPlaylistBtn();
        enterPlaylistName(By.cssSelector("[name='name']"), getRandomString(15));
        choosePlayList();
        clickDeletePlaylistBtn();
        playlistIsDeleted();
    }

    private void enterPlaylistName(By inputLocator, String inputText) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys(inputText, Keys.ENTER);
    }
    private void clickNewPlaylistBtn() {
        WebElement newPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='playlist-context-menu-create-simple']")));
        newPlaylist.click();
    }

    private void clickPlusIcon() {
        WebElement plusIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='sidebar-create-playlist-btn']")));
        plusIcon.click();
    }
    private void choosePlayList() throws InterruptedException {
        WebElement playListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        playListElement.click();
    }
    private void clickDeletePlaylistBtn() {
        WebElement deletePlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-delete-playlist")));
        deletePlaylist.click();
    }
    private void playlistIsDeleted() {
        WebElement playlistDeletedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Deleted playlist')]")));
        Assert.assertTrue(playlistDeletedMsg.isDisplayed());
    }

}