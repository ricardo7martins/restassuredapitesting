package br.com.restassuredapitesting.tests.booking.requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {
    public Response bookingReturnIds() {
        return given()
                .when()
                .get("https://treinamento-api.herokuapp.com/booking");
    }

}
