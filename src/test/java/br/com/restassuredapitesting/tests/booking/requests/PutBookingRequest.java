package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayloads bookingPayload = new BookingPayloads();
    GetBookingRequest getBookings = new GetBookingRequest();

    @Step("Atualizar uma reserva com o parâmetro token")
    public Response updateBookingToken(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayload.getBookingPayload().toString())
                .put("booking/" + id);
    }

    @Step("Atualizar uma reserva sem o parâmetro token")
    public Response updateBookingNoToken(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayload.getBookingPayload().toString())
                .put("booking/" + id);
    }

    @Step("Atualizar parte de uma reserva com o parâmetro token")
    public Response updateBookingWithBasicAuth() {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(bookingPayload.getBookingPayload().toString())
                .put("booking/" + getBookings.getFirstBookingId());

    }
}
