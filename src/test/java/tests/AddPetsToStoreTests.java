package tests;

import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.petmodel.CategoryModel;
import models.petmodel.PetModel;
import models.petmodel.TagModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.AddPetsToStoreSteps;
import steps.DeletePetByIdSteps;
import steps.VerifyResponsePetStep;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.RequestSpec.petRequestSpec;
import static specs.ResponseSpec.getResponseSpec;
import static tests.TestData.categoryID;
import static tests.TestData.categoryName;
import static tests.TestData.petName;
import static tests.TestData.photoUrl;
import static tests.TestData.status;
import static tests.TestData.tagID;
import static tests.TestData.tagName;
import static tests.TestData.tags;

@Feature("Add pets to the store")
public class AddPetsToStoreTests extends TestBase {
    private PetModel requestBody = new PetModel();
    private CategoryModel category = new CategoryModel();
    private TagModel tag = new TagModel();
    private AddPetsToStoreSteps addPetsToStoreSteps = new AddPetsToStoreSteps();
    private VerifyResponsePetStep verifyResponsePetStep = new VerifyResponsePetStep();
    private PetModel responseBody;
    private Long petId;
    private DeletePetByIdSteps deletePetByIdSteps = new DeletePetByIdSteps();

    @Test
    @DisplayName("Add a pet to the store")
    void addPetTest() {
        category.setId(categoryID);
        category.setName(categoryName);
        requestBody.setCategory(category);
        requestBody.setName(petName);
        requestBody.setPhotoUrls(List.of(photoUrl));
        tag.setId(tagID);
        tag.setName(tagName);
        tags = List.of(tag);
        requestBody.setTags(tags);
        requestBody.setStatus(status);

        responseBody = addPetsToStoreSteps.addPetToStore(requestBody, 200);
        petId = responseBody.getId();
        verifyResponsePetStep.verifyPetResponse(categoryID, categoryName, petName, photoUrl, tags, status, responseBody);
        findPetByIdWithRetry(petId);
    }

    @AfterEach
    void deletePet() {
        deletePetByIdSteps.deletePetById(petId, 200);
    }

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
