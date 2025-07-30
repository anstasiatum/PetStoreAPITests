package tests.waitmethods;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.RequestSpec.petRequestSpec;
import static specs.ResponseSpec.getResponseSpec;
import static tests.TestBase.headerO;
import static tests.TestBase.headerS;

public class DeletePetByIdWithRetry {
    public static void deletePetByIdWithRetry(Long petId) {
        int retries = 10;
        int waitMs = 1000;

        for (int i = 0; i < retries; i++) {
            Response response = given(petRequestSpec)
                    .header(headerS, headerO)
                    .when()
                    .delete(format("/%d", petId));

            if (response.getStatusCode() == 200) {
                response.then().spec(getResponseSpec(200));
                return;
            }

            try {
                Thread.sleep(waitMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        given(petRequestSpec)
                .header(headerS, headerO)
                .when()
                .delete(format("/%d", petId))
                .then()
                .spec(getResponseSpec(200));
    }
}
