package br.com.restassuredapitesting.tests.auth.requests;

import br.com.restassuredapitesting.tests.auth.requests.payloads.AuthPayloads;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {
    AuthPayloads payloadLogin = new AuthPayloads();

    public Response tokenReturn() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payloadLogin.jsonAuthLogin().toString())
                .post("https://treinamento-api.herokuapp.com/auth");
    }

    public String getToken() {
        return "token=" + this.tokenReturn()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
