package steps;

import io.qameta.allure.Step;
import models.petmodel.PetModel;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.RequestSpec.petRequestSpec;
import static specs.ResponseSpec.getResponseSpec;

public class FindPetByIdSteps {
    @Step("Send a request to find a pet by its ID")
    public PetModel findPetById(Long petId, int expectedCode) {
        return given(petRequestSpec)
                .when()
                .get(format("/%d", petId))
                .then()
                .spec(getResponseSpec(expectedCode))
                .extract().as(PetModel.class);
    }
}
