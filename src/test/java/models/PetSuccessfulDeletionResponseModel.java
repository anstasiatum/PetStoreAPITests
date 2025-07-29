package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetSuccessfulDeletionResponseModel {
    int code;
    String type;
    String message;
}
