package br.com.restassuredapitesting.tests.ping.requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetPingRequest {

    public Response pingReturnApi() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("https://treinamento-api.herokuapp.com/ping");
    }
}
