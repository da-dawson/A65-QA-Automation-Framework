import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {
    
    @Test
    public void testSimpleNavigation() {
        getDriver().get("https://www.google.com");
        Assert.assertTrue(getDriver().getTitle().toLowerCase().contains("google"), 
            "Failed to navigate to Google");
        System.out.println("Successfully navigated to Google");
    }
}
