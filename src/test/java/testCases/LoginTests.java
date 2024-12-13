package testCases;

import baseTest.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.LoginPage;
import yehiaEngine.managers.JsonManager;
import yehiaEngine.managers.SessionManager;

import static yehiaEngine.driverManager.BrowserFactory.*;

public class LoginTests extends BaseTest {
    //Variables
    String jsonFilePathForLogin = "src/test/resources/TestDataJsonFiles/LoginTests_TestData.json";
    JsonManager json = new JsonManager(jsonFilePathForLogin);

    @Test
    public void loginWithValidUser()
    {
        new LoginPage(getDriver(isolatedDriver))
                .verifyLoginPageIsOpened(json.getData("Headers.LoginHeader"))
                .loginWithValidUser(json.getData("ValidUsers[0].Username"),json.getData("ValidUsers[0].Password"))
                .verifyHomePageIsOpened();

        //Store Cookies into Json File to byPass UI Login for next test and to Authorize APIs
        new SessionManager(getDriver(isolatedDriver), jsonFilePathForSessionDataForAdmin)
                .storeSessionCookies(json.getData("ValidUsers[0].Username"));
    }
}
