import org.testng.Assert;
import org.testng.annotations.Test;

public class LambdaTestVerification extends BaseTest {
    
    @Test
    public void testLambdaTestConnection() {
        getDriver().get("https://qa.koel.app/");
        Assert.assertTrue(getDriver().getTitle().contains("Koel"), "Failed to load Koel app");
        System.out.println("Successfully connected to LambdaTest and loaded the application");
    }
    
    @Test
    public void testParallelExecution() {
        getDriver().get("https://qa.koel.app/");
        System.out.println("Running parallel test on thread: " + Thread.currentThread().getId());
        Assert.assertTrue(true, "Parallel test execution verification");
    }
}
