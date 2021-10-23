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

    @Step("Retorna o Id da primeira reserva na lista")
    public int getFirstId() {
        return bookingReturnIds()
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
                .get("booking/" + getFirstId());
    }

    @Step("Filtra a lista de reservas por um filtro")
    public Response getBookingsBySingleFilter(String filter, String value) {
        return given()
                .when()
                .get("booking/?" + filter + "=" + value);
//                .get(String.format("booking/?%s=%s", filter, value));
    }

    @Step("Filtra a lista de reservas por dois filtros")
    public Response getBookingsByTwoFilters(String firstFilter, String firstValue,
                                            String secondFilter, String secondValue) {
        return given()
                .when()
                .get(String.format("booking/?%s=%s&%s=%s", firstFilter, firstValue,
                        secondFilter, secondValue));
    }

    @Step("Filtra a lista de reservas por três filtros")
    public Response getBookingsByThreeFilters(String firstFilter, String firstValue,
                                              String secondFilter, String secondValue,
                                              String thirdFilter, String thirdValue) {
        return given()
                .when()
                .get(String.format("booking/?%s=%s&%s=%s&%s=%s", firstFilter, firstValue,
                        secondFilter, secondValue, thirdFilter, thirdValue));
    }
}
