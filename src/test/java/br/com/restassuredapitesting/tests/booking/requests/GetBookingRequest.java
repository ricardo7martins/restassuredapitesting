package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.ExtractableResponse;
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

    @Step("Retorna um booking específico")
    public Response getBooking(int id) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking/" + id);
    }

    @Step("Retorna a primeira reserva na lista")
    public Response getFirstBooking() {
        return given()
                .header("Accept", "application/json")
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

    public String getValueFromFirstBooking(String key) {
        ExtractableResponse<Response> firstBooking = getFirstBooking().then().extract();

        if (key.equals("checkin")) {
            key = "bookingdates.get(\"checkin\")";
        } else if (key.equals("checkout")) {
            key = "bookingdates.get(\"checkout\")";
        }
        return firstBooking.path(key);
    }

    public String getValueFromBooking(int bookingId, String key) {
        ExtractableResponse<Response> firstBooking = new GetBookingRequest()
                .getBooking(bookingId)
                .then()
                .extract();

        if (key.equals("checkin")) {
            key = "bookingdates.get(\"checkin\")";
        } else if (key.equals("checkout")) {
            key = "bookingdates.get(\"checkout\")";
        }
        return firstBooking.path(key);
    }
}
