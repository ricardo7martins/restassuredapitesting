package br.com.restassuredapitesting.tests.auth.requests;

import br.com.restassuredapitesting.tests.auth.requests.payloads.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {
    AuthPayloads loginPayload = new AuthPayloads();


    @Step("Realiza o login")
    public Response makeLogin() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(loginPayload.jsonAuthLogin().toString())
                .post("auth");
    }

    @Step("Busca o token do login")
    public String getToken() {
        return "token=" + this.makeLogin()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
