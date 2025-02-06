package com.akash.dhembare2000.base;

import com.akash.dhembare2000.Asserts.AssertActions;
import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

// Common to all Test Cases
// Base Test is Father to Every Test Case -> Testcase - Son - Single Inheritance
public class BaseTest {

    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;

    // TC - Header
    @BeforeTest
    public void setUp(){
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = new RequestSpecBuilder().setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json")
                .build().log().all();

        // OR

//        requestSpecification= RestAssured.given();
//        requestSpecification.baseUri(APIConstants.BASE_URL).contentType(ContentType.JSON).log().all();

    }

    public String getToken(){
        requestSpecification=RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        // Setting the payload
        String payload= payloadManager.setAuthPayload();

        // Get the token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        // Token Extraction
        String token=payloadManager.getTokenFromJSON(response.asString());

        return token;
    }

}
