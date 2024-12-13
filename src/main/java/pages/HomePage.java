package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomSoftAssert;
import yehiaEngine.elementActions.WebElementsActions;

import static yehiaEngine.browserActions.WindowManager.navigateToURL;
import static yehiaEngine.managers.PropertiesManager.getPropertiesValue;

public class HomePage {
    //Variables
    WebDriver driver;
    WebElementsActions action;

    //Locators
    final private By logoImage = By.cssSelector(".oxd-brand-banner img");
    final private By dashboardTab = By.partialLinkText("Dashboard");
    final private By adminTab = By.partialLinkText("Admin");
    final private By recruitmentTab = By.partialLinkText("Recruitment");


    //Constructor
    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        action = new WebElementsActions(driver);
    }

    //Actions
    @Step("Open Dashboard Page")
    public DashboardPage openDashboardPage()
    {
        action.press(dashboardTab);
        return new DashboardPage(driver);
    }

    @Step("Open Admin Page")
    public AdminPage openAdminPage()
    {
        action.press(adminTab);
        return new AdminPage(driver);
    }

    @Step("Open Recruitment Page")
    public RecruitmentPage openRecruitmentPage()
    {
        action.press(recruitmentTab);
        return new RecruitmentPage(driver);
    }

    //Validations
    @Step("Verify Home Page is Opened")
    public HomePage verifyHomePageIsOpened()
    {
        verifyWebsiteLogoIsDisplayed();
        return this;
    }

    //Private Methods
    @Step("verify Website Logo Is Displayed")
    private HomePage verifyWebsiteLogoIsDisplayed()
    {
        CustomSoftAssert.assertTrue(action.isElementDisplayed(logoImage));
        return this;
    }
}
