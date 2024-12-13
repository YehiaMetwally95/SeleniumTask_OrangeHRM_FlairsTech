package testCases;

import baseTest.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import objectModelsForAPIs.AddEmployeeRequestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import yehiaEngine.managers.JsonManager;
import yehiaEngine.managers.SessionManager;

import static yehiaEngine.driverManager.BrowserFactory.getDriver;
import static yehiaEngine.utilities.RandomDataGenerator.generateUniqueName;

public class AddUserTestsWithStaticData extends BaseTest {
    //Variables
    String jsonFilePathForAddUser = "src/test/resources/TestDataJsonFiles/AddUserTestsWithStaticData_TestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddUser);
    JsonManager session = new JsonManager(jsonFilePathForSessionDataForAdmin);
    String employeeFullName;

/*    @BeforeMethod
    public void byPassUILogin()
    {
        new SessionManager(getDriver(isolatedDriver), jsonFilePathForSessionDataForAdmin).applyCookiesToCurrentSession();
    }*/

    @BeforeMethod
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

    @BeforeMethod (dependsOnMethods = {"loginWithValidUser"})
    public void addNewEmployeeWithRandomDataThroughAPI() throws JsonProcessingException {
        employeeFullName = new AddEmployeeRequestModel()
                .prepareAddEmployeeRequestWithRandomData()
                .sendAddEmployeeRequest(session.getData("cookies_data[0].name")+"="+session.getData("cookies_data[0].value"))
                .validateResponseCodeFromResponse(Integer.parseInt(json.getData("APIResponseCodes.AddEmployee")))
                .validateEmployeeDetailsFromResponse()
                .getEmployeeFullName();
        json.setData("NewUserInfo.EmployeeName",employeeFullName);

        //Add Unique Username to Test Data JsonFile to avoid Duplication Errors
        json.setData("NewUserInfo.UserName",generateUniqueName());
    }

    @Test
    public void addNewUserWithStaticData()
    {
        //Open Admin Page and Save Current Total Records into Json File
        new HomePage(getDriver(isolatedDriver))
                .openAdminPage()
                .verifyAdminPageIsOpened(json.getData("Headers.AdminHeader"))
                .saveCurrentTotalNumberOfRecords("TotalEmployeeRecords.OldValue")
        //Navigate to User Creation Page and Create New User
                .navigateToUserCreationPage()
                .verifyUserCreationPageIsOpened(json.getData("Headers.AddUserHeader"))
                .addNewUser(json.getData("NewUserInfo.UserRole"),json.getData("NewUserInfo.EmployeeName"),
                        json.getData("NewUserInfo.Status"),json.getData("NewUserInfo.UserName"),
                        json.getData("NewUserInfo.Password"),json.getData("NewUserInfo.ConfirmPassword"))
        //Save Current Total Records into Json File
                .saveCurrentTotalNumberOfRecords("TotalEmployeeRecords.NewValue")

        //Assert The Total Records is Increased by 1
                .assertTotalRecordNumberIncreasedBy1()
        //Save Current Total Records into Json File
                .saveCurrentTotalNumberOfRecords("TotalEmployeeRecords.OldValue")
        //Search for User by Username Then Delete it
                .searchForUsername(json.getData("NewUserInfo.UserName"))
                .verifyUsernameDisplayedInSearchResult(json.getData("NewUserInfo.UserName"))
                .deleteUsernameRecord()
        //Save Current Total Records into Json File
                .saveCurrentTotalNumberOfRecords("TotalEmployeeRecords.NewValue")
        //Assert The Total Records is Decreased by 1
                .assertTotalRecordNumberDecreasedBy1();
    }
}
