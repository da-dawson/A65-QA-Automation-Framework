import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework23 extends BaseTest {

    private String playListName;

    @BeforeClass
    public void loginAndPreparePlaylistName() {
        loginPage.loginCorrectCred();
        playListName = "DominicasPlaylist_" + homePage.generateRandomName();
    }

    @Test(priority = 1)
    public void createPlaylist() {
        playlistPage.clickOnCreatePlaylistBtn();
        playlistPage.clickOnCreateNewPlaylist();
        playlistPage.enterNewPlaylistName(playListName);
        playlistPage.checkShowSuccess();
    }

    @Test(priority = 2)
    public void renamePlaylist() {
        playlistPage.doubleClickOnPlaylist(playListName);
        playListName = "Updated_" + playListName;
        playlistPage.enterNewPlaylistName(playListName);
        playlistPage.checkMessage(playListName);
    }

    @Test(priority = 3)
    public void deletePlaylist() {
        playlistPage.clickOnPlaylist(playListName);
        playlistPage.clickOnDeletePlaylistBtn();
        playlistPage.checkShowSuccess();
    }
}
