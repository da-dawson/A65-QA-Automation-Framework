package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HomePage extends BasePage {

    // Static elements with @FindBy
    @FindBy(css = "li a.songs")
    private WebElement allSongsLink;

    @FindBy(css = ".playback")
    private WebElement playButton;

    @FindBy(css = "[data-testid='sound-bar-play']")
    private WebElement soundBar;

    @FindBy(css = "[data-testid='play-btn']")
    private WebElement playBtn;

    @FindBy(css = ".side.player-controls")
    private WebElement playPanel;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Dynamic locator, so we still use By
    public void clickOnPlaylist(String playlistName) {
        By playlist = By.xpath("//a[contains(text(),'" + playlistName + "')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(playlist)).click();
    }

    public void clickAllSongs() {
        wait.until(ExpectedConditions.elementToBeClickable(allSongsLink)).click();
    }

    public void clickPlay() {
        wait.until(ExpectedConditions.elementToBeClickable(playButton)).click();
    }

    public void checkSongIsPlaying() {
        wait.until(ExpectedConditions.visibilityOf(soundBar));
        Assert.assertTrue(soundBar.isDisplayed());
    }

    public void checkIfPlayBtnIsVisible() {
        wait.until(ExpectedConditions.visibilityOf(playBtn));
        Assert.assertTrue(playBtn.isDisplayed());
    }

    public void mouseMoveToPlayBtn() {
        wait.until(ExpectedConditions.visibilityOf(playPanel));
        actions.moveToElement(playPanel).click(playPanel).perform();
    }

    public void contextClickSongByName(String songName) {
        By songLocator = By.xpath("//section[@id='songsWrapper']//td[text()='" + songName + "']");
        WebElement song = wait.until(ExpectedConditions.elementToBeClickable(songLocator));
        actions.contextClick(song).perform();
    }
}