package tests;

import io.qameta.allure.Feature;
import models.PetSuccessfulDeletionResponseModel;
import models.petmodel.CategoryModel;
import models.petmodel.PetModel;
import models.petmodel.TagModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.AddPetsToStoreSteps;
import steps.DeletePetByIdSteps;

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

@Feature("Delete a pet by its ID")
public class DeletePetsByIdTests extends TestBase {
    private PetModel requestBody = new PetModel();
    private CategoryModel category = new CategoryModel();
    private TagModel tag = new TagModel();
    private AddPetsToStoreSteps addPetsToStoreSteps = new AddPetsToStoreSteps();
    private DeletePetByIdSteps deletePetByIdSteps = new DeletePetByIdSteps();

    private PetSuccessfulDeletionResponseModel deletionResponseBody;
    private PetModel creationResponseBody;
    private Long petId;

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

        creationResponseBody = addPetsToStoreSteps.addPetToStore(requestBody, 200);
        petId = creationResponseBody.getId();
        findPetByIdWithRetry(petId);
    }

    @Test
    @DisplayName("Delete an existent pet by its ID")
    void deleteAnExistentPetById() {
        deletionResponseBody = deletePetByIdSteps.deletePetById(petId, 200);
        deletePetByIdSteps.verifySuccessfulDeletionResponseBody(deletionResponseBody, petId);
    }
}
