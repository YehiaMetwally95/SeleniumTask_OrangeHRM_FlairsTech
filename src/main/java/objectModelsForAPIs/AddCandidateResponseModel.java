package objectModelsForAPIs;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojoClassesForAPIs.AddCandidateRequestPojo;
import pojoClassesForAPIs.AddCandidateResponsePojo;
import yehiaEngine.assertions.CustomSoftAssert;

import static yehiaEngine.managers.ApisManager.getResponseCode;

public class AddCandidateResponseModel {
    //ObjectsFromPojoClasses
    AddCandidateRequestPojo requestObject;
    AddCandidateResponsePojo responseObject;
    Response response;

    //Constructor to pass the response from Request Model to Response Model
    public AddCandidateResponseModel(AddCandidateRequestPojo requestObject, AddCandidateResponsePojo responseObject , Response response) {
        this.requestObject = requestObject;
        this.responseObject = responseObject;
        this.response = response;
    }

    //Validation Methods
    @Step("Validate Response Code from Response")
    public AddCandidateResponseModel validateResponseCodeFromResponse(int responseCode) {
        CustomSoftAssert.assertEquals(getResponseCode(response),responseCode);
        return this;
    }

    @Step("Validate Candidate Details from Response")
    public AddCandidateResponseModel validateCandidateDetailsFromResponse() {
        validateFirstnameFromResponse()
                .validateMiddlenameFromResponse()
                .validateLastnameFromResponse()
                .validateEmailFromResponse();
        return this;
    }

    @Step("Validate Firstname  from Response")
    private AddCandidateResponseModel validateFirstnameFromResponse() {
        CustomSoftAssert.assertEquals(responseObject.getData().getFirstName(),requestObject.getFirstName());
        return this;
    }

    @Step("Validate Middlename  from Response")
    private AddCandidateResponseModel validateMiddlenameFromResponse() {
        CustomSoftAssert.assertEquals(responseObject.getData().getMiddleName(),requestObject.getMiddleName());
        return this;
    }

    @Step("Validate Lastname  from Response")
    private AddCandidateResponseModel validateLastnameFromResponse() {
        CustomSoftAssert.assertEquals(responseObject.getData().getLastName(),requestObject.getLastName());
        return this;
    }

    @Step("Validate Email from Response")
    private AddCandidateResponseModel validateEmailFromResponse() {
        CustomSoftAssert.assertEquals(responseObject.getData().getEmail(),requestObject.getEmail());
        return this;
    }

    //Getter Methods
    public AddCandidateRequestPojo getRequestPojoObject() {
        return requestObject;
    }

    public AddCandidateResponsePojo getResponsePojoObject() {
        return responseObject;
    }

    @Step("Get Candidate ID from Response")
    public int getCandidateIDFromResponse() {
        return responseObject.getData().getId();
    }
}
