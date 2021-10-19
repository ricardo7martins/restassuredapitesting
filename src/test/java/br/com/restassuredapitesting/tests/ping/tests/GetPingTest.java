package br.com.restassuredapitesting.tests.ping.tests;

import br.com.restassuredapitesting.base.BaseTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.ping.requests.GetPingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GetPingTest extends BaseTests {

    public GetPingRequest getPingRequest = new GetPingRequest();

    @Test
    @Category(AllTests.class)
    public void validaApiOnline() {
        getPingRequest.pingReturnApi()
                .then()
                .statusCode(201);
//                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
