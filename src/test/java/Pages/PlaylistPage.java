package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class PlaylistPage extends BasePage{

    public PlaylistPage(WebDriver driver) {
        super(driver);
    }
    public void checkNumberOfSongsInPlaylist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".song-list-wrap.main-scroll-wrap.playlist td.title")));
        List<WebElement> allSongsInPlaylist =driver.findElements(By.cssSelector(".song-list-wrap.main-scroll-wrap.playlist td.title"));
        //This block is for visibility - printing all the songs
        for (WebElement element: allSongsInPlaylist)
        {
            System.out.println(element.getText());
        }
        Assert.assertEquals(allSongsInPlaylist.size(),3);
    }
    public void clickOnCreateNewPlaylist() {
        wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("[data-testid='playlist-context-menu-create-simple']")))
                .click();
    }

    public void clickOnCreatePlaylistBtn() {
        wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("[data-testid='sidebar-create-playlist-btn']")))
                .click();
    }

    public void enterNewPlaylistName(String newPlayListName) {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
//       clear() does not work, element has an attribute of "required"
//       workaround is ctrl A (to select all) then backspace to clear then replace with new playlist name
//        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL,"A", Keys.BACK_SPACE));
        //FOR MAC
        playlistInputField.sendKeys(Keys.chord(Keys.COMMAND,"A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlayListName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public void clickOnDeletePlaylistBtn() {
        WebElement deletePlaylistBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".del.btn-delete-playlist")));
        actions.click(deletePlaylistBtn).perform();
    }

    public void clickOnPlaylist(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + playlistName + "')]"))).click();
    }

    public void doubleClickOnPlaylist(String playlistName) {
        WebElement playlistElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + playlistName + "')]")));
        actions.doubleClick(playlistElement).perform();
    }

    public void checkMessage(String playListName) {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Updated playlist')]")));
        Assert.assertEquals("Updated playlist \""+playListName+".\"",notification.getText());
    }
}