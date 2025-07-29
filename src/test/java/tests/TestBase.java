package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    private static final String baseURL = "https://petstore.swagger.io/v2";
    private static final String apiKey = "special-key";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = baseURL;
    }
}
