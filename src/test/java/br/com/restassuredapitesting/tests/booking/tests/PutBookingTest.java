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

import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Atualização de Reservas")
public class PutBookingTest extends BaseTests {

    PutBookingRequest putBookings = new PutBookingRequest();
    GetBookingRequest getBookings = new GetBookingRequest();
    PostAuthRequest login = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva somente utilizando o token")
    public void alterarBookingComToken() {
        putBookings.updateBookingToken(getBookings.getFirstId(), login.getToken())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")
    public void alterarBookingComTokenInvalido() {
        putBookings.updateBookingToken(getBookings.getFirstId(), "token=birobiro")
                .then()
                .statusCode(403)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void alterarBookingSemToken() {
        putBookings.updateBookingNoToken(getBookings.getFirstId())
                .then()
                .statusCode(401)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void alterarBookingNaoExistente() {
        putBookings.updateBookingToken(10000, login.getToken())
                .then()
                .statusCode(405)
                .body("size()", greaterThan(0));
    }
}
