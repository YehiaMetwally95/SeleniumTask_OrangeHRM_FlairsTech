package objectModelsForAPIs;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojoClassesForAPIs.DeleteCandidateRequestPojo;
import pojoClassesForAPIs.DeleteCandidateResponsePojo;
import yehiaEngine.assertions.CustomSoftAssert;

import static yehiaEngine.managers.ApisManager.getResponseCode;

public class DeleteCandidateResponseModel {
    //ObjectsFromPojoClasses
    DeleteCandidateRequestPojo requestObject;
    DeleteCandidateResponsePojo responseObject;
    Response response;

    //Constructor to pass the response from Request Model to Response Model
    public DeleteCandidateResponseModel(DeleteCandidateRequestPojo requestObject, DeleteCandidateResponsePojo responseObject , Response response) {
        this.requestObject = requestObject;
        this.responseObject = responseObject;
        this.response = response;
    }

    //Validation Methods
    @Step("Validate Response Code from Response")
    public DeleteCandidateResponseModel validateResponseCodeFromResponse(int responseCode) {
        CustomSoftAssert.assertEquals(getResponseCode(response),responseCode);
        return this;
    }

    @Step("Validate Removed IDs from Response")
    public DeleteCandidateResponseModel validateRemovedIDsFromResponse() {
        CustomSoftAssert.assertEquals(responseObject.getData().get(0),String.valueOf(requestObject.getIds().get(0)));
        return this;
    }

    //Getter Methods
    public DeleteCandidateRequestPojo getRequestPojoObject() {
        return requestObject;
    }

    public DeleteCandidateResponsePojo getResponsePojoObject() {
        return responseObject;
    }
}
