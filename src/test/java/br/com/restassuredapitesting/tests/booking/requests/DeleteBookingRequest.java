package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    PostAuthRequest loginRequest = new PostAuthRequest();
    GetBookingRequest getBookings = new GetBookingRequest();

    @Step("Deleta a primeira reserva na lista")
    public Response deleteFirstBooking() {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", loginRequest.getToken())
                .when()
                .delete("booking/" + getBookings.getFirstId());
    }

    @Step("Tenta deletar reserva não existente na lista")
    public Response deleteMissingBooking(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", loginRequest.getToken())
                .when()
                .delete("booking/" + id);
    }

    @Step("Tenta deletar reserva utilizando token inválido")
    public Response deleteBookingWrongAuth() {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=birobiro")
                .when()
                .delete("booking/" + getBookings.getFirstBooking());
    }

}
