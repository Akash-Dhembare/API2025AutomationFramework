package com.akash.dhembare2000.tests.integration;

import com.akash.dhembare2000.base.BaseTest;
import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.listeners.RetryAnalyzer;
import com.akash.dhembare2000.pojos.Booking;
import com.akash.dhembare2000.pojos.BookingResponse;
import com.akash.dhembare2000.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test(retryAnalyzer = RetryAnalyzer.class)
public class TCIntegrationFlowRetry extends BaseTest {
    // Create a Booking, Create a Token
    // Get booking
    // Update the Booking
    // Delete the Booking

    @Owner("Akash Dhembare")
    @Description("TC#1 - Step 1. Verify that the Booking can be Created")
    @Test (priority = 1)
    public void testCreateBooking(ITestContext iTestContext){
        iTestContext.setAttribute("token", getToken());

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response= RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse=response.then().log().all();

        // Validatable Assertions
        validatableResponse.statusCode(200);
       // validatableResponse.body("booking.firstname", Matchers.equalTo("Akash"));

        // Deser
        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());

        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotBlank().isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));

        // Set the booking ID
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());

    }

    @Owner("Akash Dhembare")
    @Description("TC#2 - Step 2. Verify that the Booking by ID")
    @Test (priority = 2)
    public void testVerifyBookingId(ITestContext iTestContext){
        // Booing ID
        Integer bookingID=(Integer) iTestContext.getAttribute("bookingid");

        // GET Req
        String basePathGet=APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID;
        System.out.println(basePathGet);

        requestSpecification.basePath(basePathGet);

        response=RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all();

        // Validatable Assertions
        validatableResponse.statusCode(200);

        Booking booking= payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.get.firstname"));
        assertThat(booking.getFirstname()).isNotEmpty().isNotNull();
    }

    @Owner("Akash Dhembare")
    @Description("TC#3 - Step 3. Verify Updated Booking by ID")
    @Test (priority = 3)
    public void testUpdateBookingByID(ITestContext iTestContext){
        System.out.println("Token - "+ iTestContext.getAttribute("token"));
        String token= (String) iTestContext.getAttribute("token");
        Integer bookingid=(Integer) iTestContext.getAttribute("bookingid");

        String basePathPUTPATCH= APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response=RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

        validatableResponse = response.then().log().all();

        // Validatable Assertions
        validatableResponse.statusCode(200);

        Booking booking= payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotEmpty();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.put.firstname"));
        assertThat(booking.getLastname()).isEqualTo(PropertyReader.readKey("booking.put.lastname"));
    }

    @Owner("Akash Dhembare")
    @Description("TC#4 - Step 4. Delete the booking by ID")
    @Test (priority = 4)
    public void testDeleteBookingByID(ITestContext iTestContext){
        String token=(String) iTestContext.getAttribute("token");
        Assert.assertTrue(true);

        Integer bookingid=(Integer) iTestContext.getAttribute("bookingid");
        String basePathDELETE=APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(basePathDELETE);

        requestSpecification.basePath(basePathDELETE).cookie("token", token);

        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(200);
    }


}
