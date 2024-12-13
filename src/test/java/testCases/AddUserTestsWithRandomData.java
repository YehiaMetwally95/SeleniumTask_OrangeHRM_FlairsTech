package testCases;

import baseTest.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import objectModelsForAPIs.AddEmployeeRequestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;
import pages.UserCreationPage;
import yehiaEngine.managers.JsonManager;
import yehiaEngine.managers.SessionManager;

import static yehiaEngine.driverManager.BrowserFactory.getDriver;

public class AddUserTestsWithRandomData extends BaseTest {
    //Variables
    String jsonFilePathForAddUser = "src/test/resources/TestDataJsonFiles/AddUserTestsWithRandomData_TestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddUser);
    JsonManager session = new JsonManager(jsonFilePathForSessionDataForAdmin);
    String employeeFullName;
    String employeeUsername;
    int oldTotalRecords;
    int newTotalRecords;

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
    }

    @Test
    public void addNewUserWithRandomData()
    {
        //Open Admin Page and Get Current Total Records
        oldTotalRecords =
        new HomePage(getDriver(isolatedDriver))
                .openAdminPage()
                .verifyAdminPageIsOpened(json.getData("Headers.AdminHeader"))
                .getCurrentTotalNumberOfRecords();

        //Navigate to User Creation Page
        new AdminPage(getDriver(isolatedDriver))
                .navigateToUserCreationPage();

        //Add New User and Retrieve the Username
        employeeUsername =
        new UserCreationPage(getDriver(isolatedDriver))
                .verifyUserCreationPageIsOpened(json.getData("Headers.AddUserHeader"))
                .addNewUserWithRandomData(employeeFullName)
                .getUsername();

        //Open Admin Page and Get Current Total Records
        newTotalRecords =
        new AdminPage(getDriver(isolatedDriver))
                .getCurrentTotalNumberOfRecords();

        //Assert The Total Records is Increased by 1
        new AdminPage(getDriver(isolatedDriver))
                .assertTotalRecordNumberIncreasedBy1(oldTotalRecords,newTotalRecords);

        //Open Admin Page and Search for User by Username Then Delete it
        //Retrieve Current Total Records
        oldTotalRecords = newTotalRecords;
        newTotalRecords =
        new AdminPage(getDriver(isolatedDriver))
                .searchForUsername(employeeUsername)
                .verifyUsernameDisplayedInSearchResult(employeeUsername)
                .deleteUsernameRecord()
                .getCurrentTotalNumberOfRecords();

        //Assert The Total Records is Decreased by 1
        new AdminPage(getDriver(isolatedDriver))
                .assertTotalRecordNumberDecreasedBy1(oldTotalRecords,newTotalRecords);
    }
}
