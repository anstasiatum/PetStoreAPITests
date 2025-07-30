package steps;

import io.qameta.allure.Step;
import models.PetSuccessfulDeletionResponseModel;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RequestSpec.petRequestSpec;
import static specs.ResponseSpec.getResponseSpec;
import static tests.TestBase.headerO;
import static tests.TestBase.headerS;

public class DeletePetByIdSteps {
    @Step("Send a request to find a pet by its ID")
    public PetSuccessfulDeletionResponseModel deletePetById(Long petId, int expectedCode) {
        return given(petRequestSpec)
                .header(headerS, headerO)
                .when()
                .delete(format("/%d", petId))
                .then()
                .spec(getResponseSpec(expectedCode))
                .extract().as(PetSuccessfulDeletionResponseModel.class);
    }

    @Step("Verify successful deletion response body")
    public void verifySuccessfulDeletionResponseBody(PetSuccessfulDeletionResponseModel responseBody, Long petId) {
        assertEquals(200, responseBody.getCode());
        assertEquals("unknown", responseBody.getType());
        assertEquals(petId.toString(), responseBody.getMessage());
    }
}
