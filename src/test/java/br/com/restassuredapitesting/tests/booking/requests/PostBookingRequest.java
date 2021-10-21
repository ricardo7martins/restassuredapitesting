package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    BookingPayloads bookingPayload = new BookingPayloads();
    PostAuthRequest getLogin = new PostAuthRequest();

    @Step("Pedir para criar reserva sem estar logado")
    public Response sendBookingNoLogin() {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayload.getBookingPayload().toString())
                .post("booking");
    }

    @Step("Pedir para criar reserva estando logado")
    public Response sendBookingWithLogin() {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", getLogin.getToken())
                .when()
                .body(bookingPayload.getBookingPayload().toString())
                .post("booking");
    }

    @Step("Pedir para criar reserva faltando parâmetros no corpo do envio")
    public Response sendBookingWithMissingInfo() {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", getLogin.getToken())
                .when()
                .body(bookingPayload.getPayloadMissingParams().toString())
                .post("booking");
    }

    @Step("Pedir para criar reserva com parâmetros adicionais no corpo do envio")
    public Response sendBookingWithExtraInfo() {
        JSONObject payload = bookingPayload.getBookingPayload();
        payload.put("transport", true);
        payload.put("safe", false);
        payload.put("wakeupcalltime", 8);

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", getLogin.getToken())
                .when()
                .body(payload.toString())
                .post("booking");
    }

    @Step("Pedir para criar reserva com header Accept inválido")
    public Response sendBookingWithInvalidAcceptHeader() {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "ThisIsNotIt!")
                .when()
                .body(bookingPayload.getBookingPayload().toString())
                .post("booking");
    }
}
