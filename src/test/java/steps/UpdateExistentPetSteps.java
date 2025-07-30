package steps;

import io.qameta.allure.Step;
import models.petmodel.PetModel;

import static io.restassured.RestAssured.given;
import static specs.RequestSpec.petRequestSpec;
import static specs.ResponseSpec.getResponseSpec;
import static tests.TestBase.headerO;
import static tests.TestBase.headerS;

public class UpdateExistentPetSteps {
    @Step("Send a request to update a pet in the store")
    public PetModel updatePetInStore(PetModel requestBody, int expectedCode) {
        return given(petRequestSpec)
                .header(headerS, headerO)
                .body(requestBody)
                .when()
                .put()
                .then()
                .spec(getResponseSpec(expectedCode))
                .extract().as(PetModel.class);
    }
}
