package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {


    @Step("Retorna os Id da listagem de reservas")
    public Response getAllBookings() {
        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna o Id da primeira reserva na lista")
    public int getFirstBookingId() {
        return getAllBookings()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
    }

    public Response getBooking(String id) {
        return given()
                .when()
                .get("booking/" + id);
    }

    @Step("Retorna a primeira reserva na lista")
    public Response getFirstBooking() {
        return given()
                .when()
                .get("booking/" + getFirstBookingId());
    }

    @Step("Filtra a lista de reservas por um filtro")
    public Response getBookingsByFilters(String filter, String value) {
        return given()
                .queryParams(filter, value)
                .when()
                .get("booking");
    }


    @Step("Filtra a lista de reservas por dois filtros")
    public Response getBookingsByFilters(String firstFilter, String firstValue,
                                         String secondFilter, String secondValue) {
        return given()
                .queryParams(firstFilter, firstValue, secondFilter, secondValue)
                .when()
                .get("booking");
    }

    @Step("Filtra a lista de reservas por três filtros")
    public Response getBookingsByFilters(String firstFilter, String firstValue,
                                         String secondFilter, String secondValue,
                                         String thirdFilter, String thirdValue) {
        return given()
                .queryParams(firstFilter, firstValue, secondFilter, secondValue,
                        thirdFilter, thirdValue)
                .when()
                .get("booking");
    }
}
