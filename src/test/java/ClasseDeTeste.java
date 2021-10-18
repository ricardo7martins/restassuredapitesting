import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ClasseDeTeste {

    @Test
    public void validaApiOnline() {
        Response responsePing = given()
                .header("Content-Type", "application/json")
                .when()
                .get("https://treinamento-api.herokuapp.com/ping");

        responsePing
                .then()
                .statusCode(201);
//                .time(lessThan(2L), TimeUnit.SECONDS);
    }

}
