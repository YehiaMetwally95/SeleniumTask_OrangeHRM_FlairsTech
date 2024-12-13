package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomSoftAssert;

public class DashboardPage extends HomePage {
    //Locators
    final private By dashboardHeader = By.className("oxd-topbar-header-breadcrumb-module");

    //Constructor
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    //Actions

    //Validations
    @Step("Verify Dashboard Page is Opened")
    public DashboardPage verifyDashboardPageIsOpened(String header)
    {
        verifyDashboardHeader(header);
        return this;
    }

    //Private Methods
    @Step("Verify Dashboard Header is Displayed")
    private DashboardPage verifyDashboardHeader(String header)
    {
        CustomSoftAssert.assertEquals(action.readText(dashboardHeader),header);
        return this;
    }
}
