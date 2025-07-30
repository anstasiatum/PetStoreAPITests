package steps;

import io.qameta.allure.Step;
import models.petmodel.PetModel;

import static io.restassured.RestAssured.given;
import static specs.RequestSpec.petRequestSpec;
import static specs.ResponseSpec.getResponseSpec;
import static tests.TestBase.headerO;
import static tests.TestBase.headerS;

public class AddPetsToStoreSteps {
    @Step("Send a request to add a pet to the store")
    public PetModel addPetToStore(PetModel requestBody, int expectedCode) {
        return given(petRequestSpec)
                .header(headerS, headerO)
                .body(requestBody)
                .when()
                .post()
                .then()
                .spec(getResponseSpec(expectedCode))
                .extract().as(PetModel.class);
    }
}
