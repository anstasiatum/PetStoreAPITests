package tests;

import io.qameta.allure.Feature;
import models.petmodel.CategoryModel;
import models.petmodel.PetModel;
import models.petmodel.TagModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.AddPetsToStoreSteps;
import steps.DeletePetByIdSteps;
import steps.FindPetByIdSteps;
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
import static tests.waitmethods.FindPetByIdWithRetry.findPetByIdWithRetry;

@Feature("Search for a pet by its ID")
public class FindPetByIdTests extends TestBase {
    private PetModel requestBody = new PetModel();
    private CategoryModel category = new CategoryModel();
    private TagModel tag = new TagModel();
    private AddPetsToStoreSteps addPetsToStoreSteps = new AddPetsToStoreSteps();
    private FindPetByIdSteps findPetByIdSteps = new FindPetByIdSteps();
    private VerifyResponsePetStep verifyResponsePetStep = new VerifyResponsePetStep();
    private PetModel responseBody;
    private Long petId;
    private DeletePetByIdSteps deletePetByIdSteps = new DeletePetByIdSteps();

    @BeforeEach
    void addPet() {
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
        findPetByIdWithRetry(petId);
    }

    @Test
    @DisplayName("Find an existent pet by its ID")
    void findAnExistentPetById() {
        responseBody = findPetByIdSteps.findPetById(petId, 200);
        verifyResponsePetStep.verifyPetResponse(categoryID, categoryName, petName, photoUrl, tags, status, responseBody);
    }

    @AfterEach
    void deletePet() {
        deletePetByIdSteps.deletePetById(petId, 200);
    }
}
