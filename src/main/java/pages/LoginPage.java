package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomSoftAssert;
import yehiaEngine.elementActions.WebElementsActions;

import static yehiaEngine.browserActions.WindowManager.navigateToURL;
import static yehiaEngine.driverManager.BrowserFactory.getDriver;
import static yehiaEngine.managers.PropertiesManager.getPropertiesValue;

public class LoginPage {
    //Variables
    WebDriver driver;
    WebElementsActions action;

    //Locators
    final private By usernameTextBox = By.name("username");
    final private By passwordTextBox = By.name("password");
    final private By loginButton = By.xpath("//button[@type='submit']");
    final private By loginHeader = By.cssSelector("div .orangehrm-login-title");

    //Constructor
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        action = new WebElementsActions(driver);
    }

    //Actions
    @Step("Open Login Page")
    public LoginPage openLoginPage()
    {
        navigateToURL(driver,getPropertiesValue("baseUrlWeb"));
        return this;
    }

    @Step("LoginWithValidUser")
    public HomePage loginWithValidUser(String username,String password)
    {
        fillUsername(username).
                fillPassword(password).
                clickOnLoginButton();
        return new HomePage(driver);
    }

    //Validations
    @Step("Verify Login Page is Opened")
    public LoginPage verifyLoginPageIsOpened(String header)
    {
        verifyLoginHeader(header);
        return this;
    }

    //Private Methods
    @Step("Fill Username")
    private LoginPage fillUsername(String username)
    {
        action.type(usernameTextBox,username);
        return this;
    }

    @Step("Fill Password")
    private LoginPage fillPassword(String password)
    {
        action.type(passwordTextBox,password);
        return this;
    }

    @Step("Verify Login Header")
    private LoginPage verifyLoginHeader(String header)
    {
        CustomSoftAssert.assertEquals(action.readText(loginHeader),header);
        return this;
    }

    @Step("Click on Login Button")
    private HomePage clickOnLoginButton()
    {
        action.press(loginButton);
        return new HomePage(driver);
    }
}
