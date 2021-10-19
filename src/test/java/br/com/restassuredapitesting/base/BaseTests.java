package br.com.restassuredapitesting.base;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseTests {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://treinamento-api.herokuapp.com/";
    }
}
