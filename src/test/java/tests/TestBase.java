package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    private static final String baseURL = "https://petstore.swagger.io/v2";
    public static final String headerS = "api_key";
    public static final String headerO = "special-key";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = baseURL;
    }
}
