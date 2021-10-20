package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature de Atualização de Reservas")
public class PutBookingTest extends BaseTests {

    PutBookingRequest bookingRequest = new PutBookingRequest();
    GetBookingRequest getBookings = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(AllTests.class)
    @DisplayName("Alterar uma reserva somente utilizando o token")
    public void updateBooking() {
        PostAuthRequest login = new PostAuthRequest();

        bookingRequest.updateBookingToken(getBookings.getFirstId(), login.getToken())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}
