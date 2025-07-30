package tests.waitmethods;

import io.restassured.response.Response;
import models.petmodel.PetModel;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.RequestSpec.petRequestSpec;
import static specs.ResponseSpec.getResponseSpec;
import static tests.TestBase.headerO;
import static tests.TestBase.headerS;

public class FindPetByIdWithRetry {
    // Checks that the pet appeared in the DB
    public static PetModel findPetByIdWithRetry(Long petId) {
        int retries = 5;
        int waitMs = 500;

        for (int i = 0; i < retries; i++) {
            Response response = given(petRequestSpec)
                    .header(headerS, headerO)
                    .when()
                    .get(format("/%d", petId));

            if (response.getStatusCode() == 200) {
                return response.then()
                        .spec(getResponseSpec(200))
                        .extract().as(PetModel.class);
            }

            try {
                Thread.sleep(waitMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return given(petRequestSpec)
                .header(headerS, headerO)
                .when()
                .get(format("/%d", petId))
                .then()
                .spec(getResponseSpec(200))
                .extract().as(PetModel.class);
    }
}
