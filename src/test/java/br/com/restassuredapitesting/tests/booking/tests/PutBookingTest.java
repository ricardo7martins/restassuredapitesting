package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Atualização de Reservas")
public class PutBookingTest extends BaseTests {

    PutBookingRequest putBookings = new PutBookingRequest();
    GetBookingRequest getBookings = new GetBookingRequest();
    PostAuthRequest login = new PostAuthRequest();
    BookingPayloads bookingPayload = new BookingPayloads();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, SmokeTests.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva somente utilizando o token")
    public void updateBookingWithToken() {
        putBookings.updateBookingToken(getBookings.getFirstBookingId(),
                        login.getToken())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")
    public void updateBookingWithInvalidToken() {
        putBookings.updateBookingToken(getBookings.getFirstBookingId(), "token=birobiro")
                .then()
                .statusCode(403)
                .body(contains("Forbidden"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void updateBookingWithNoToken() {
        putBookings.updateBookingNoToken(getBookings.getFirstBookingId())
                .then()
                .statusCode(401)
                .body(contains("Unauthorized"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void updateNonExistentBooking() {
        putBookings.updateBookingToken(-1, login.getToken())
                .then()
                .statusCode(404)
                .body(contains("Not Found"));
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva usando o Basic auth")
    public void updateBookingWithBasicAuth() {
        putBookings.updateBookingWithBasicAuth()
                .then()
                .statusCode(200);

    }
}
