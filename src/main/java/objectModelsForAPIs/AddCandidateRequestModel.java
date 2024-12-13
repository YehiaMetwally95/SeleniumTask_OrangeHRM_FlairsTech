package objectModelsForAPIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojoClassesForAPIs.AddCandidateRequestPojo;
import pojoClassesForAPIs.AddCandidateResponsePojo;
import pojoClassesForAPIs.AddEmployeeRequestPojo;
import pojoClassesForAPIs.AddEmployeeResponsePojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static yehiaEngine.managers.ApisManager.MakeAuthRequest;
import static yehiaEngine.managers.ApisManager.getResponseBody;
import static yehiaEngine.managers.PropertiesManager.getPropertiesValue;
import static yehiaEngine.utilities.RandomDataGenerator.*;

public class AddCandidateRequestModel {

    //Variables
    String registerEndpoint = getPropertiesValue("baseUrlApi")+"recruitment/candidates";
    String jsonBodyAsString;
    Response response;
    JsonMapper mapper;

    //ObjectsFromPojoClasses
    AddCandidateRequestPojo requestObject;
    AddCandidateResponsePojo responseObject;

    //Method to set Request Body with Random Data
    @Step("Prepare Add Candidate Request Body With Random Data")
    public AddCandidateRequestModel prepareAddCandidateRequestWithRandomData() {
        requestObject = AddCandidateRequestPojo.builder()
                .firstName(generateName())
                .middleName(generateName())
                .lastName(generateName())
                .email(generateUniqueEmail())
                .contactNumber(generateUniqueInteger())
                .keywords(generateCompany())
                .comment(generateDescription())
                .dateOfApplication(generatePreviousDate())
                .consentToKeepData(generateItemFromList(Arrays.asList(true,false)))
                .vacancyId(generateItemFromList(Arrays.asList(1,2,3,5,6,7,8)))
                .build();
        return this;
    }

    //Method to Execute Add Candidate Request
    @Step("Send Request of Add Candidate")
    public AddCandidateResponseModel sendAddCandidateRequest(String cookie) throws JsonProcessingException {
        response =
                MakeAuthRequest("Post", registerEndpoint, requestObject,"application/json"
                        ,"CookieAuth",null,null,cookie);
        jsonBodyAsString = getResponseBody(response);

        mapper = new JsonMapper();
        responseObject = mapper.readValue(jsonBodyAsString, AddCandidateResponsePojo.class);

        return new AddCandidateResponseModel(requestObject, responseObject,response);
    }

}
