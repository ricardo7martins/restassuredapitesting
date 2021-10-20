package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest extends BaseTests {

    PostAuthRequest loginRequest = new PostAuthRequest();
    GetBookingRequest getBookings = new GetBookingRequest();

    public Response deleteFirstBooking() {
        System.out.println("Primeiro Id antes da deleção: " + getBookings.getFirstId());
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", loginRequest.getToken())
                .when()
                .delete(("booking/" + getBookings.getFirstId()));
    }

}
