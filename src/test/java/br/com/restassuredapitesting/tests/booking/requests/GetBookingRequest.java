package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna os Id da listagem de reservas")
    public Response bookingReturnIds() {
        return given()
                .when()
                .get("booking");
    }

    public int getFirstId() {
        int first = bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        System.out.println(first);
        return first;
    }

    public Response getFirstBooking() {
        return given()
                .when()
                .get("booking/" + getFirstId());
    }
}
