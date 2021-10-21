package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    PostAuthRequest login = new PostAuthRequest();
    BookingPayloads bookingPayload = new BookingPayloads();

    @Step("Atualiza uma reserva específica com o parâmetro token")
    public Response updateBookingToken(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayload.getBookingPayload().toString())
                .put("booking/" + id);
    }

    @Step("Atualiza uma reserva específica sem o parâmetro token")
    public Response updateBookingNoToken(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayload.getBookingPayload().toString())
                .put("booking/" + id);
    }

    @Step("Atualiza parte de uma reserva específica com o parâmetro token")
    public Response updatePartialWithToken() {
        JSONObject payload = new JSONObject();
        payload.put("firstname", "Jonas");
        payload.put("lastname", "Franco");
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", login.getToken())
                .when()
                .body(payload.toString())
                .put("booking/" + 8);

    }
}
