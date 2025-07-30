package tests;

import io.qameta.allure.Feature;
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

import static tests.TestData.categoryID;
import static tests.TestData.categoryName;
import static tests.TestData.petName;
import static tests.TestData.photoUrl;
import static tests.TestData.status;
import static tests.TestData.tagID;
import static tests.TestData.tagName;
import static tests.TestData.tags;
import static tests.waitmethods.DeletePetByIdWithRetry.deletePetByIdWithRetry;
import static tests.waitmethods.FindPetByIdWithRetry.findPetByIdWithRetry;

@Feature("Add pets to the store")
public class AddPetsToStoreTests extends TestBase {
    private PetModel requestBody = new PetModel();
    private CategoryModel category = new CategoryModel();
    private TagModel tag = new TagModel();
    private AddPetsToStoreSteps addPetsToStoreSteps = new AddPetsToStoreSteps();
    private VerifyResponsePetStep verifyResponsePetStep = new VerifyResponsePetStep();
    private PetModel responseBody;
    private Long petId;

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
        deletePetByIdWithRetry(petId);
    }
}
