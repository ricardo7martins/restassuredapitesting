package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.*;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.*;

@Feature("Feature - Adição de Reservas")
public class PostBookingTest extends BaseTests {
    PostBookingRequest postBooking = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Tentar criar uma reserva sem estar logado")
    public void checkCreateBookingNoLogin() {
        postBooking.createBookingNoLogin()
                .then()
                .statusCode(401);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class, SecurityTests.class})
    @DisplayName("Tentar criar uma reserva estando logado")
    public void checkCreateBookingWithLogin() {
        postBooking.createBookingWithLogin()
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Validar a criação de mais de uma reserva em sequência")
    public void checkCreateSeveralBookingsShortPeriod() {
        for (int i = 0; i < 2; i++) {
            postBooking.createBookingWithLogin()
                    .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0));
        }
        postBooking.createBookingWithLogin()
                .then()
                .statusCode(429)
                .body("size()", equalTo(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class, ContractTests.class, SecurityTests.class})
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void checkCreateBookingWIthMissingParams() {
        postBooking.createBookingWithMissingInfo()
                .then()
                .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class, ContractTests.class, SecurityTests.class})
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void checkCreateBookingWithExtraParams() {
        JSONObject payload = new BookingPayloads().getBookingPayload();
        payload.put("language", "portuguese");
        postBooking.createBookingWithExtraInfo(payload)
                .then()
                .statusCode(400)
                .body("booking", hasKey("language"));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void checkCreateBookingWithInvalidAcceptHeader() {
        postBooking.createBookingWithInvalidAcceptHeader()
                .then()
                .statusCode(418);
    }
}
