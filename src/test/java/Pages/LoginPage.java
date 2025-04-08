package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[type='email']")
    private WebElement emailField;

    @FindBy(css = "[type='password']")
    private WebElement passwordField;

    @FindBy(css = "[type='Submit']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginCorrectCred() {
        provideEmail("dominica.dawson@testpro.io")
                .providePassword("H3lpMe2Te$tPle@se!")
                .clickSubmit();
    }

    public LoginPage provideEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmit() {
        submitButton.click();
        return this;
    }
}
