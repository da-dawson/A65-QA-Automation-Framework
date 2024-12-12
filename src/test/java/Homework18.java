import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

    public class Homework18 extends BaseTest {
        @Test
        public void playSong(){

            navigateToPage();
            provideEmail("dominica.dawson@testpro.io");
            providePassword("H3lpMe2Te$tPle@se!");
            clickSubmit();
            clickNextSongBtn();
            clickPlayBtn();
            songPlays();

        }

        private void songPlays() {
            WebElement soundbar = this.getDriver().findElement(By.cssSelector("[data-testid='sound-bar-play']"));
            Assert.assertTrue(soundbar.isDisplayed());
        }

        private void clickPlayBtn() {
            WebElement playBtn = this.getDriver().findElement(By.cssSelector("[data-testid='play-btn']"));
            playBtn.click();
        }

        private void clickNextSongBtn() {
            WebElement NextSongBtn = this.getDriver().findElement(By.cssSelector("[data-testid='play-next-btn']"));
            NextSongBtn.click();
        }
}
