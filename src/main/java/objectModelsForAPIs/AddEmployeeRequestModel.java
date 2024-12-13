package objectModelsForAPIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojoClassesForAPIs.AddEmployeeRequestPojo;
import pojoClassesForAPIs.AddEmployeeResponsePojo;
import yehiaEngine.managers.JsonManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static yehiaEngine.managers.ApisManager.*;
import static yehiaEngine.managers.PropertiesManager.getPropertiesValue;
import static yehiaEngine.utilities.RandomDataGenerator.*;
import static yehiaEngine.utilities.RandomDataGenerator.generateUniqueInteger;

public class AddEmployeeRequestModel {

    //Variables
    String registerEndpoint = getPropertiesValue("baseUrlApi")+"pim/employees";
    String jsonBodyAsString;
    Response response;
    JsonMapper mapper;

    //ObjectsFromPojoClasses
    AddEmployeeRequestPojo requestObject;
    AddEmployeeResponsePojo responseObject;

    //Method to set Request Body with Random Data
    @Step("Prepare AddEmployee Request Body With Random Data")
    public AddEmployeeRequestModel prepareAddEmployeeRequestWithRandomData() {
        requestObject = AddEmployeeRequestPojo.builder()
                .firstName(generateName())
                .middleName(generateName())
                .lastName(generateName())
                .empPicture(null)
                .employeeId(generateUniqueInteger())
                .build();
        return this;
    }

    //Method to Execute AddEmployee Request
    @Step("Send Request of Add Employee")
    public AddEmployeeResponseModel sendAddEmployeeRequest(String cookie) throws JsonProcessingException {
        response =
                MakeAuthRequest("Post", registerEndpoint, requestObject,"application/json"
                        ,"CookieAuth",null,null,cookie);
        jsonBodyAsString = getResponseBody(response);

        mapper = new JsonMapper();
        responseObject = mapper.readValue(jsonBodyAsString, AddEmployeeResponsePojo.class);

        return new AddEmployeeResponseModel(requestObject, responseObject,response);
    }

}
