import Pages.LoginPage;
import Pages.PlaylistPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework22 extends BaseTest{
    LoginPage loginPage;
    PlaylistPage playlistPage;
    public String playListName ;
    @BeforeClass
    void login(){
        loginPage = new LoginPage(driver);
        playlistPage = new PlaylistPage(driver);
        loginPage.loginCorrectCred();
        playListName ="DominicasPlaylist"+basePage.generateRandomName();
    }
    @Test(priority = 1)
    public void createPlaylist()  {
        playlistPage.clickOnCreatePlaylistBtn();
        playlistPage.clickOnCreateNewPlaylist();
        playlistPage.enterNewPlaylistName(playListName);
        basePage.checkShowSuccess();
    }
    @Test(priority = 2)
    public void renamePlaylist()  {
        playlistPage.doubleClickOnPlaylist(playListName);
        playListName ="Updated"+ playListName;
        playlistPage.enterNewPlaylistName(playListName);
        playlistPage.checkMessage(playListName);
    }

    @Test(priority = 3)
    public void deletePlaylist()  {
        playlistPage.clickOnPlaylist(playListName);
        playlistPage.clickOnDeletePlaylistBtn();
        basePage.checkShowSuccess();
    }
}