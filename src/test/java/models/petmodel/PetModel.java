package models.petmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PetModel {
    private Long id;
    private CategoryModel category;
    private String name;
    private List<String> photoUrls;
    private List<TagModel> tags;
    private String status;

    public PetModel(PetModel other) {
        this.id = other.id;
        this.category = other.category;
        this.name = other.name;
        this.photoUrls = other.photoUrls;
        this.tags = other.tags;
        this.status = other.status;
    }
}