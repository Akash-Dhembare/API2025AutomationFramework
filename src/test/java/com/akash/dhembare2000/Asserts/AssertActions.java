package com.akash.dhembare2000.Asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class AssertActions {
    // Common assertions which can be reused.

    public void verifyResponseBody(String actual, String expected, String description){
        assertEquals(actual , expected, description);
    }

    public void verifyResponseBody(int actual, int expected, String description){
        assertEquals(actual , expected, description);
    }

    public void verifyStatusCode(Response response, Integer expected){
        assertEquals(response.getStatusCode(), expected);
    }
}
