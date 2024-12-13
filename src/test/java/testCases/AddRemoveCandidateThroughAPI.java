package testCases;

import baseTest.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import objectModelsForAPIs.AddCandidateRequestModel;
import objectModelsForAPIs.AddEmployeeRequestModel;
import objectModelsForAPIs.DeleteCandidateRequestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import yehiaEngine.managers.JsonManager;
import yehiaEngine.managers.SessionManager;

import java.util.Arrays;

import static yehiaEngine.driverManager.BrowserFactory.getDriver;

public class AddRemoveCandidateThroughAPI extends BaseTest {
    //Variables
    String jsonFilePathForAddUser = "src/test/resources/TestDataJsonFiles/AddRemoveCandidateThroughAPI_TestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddUser);
    JsonManager session = new JsonManager(jsonFilePathForSessionDataForAdmin);
    int candidateID;

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

    @Test
    public void addRemoveNewCandidateWithRandomDataThroughAPI() throws JsonProcessingException {
        candidateID =
        new AddCandidateRequestModel()
                .prepareAddCandidateRequestWithRandomData()
                .sendAddCandidateRequest(session.getData("cookies_data[0].name")+"="+session.getData("cookies_data[0].value"))
                .validateCandidateDetailsFromResponse()
                .validateResponseCodeFromResponse(Integer.parseInt(json.getData("APIResponseCodes.AddCandidate")))
                .getCandidateIDFromResponse();

        new DeleteCandidateRequestModel()
                .prepareDeleteCandidateRequest(Arrays.asList(candidateID))
                .sendDeleteCandidateRequest(session.getData("cookies_data[0].name")+"="+session.getData("cookies_data[0].value"))
                .validateResponseCodeFromResponse(Integer.parseInt(json.getData("APIResponseCodes.RemoveCandidate")))
                .validateRemovedIDsFromResponse();
    }
}
