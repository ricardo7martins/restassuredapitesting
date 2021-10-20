package br.com.restassuredapitesting.tests.auth.requests;

import br.com.restassuredapitesting.tests.auth.requests.payloads.AuthPayloads;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


@Feature("Feature de Autenticação")
public class PostAuthRequest {
    AuthPayloads loginPayload = new AuthPayloads();


    @Step("Retorna o token")
    public Response tokenReturn() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(loginPayload.jsonAuthLogin().toString())
                .post("auth");
    }

    @Step("Busca o token")
    public String getToken() {
        return "token=" + this.tokenReturn()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
