package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomSoftAssert;

public class RecruitmentPage extends HomePage{
    //Locators
    final private By recruitmentHeader = By.className("oxd-topbar-header-breadcrumb-module");

    //Constructor
    public RecruitmentPage(WebDriver driver) {
        super(driver);
    }

    //Actions

    //Validations
    @Step("Verify Recruitment Page is Opened")
    public RecruitmentPage verifyRecruitmentPageIsOpened(String header)
    {
        verifyRecruitmentHeader(header);
        return this;
    }

    //Private Methods
    @Step("Verify Recruitment Header is Displayed")
    private RecruitmentPage verifyRecruitmentHeader(String header)
    {
        CustomSoftAssert.assertEquals(action.readText(recruitmentHeader),header);
        return this;
    }
}
