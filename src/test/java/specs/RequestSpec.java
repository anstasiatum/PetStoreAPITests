package specs;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpec {
    public static RequestSpecification petRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all()
            .basePath("/pet");
}


