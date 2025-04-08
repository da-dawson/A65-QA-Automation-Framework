package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class PlaylistPage extends BasePage {

    @FindBy(css = "[data-testid='playlist-context-menu-create-simple']")
    private WebElement createNewPlaylistOption;

    @FindBy(css = "[data-testid='sidebar-create-playlist-btn']")
    private WebElement createPlaylistSidebarButton;

    @FindBy(css = "[name='name']")
    private WebElement playlistInputField;

    @FindBy(css = ".del.btn-delete-playlist")
    private WebElement deletePlaylistButton;

    @FindBy(css = ".song-list-wrap.main-scroll-wrap.playlist td.title")
    private List<WebElement> allSongsInPlaylist;

    public PlaylistPage(WebDriver driver) {
        super(driver);
    }

    public void checkNumberOfSongsInPlaylist() {
        wait.until(ExpectedConditions.visibilityOfAllElements(allSongsInPlaylist));
        for (WebElement song : allSongsInPlaylist) {
            System.out.println(song.getText());
        }
        Assert.assertEquals(allSongsInPlaylist.size(), 3);
    }

    public void clickOnCreateNewPlaylist() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewPlaylistOption)).click();
    }

    public void clickOnCreatePlaylistBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(createPlaylistSidebarButton)).click();
    }

    public void enterNewPlaylistName(String newPlayListName) {
        wait.until(ExpectedConditions.visibilityOf(playlistInputField));
        playlistInputField.sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlayListName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public void clickOnDeletePlaylistBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(deletePlaylistButton));
        actions.click(deletePlaylistButton).perform();
    }

    public void clickOnPlaylist(String playlistName) {
        By playlistLocator = By.xpath("//a[contains(text(),'" + playlistName + "')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(playlistLocator)).click();
    }

    public void doubleClickOnPlaylist(String playlistName) {
        By playlistLocator = By.xpath("//a[contains(text(),'" + playlistName + "')]");
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(playlistLocator));
        actions.doubleClick(playlist).perform();
    }

    public void checkMessage(String playListName) {
        WebElement notification = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[contains(text(),'Updated playlist')]")));

        String actualText = notification.getText().trim();
        String expectedText = ("Updated playlist \"" + playListName + ".\"").trim();

        System.out.println("DEBUG -> Expected: " + expectedText);
        System.out.println("DEBUG -> Actual:   " + actualText);

        Assert.assertEquals(actualText, expectedText);
    }

}
