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
import steps.UpdateExistentPetSteps;
import steps.VerifyResponsePetStep;

import java.util.List;

import static tests.TestData.availableStatus;
import static tests.TestData.categoryID;
import static tests.TestData.categoryName;
import static tests.TestData.photoUrl;
import static tests.TestData.soldStatus;
import static tests.TestData.tagID;
import static tests.TestData.tagName;
import static tests.TestData.tags;
import static tests.waitmethods.DeletePetByIdWithRetry.deletePetByIdWithRetry;
import static tests.waitmethods.FindPetByIdWithRetry.findPetByIdWithRetry;

@Feature("Update pets in the store")
public class UpdatePetInStoreTests extends TestBase {
    private PetModel addPetRequestBody = new PetModel();
    private PetModel updatePetRequestBody = new PetModel();
    private CategoryModel category = new CategoryModel();
    private TagModel tag = new TagModel();
    private AddPetsToStoreSteps addPetsToStoreSteps = new AddPetsToStoreSteps();
    private VerifyResponsePetStep verifyResponsePetStep = new VerifyResponsePetStep();
    private PetModel responseBody;
    private UpdateExistentPetSteps updateExistentPetSteps = new UpdateExistentPetSteps();
    private Long petId;
    private DeletePetByIdSteps deletePetByIdSteps = new DeletePetByIdSteps();

    @BeforeEach
    void addPet() {
        category.setId(categoryID);
        category.setName(categoryName);
        addPetRequestBody.setCategory(category);
        addPetRequestBody.setName("Captain Morgan");
        addPetRequestBody.setPhotoUrls(List.of(photoUrl));
        tag.setId(tagID);
        tag.setName(tagName);
        tags = List.of(tag);
        addPetRequestBody.setTags(tags);
        addPetRequestBody.setStatus("available");

        responseBody = addPetsToStoreSteps.addPetToStore(addPetRequestBody, 200);
        petId = responseBody.getId();
        findPetByIdWithRetry(petId);
    }

    @Test
    @DisplayName("Change pet status from available to sold")
    void changePetStatus() {
        updatePetRequestBody = new PetModel(addPetRequestBody);
        updatePetRequestBody.setStatus(soldStatus);
        updatePetRequestBody.setId(petId);

        responseBody = updateExistentPetSteps.updatePetInStore(updatePetRequestBody, 200);
        verifyResponsePetStep.verifyPetResponse(categoryID, categoryName, "Captain Morgan", photoUrl, tags, soldStatus, responseBody);
    }

    @Test
    @DisplayName("Change pet name")
    void changePetName() {
        String newPetName = "Ana Amari";
        updatePetRequestBody = new PetModel(addPetRequestBody);
        updatePetRequestBody.setName(newPetName);
        updatePetRequestBody.setId(petId);

        responseBody = updateExistentPetSteps.updatePetInStore(updatePetRequestBody, 200);
        verifyResponsePetStep.verifyPetResponse(categoryID, categoryName, newPetName, photoUrl, tags, availableStatus, responseBody);
    }

    @AfterEach
    void deletePet() {
        deletePetByIdWithRetry(petId);
    }
}
