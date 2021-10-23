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
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar Ids de reservas")
    public void validaListagemDeIdsDasReservas() {
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reservas")
    public void validaSchemaDaListagemDeReservas() {
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils
                        .getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema do retorno de uma reserva específica")
    public void validaSchemaDeUmaReserva() {
        getBookingRequest.getFirstBooking()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils
                        .getSchemaBasePath("booking", "booking"))));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro 'firstname'")
    public void validaBookingsComFiltroFirstName() {
        getBookingRequest.getBookingsBySingleFilter("firstname", "Jim")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro 'lastname'")
    public void validaBookingsComFiltroLastName() {
        getBookingRequest.getBookingsBySingleFilter("lastname", "Brown")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro 'checkin'")
    public void validaBookingsComFiltroCheckin() {
        getBookingRequest.getBookingsBySingleFilter("checkin", "2019-01-01")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro 'checkout'")
    public void validaBookingsComFiltroCheckout() {
        getBookingRequest.getBookingsBySingleFilter("checkout", "2020-07-07")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando os filtros 'checkin' e 'checkout'")
    public void validaBookingsComFiltroCheckinECheckout() {
        getBookingRequest.getBookingsByTwoFilters("checkin", "2018-05-01",
                        "checkout", "2020-03-01")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Listar IDs de reservas utilizando os filtros 'firstname', 'checkin' e  'checkout'")
    public void validaBookingsComFiltroNameECheckinECheckout() {
        getBookingRequest.getBookingsByThreeFilters("firstname", "Eric",
                        "checkin", "2021-01-01",
                        "checkout", "2022-01-01")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void validaBookingsComFiltroInvalido() {
        getBookingRequest.getBookingsBySingleFilter("*<):O)", "doesItMatter?")
                .then()
                .statusCode(500)
                .body(contains("Internal Server Error"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Retornar lista vazia quando não houver retornos com certo filtro")
    public void validaBookingsComFiltroValidoSemRespostas() {
        getBookingRequest.getBookingsBySingleFilter("firstname", "NonExistentName")
                .then()
                .statusCode(200)
                .body("size()", is(0));
    }
}
