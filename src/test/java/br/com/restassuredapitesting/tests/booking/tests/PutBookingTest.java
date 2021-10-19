package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

public class PutBookingTest extends BaseTests {

    PutBookingRequest bookingRequest = new PutBookingRequest();
    GetBookingRequest getBookings = new GetBookingRequest();

    @Test
    @Category(AllTests.class)
    public void updateBooking() {
        PostAuthRequest login = new PostAuthRequest();
        int primeiroId = getBookings
                .bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");


        bookingRequest.updateBookingToken(primeiroId, login.getToken())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}
