package br.com.restassuredapitesting.tests.ping.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.ping.requests.GetPingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Feature("Feature de API Online")
public class GetPingTest extends BaseTests {

    public GetPingRequest getPingRequest = new GetPingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Verificar se a API está online")
    public void validaApiOnline() {
        getPingRequest.pingReturnApi()
                .then()
                .statusCode(201);
//                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
