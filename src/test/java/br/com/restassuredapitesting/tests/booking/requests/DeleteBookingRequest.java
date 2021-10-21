package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    PostAuthRequest loginRequest = new PostAuthRequest();
    GetBookingRequest getBookings = new GetBookingRequest();

    public Response deleteFirstBooking() {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", loginRequest.getToken())
                .when()
                .delete("booking/" + getBookings.getFirstId());
    }

    public Response deleteMissingBooking(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", loginRequest.getToken())
                .when()
                .delete("booking/" + id);
    }

    public Response deleteBookingWrongAuth() {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=birobiro")
                .when()
                .delete("booking/" + getBookings.getFirstBooking());
    }

}
