import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework17 extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException {
        String newSongAddedNotificationText = "Added 1 song into";
        navigateToPage();
        enterEmail("dominica.dawson@testpro.io");
        enterPassword("H3lpMe2Te$tPle@se!");
        clickSubmit();
        searchSong("Dark Days");
        clickViewAllBtn();
        selectFirstSongResult();
        clickAddToBtn();
        choosePlayList();
        Assert.assertTrue(getNotificationText().contains(newSongAddedNotificationText));

    }
}