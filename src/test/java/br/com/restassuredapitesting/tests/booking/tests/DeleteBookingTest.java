package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature - Deleção de reservas")
public class DeleteBookingTest extends BaseTests {
    DeleteBookingRequest deleteBooking = new DeleteBookingRequest();
    GetBookingRequest bookings = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, SmokeTests.class, SecurityTests.class})
    @DisplayName("Excluir um reserva com sucesso")
    public void checkBookingDeletion() {
        int firstIdBeforeDeletion = bookings.getFirstBookingId();
        deleteBooking.deleteFirstBooking()
                .then()
                .statusCode(204);
        Assert.assertNotEquals(firstIdBeforeDeletion, bookings.getFirstBookingId());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Tentar excluir um reserva que não existe")
    public void checkNonExistentBookingDeletion() {
        deleteBooking.deleteMissingBooking()
                .then()
                .statusCode(404);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class, SecurityTests.class})
    @DisplayName("Tentar excluir uma reserva sem autorização")
    public void checkUnauthorizedBookingDeletion() {
        deleteBooking.deleteBookingWrongAuth()
                .then()
                .statusCode(403);
    }
}
