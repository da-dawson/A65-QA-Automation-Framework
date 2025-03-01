package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
//    public HomePage(WebDriver driver) {
//        super(driver);
//    }

    public void clickOnPlaylist(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + playlistName + "')]"))).click();
    }

    public void clickAllSongs() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li a.songs"))).click();
    }

    public void clickPlay() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".playback"))).click();
    }

    public void checkSongIsPlaying() {
        WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='sound-bar-play']")));
        Assert.assertTrue(soundBar.isDisplayed());
    }

    public void checkIfPlayBtnIsVisible() {
        WebElement playBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='play-btn']")));
        Assert.assertTrue(playBtn.isDisplayed());
    }

    public void mouseMoveToPlayBtn() {
        WebElement playPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".side.player-controls")));
        actions.moveToElement(playPanel).click(playPanel).perform();
    }

    public void contextClickSongByName(String songName) {
        WebElement song = wait.until(ExpectedConditions.
                elementToBeClickable(By.xpath("//section[@id='songsWrapper']//td[text()='" + songName + "']")));
        actions.contextClick(song).perform();
    }
}