package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public class ResponseSpec {
    public static ResponseSpecification getResponseSpec(Integer expectedStatusCode) {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectStatusCode(expectedStatusCode)
                .build();
    }
}

