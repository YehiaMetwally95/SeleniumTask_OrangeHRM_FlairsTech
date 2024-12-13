package objectModelsForAPIs;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojoClassesForAPIs.AddEmployeeRequestPojo;
import pojoClassesForAPIs.AddEmployeeResponsePojo;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;

import static yehiaEngine.managers.ApisManager.*;

public class AddEmployeeResponseModel {
    //ObjectsFromPojoClasses
    AddEmployeeRequestPojo requestObject;
    AddEmployeeResponsePojo responseObject;
    Response response;

    //Constructor to pass the response from Request Model to Response Model
    public AddEmployeeResponseModel(AddEmployeeRequestPojo requestObject, AddEmployeeResponsePojo responseObject , Response response) {
        this.requestObject = requestObject;
        this.responseObject = responseObject;
        this.response = response;
    }

    //Validation Methods
    @Step("Validate Response Code from Response")
    public AddEmployeeResponseModel validateResponseCodeFromResponse(int responseCode) {
        CustomSoftAssert.assertEquals(getResponseCode(response),responseCode);
        return this;
    }

    @Step("Validate Employee Details from Response")
    public AddEmployeeResponseModel validateEmployeeDetailsFromResponse() {
        validateFirstnameFromResponse()
                .validateMiddlenameFromResponse()
                .validateLastnameFromResponse()
                .validateEmployeeIDFromResponse();
        return this;
    }

    @Step("Validate Firstname  from Response")
    private AddEmployeeResponseModel validateFirstnameFromResponse() {
        CustomSoftAssert.assertEquals(responseObject.getData().getFirstName(),requestObject.getFirstName());
        return this;
    }

    @Step("Validate Middlename  from Response")
    private AddEmployeeResponseModel validateMiddlenameFromResponse() {
        CustomSoftAssert.assertEquals(responseObject.getData().getMiddleName(),requestObject.getMiddleName());
        return this;
    }

    @Step("Validate Lastname  from Response")
    private AddEmployeeResponseModel validateLastnameFromResponse() {
        CustomSoftAssert.assertEquals(responseObject.getData().getLastName(),requestObject.getLastName());
        return this;
    }

    @Step("Validate Employee ID  from Response")
    private AddEmployeeResponseModel validateEmployeeIDFromResponse() {
        CustomSoftAssert.assertEquals(responseObject.getData().getEmployeeId(),requestObject.getEmployeeId());
        return this;
    }

    //Getter Methods
    public String getEmployeeFullName() {
        return responseObject.getData().getFirstName()
                +" "
                + responseObject.getData().getMiddleName()
                +" "
                + responseObject.getData().getLastName();
    }

    public AddEmployeeRequestPojo getRequestPojoObject() {
        return requestObject;
    }

    public AddEmployeeResponsePojo getResponsePojoObject() {
        return responseObject;
    }
}
