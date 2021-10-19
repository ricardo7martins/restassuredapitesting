package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    PostAuthRequest loginRequest = new PostAuthRequest();
    BookingPayloads bookingPayload = new BookingPayloads();

    public Response updateBookingToken(int id, String token) {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayload.getBookingPayload().toString())
                .put("booking/" + id);
    }
}
