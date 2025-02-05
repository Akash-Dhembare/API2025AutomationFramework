package com.akash.dhembare2000.modules;

import com.akash.dhembare2000.pojos.Booking;
import com.akash.dhembare2000.pojos.BookingDates;
import com.akash.dhembare2000.pojos.BookingResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

public class PayloadManager {
    // Serialization and Deserialization
    Gson gson;

    // Converting the JAVA object to the String
    // {}

    public String createPayloadBookingAsString(){
        Booking booking=new Booking();
        booking.setFirstname("Akash");
        booking.setLastname("Dhembare");
        booking.setTotalprice(222);
        booking.setDepositpaid(true);

        BookingDates bookingdates=new BookingDates();
        bookingdates.setCheckin("2024-02-02");
        bookingdates.setCheckout("2025-01-02");

        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        // JAVA Object -> JSON String (byteStream) - Serialization
        gson=new Gson();
        String jsonStringpayload=gson.toJson(booking);
        System.out.println(jsonStringpayload);

        return jsonStringpayload;
    }

    public String createPayloadBookingAsStringFaker(){

        Faker faker= new Faker();
        Booking booking=new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setDepositpaid(faker.random().nextBoolean());

        BookingDates bookingdates=new BookingDates();
        bookingdates.setCheckin("2024-02-02");
        bookingdates.setCheckout("2025-01-02");

        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds(faker.food().dish());

        System.out.println(booking);

        // JAVA Object -> JSON String (byteStream) - Serialization
        gson=new Gson();
        String jsonStringpayload=gson.toJson(booking);
        System.out.println(jsonStringpayload);

        return jsonStringpayload;
    }

    public BookingResponse bookingResponseJava(String responseString){
        gson=new Gson();
        BookingResponse bookingResponse= gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }
}
