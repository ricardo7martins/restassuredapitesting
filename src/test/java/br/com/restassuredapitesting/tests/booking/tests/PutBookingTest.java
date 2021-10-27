package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.*;

@Feature("Feature - Atualização de Reservas")
public class PutBookingTest extends BaseTests {

    PutBookingRequest putBookings = new PutBookingRequest();
    GetBookingRequest bookings = new GetBookingRequest();
    PostAuthRequest login = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva somente utilizando o token")
    public void updateBookingWithToken() {
        putBookings.updateBookingToken(bookings.getFirstBookingId(),
                        login.getToken())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")
    public void updateBookingWithInvalidToken() {
        putBookings.updateBookingToken(bookings.getFirstBookingId(), "token=ThereIsNoSpoon")
                .then()
                .statusCode(403)
                .body(equalTo("Forbidden"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void updateBookingWithNoToken() {
        putBookings.updateBookingNoToken(bookings.getFirstBookingId())
                .then()
                .statusCode(401)
                .body("size()", is(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void updateNonExistentBooking() {
        putBookings.updateBookingToken(-1, login.getToken())
                .then()
                .statusCode(404)
                .body("size()", is(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva usando o Basic auth informado na documentação de Booking - UpdateBooking .Header")
    public void updateBookingWithBasicAuthFromDocs() {
        putBookings.updateBookingWithBasicAuth("Authorisation", "Basic YWRtaW46cGFzc3dvcmQxMjM=]")
                .then()
                .statusCode(200)
                .body("firstname", equalTo(bookings.getValueFromFirstBooking("firstname")));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva usando o Basic auth corrigido")
    public void updateBookingWithBasicAuthWithCorrection() {
        putBookings.updateBookingWithBasicAuth("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .then()
                .statusCode(200)
                .body("firstname", equalTo(bookings.getValueFromFirstBooking("firstname")));
    }
}
