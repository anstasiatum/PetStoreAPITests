package steps;

import io.qameta.allure.Step;
import models.petmodel.PetModel;
import models.petmodel.TagModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VerifyResponsePetStep {

    @Step("Verify the pet information in the response")
    public void verifyPetResponse(long categoryID, String categoryName, String petName, String photoUrl, List<TagModel> tags, String status, PetModel responseBody) {
        assertNotNull(responseBody.getId());
        assertEquals(categoryName, responseBody.getCategory().getName());
        assertEquals(categoryID, responseBody.getCategory().getId());
        assertEquals(petName, responseBody.getName());
        assertEquals(List.of(photoUrl), responseBody.getPhotoUrls());
        assertEquals(tags, responseBody.getTags());
        assertEquals(status, responseBody.getStatus());
    }
}
