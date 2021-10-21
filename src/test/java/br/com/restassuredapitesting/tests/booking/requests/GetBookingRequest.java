package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.base.BaseTests;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest extends BaseTests {

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
        return first;
    }

    public Response getFirstBooking() {
        return given()
                .when()
                .get("booking/" + getFirstId());
    }

    public Response getBookingsBySingleFilter(String filter, String value) {
        return given()
                .when()
                .get(String.format("booking/?%s=%s", filter, value));
    }

    public Response getBookingsByTwoFilters(String firstFilter, String firstValue,
                                            String secondFilter, String secondValue) {
        return given()
                .when()
                .get(String.format("booking/?%s=%s&%s=%s", firstFilter, firstValue,
                        secondFilter, secondValue));
    }
}
