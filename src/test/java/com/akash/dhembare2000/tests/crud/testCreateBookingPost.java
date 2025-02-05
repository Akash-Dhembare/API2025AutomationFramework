package com.akash.dhembare2000.tests.crud;

import com.akash.dhembare2000.base.BaseTest;
import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class testCreateBookingPost extends BaseTest {

    @Owner("Akash Dhembare")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that the post request is working fine")
    @Test
    public void testVerifyCreateBookingPOST(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response= RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Default -  Hamcrest
        validatableResponse.body("booking.firstname", Matchers.equalTo("Akash"));

        BookingResponse bookingResponse=payloadManager.bookingResponseJava(response.asString());

        // TestNG Assertions
        assertActions.verifyStatusCode(response, 200);

        // AssertJ Assertions
        assertThat(bookingResponse.getBookingid()).isNotNull().isNotZero();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Akash");
        assertThat(bookingResponse.getBooking().getFirstname()).isNotEmpty().isNotNull().isNotBlank();
    }
}
