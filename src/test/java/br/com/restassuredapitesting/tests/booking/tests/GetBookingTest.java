package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Feature - Retorno de reservas")
public class GetBookingTest extends BaseTests {
    GetBookingRequest bookings = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar Ids de reservas")
    public void checkAllBookingsShowing() {
        bookings.getAllBookings()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class, SmokeTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reservas")
    public void checkBookingsListSchema() {
        bookings.getAllBookings()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils
                        .getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class, SmokeTests.class})
    @DisplayName("Garantir o schema do retorno de uma reserva específica")
    public void checkBookingSchema() {
        bookings.getFirstBooking()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils
                        .getSchemaBasePath("booking", "booking"))));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro 'firstname'")
    public void checkSearchFilteredByFirstName() {
        bookings.getBookingsByFilters("firstname",
                        bookings.getValueFromFirstBooking("firstname"))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("bookingid", hasItem(bookings.getFirstBookingId()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro 'lastname'")
    public void checkSearchFilteredByLastName() {
        bookings.getBookingsByFilters("lastname",
                        bookings.getValueFromFirstBooking("lastname"))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("bookingid", hasItem(bookings.getFirstBookingId()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro 'checkin'")
    public void checkSearchFilteredByCheckin() {
        bookings.getBookingsByFilters("checkin",
                        bookings.getValueFromFirstBooking("checkin"))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("bookingid", hasItem(bookings.getFirstBookingId()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro 'checkout'")
    public void checkSearchFilteredByCheckout() {
        bookings.getBookingsByFilters("checkout",
                        bookings.getValueFromFirstBooking("checkout"))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("bookingid", hasItem(bookings.getFirstBookingId()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando os filtros 'checkin' e 'checkout'")
    public void checkSearchFilteredByCheckinAndCheckout() {
        bookings.getBookingsByFilters(
                        "checkin", bookings.getValueFromFirstBooking("checkin"),
                        "checkout", bookings.getValueFromFirstBooking("checkout"))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("bookingid", hasItem(bookings.getFirstBookingId()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando os filtros 'firstname', 'checkin' e  'checkout'")
    public void checkSearchFilteredByNameAndCheckinAndCheckout() {
        bookings.getBookingsByFilters(
                        "firstname", bookings.getValueFromFirstBooking("firstname"),
                        "checkin", bookings.getValueFromFirstBooking("checkin"),
                        "checkout", bookings.getValueFromFirstBooking("checkout"))
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("bookingid", hasItem(bookings.getFirstBookingId()));
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void checkSearchFilterInvalid() {
        bookings.getBookingsByFilters("*<):O)", "doesItMatter?")
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Retornar lista vazia quando não houver retornos com certo filtro")
    public void checkSearchNoReturnsForFilter() {
        bookings.getBookingsByFilters("firstname", "Totally Impossible Name")
                .then()
                .statusCode(200)
                .body("size()", is(0));
    }
}
