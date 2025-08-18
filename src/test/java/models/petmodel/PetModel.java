package models.petmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetModel {
    private Long id;
    private CategoryModel category;
    private String name;
    private List<String> photoUrls;
    private List<TagModel> tags;
    private String status;
}