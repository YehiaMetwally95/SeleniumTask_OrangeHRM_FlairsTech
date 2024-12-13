package objectModelsForAPIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojoClassesForAPIs.DeleteCandidateRequestPojo;
import pojoClassesForAPIs.DeleteCandidateResponsePojo;

import java.util.Arrays;
import java.util.List;

import static yehiaEngine.managers.ApisManager.MakeAuthRequest;
import static yehiaEngine.managers.ApisManager.getResponseBody;
import static yehiaEngine.managers.PropertiesManager.getPropertiesValue;
import static yehiaEngine.utilities.RandomDataGenerator.*;

public class DeleteCandidateRequestModel {

    //Variables
    String registerEndpoint = getPropertiesValue("baseUrlApi")+"recruitment/candidates";
    String jsonBodyAsString;
    Response response;
    JsonMapper mapper;

    //ObjectsFromPojoClasses
    DeleteCandidateRequestPojo requestObject;
    DeleteCandidateResponsePojo responseObject;

    //Method to set Request Body with
    @Step("Prepare Delete Candidate Request Body")
    public DeleteCandidateRequestModel prepareDeleteCandidateRequest(List<Integer> ids) {
        requestObject = DeleteCandidateRequestPojo.builder()
                .ids(ids)
                .build();
        return this;
    }

    //Method to Execute Delete Candidate Request
    @Step("Send Request of Delete Candidate")
    public DeleteCandidateResponseModel sendDeleteCandidateRequest(String cookie) throws JsonProcessingException {
        response =
                MakeAuthRequest("Delete", registerEndpoint, requestObject,"application/json"
                        ,"CookieAuth",null,null,cookie);
        jsonBodyAsString = getResponseBody(response);

        mapper = new JsonMapper();
        responseObject = mapper.readValue(jsonBodyAsString, DeleteCandidateResponsePojo.class);

        return new DeleteCandidateResponseModel(requestObject, responseObject,response);
    }

}
