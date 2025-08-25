package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        String url = "https://qa.koel.app/";
        getDriver().get(url);
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
        System.out.println("Successfully navigated to: " + url);
        Thread.sleep(2000);
    }
}
