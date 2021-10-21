package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostBookingTest extends BaseTests {
    PostBookingRequest postBooking = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Tentar criar uma reserva sem estar logado")
    public void validaSePodeCriarReservaSemCadastro() {
        postBooking.sendBookingNoLogin()
                .then()
                .statusCode(401);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Tentar criar uma reserva estando logado")
    public void validaSePodeCriarReservaComCadastro() {
        postBooking.sendBookingWithLogin()
                .then()
                .statusCode(200);
        System.out.println(postBooking.sendBookingWithLogin().getBody().asString());
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Validar a criação de mais de um livro em sequencia")
    public void validaSePodeCriarMaisDeUmaReservaCurtoPeriodo() {
        for (int i = 1; i < 4; i++) {
            postBooking.sendBookingWithLogin()
                    .then()
                    .statusCode(200);
            System.out.println("Criada " + i + "ª reserva na sequência!");
        }

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void validaSePodeCriarReservaComFaltandoInformacoes() {
        postBooking.sendBookingWithMissingInfo()
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void validaSePodeCriarReservaComParametrosAdicionais() {
        postBooking.sendBookingWithExtraInfo()
                .then()
                .statusCode(200);
        System.out.println(postBooking.sendBookingWithLogin().then().extract().asString());
        Assert.assertEquals("a", postBooking.sendBookingWithLogin().then().extract().asString());
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void validaSePodeCriarReservaComHeaderAcceptInvalido() {
        postBooking.sendBookingWithInvalidAcceptHeader()
                .then()
                .statusCode(418);
    }
}
