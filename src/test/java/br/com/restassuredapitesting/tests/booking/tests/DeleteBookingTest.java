package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
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
    public void validaDeleçãoDoPrimeiroBooking() {
        deleteBooking.deleteFirstBooking()
                .then()
                .statusCode(201);

        System.out.println("Primeiro Id depois da deleção: " + getBookings.getFirstId());
    }
}
