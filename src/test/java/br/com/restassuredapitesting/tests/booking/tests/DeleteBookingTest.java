package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DeleteBookingTest extends BaseTests {

    DeleteBookingRequest deleteBooking = new DeleteBookingRequest();
    GetBookingRequest getBookings = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Excluir um reserva com sucesso")
    public void validaDeleçãoDeBooking() {
        deleteBooking.deleteFirstBooking()
                .then()
                .statusCode(201);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class, SmokeTests.class})
    @DisplayName("Tentar excluir um reserva que não existe")
    public void validaDeleçãoDeBookingNaoExistente() {
        deleteBooking.deleteMissingBooking(10000)
                .then()
                .statusCode(405);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class, SmokeTests.class})
    @DisplayName("Tentar excluir uma reserva sem autorização")
    public void validaDeleçãoDeBookingSemAutorizacao() {
        deleteBooking.deleteBookingWrongAuth()
                .then()
                .statusCode(403);
    }
}
