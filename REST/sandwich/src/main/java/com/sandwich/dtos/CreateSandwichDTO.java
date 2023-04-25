package com.sandwich.dtos;

import com.sandwich.model.Ingredient;
import com.sandwich.model.Sandwich;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class CreateSandwichDTO implements Serializable {

    private UUID sandwichId = UUID.randomUUID();

    public String publicKey;

    private String designation;

    private String description;

    private List<IngredientKeyDTO> listOfIngredients;

    private CreateSandwichDTO (final UUID sandwichId) {
    }

    public CreateSandwichDTO(final String publicKey,final String designation, final String description, final List<IngredientKeyDTO> listOfIngredients){
        setPublicKey(publicKey);
        setDesignation(designation);
        setDescription(description);
        setListOfIngredients(listOfIngredients);
    }

    public CreateSandwichDTO(final UUID sandwichId,final String publicKey, final String designation, final String description, final List<IngredientKeyDTO> listOfIngredients) {
        this.sandwichId=sandwichId;
        setPublicKey(publicKey);
        setDesignation(designation);
        setDescription(description);
        setListOfIngredients(listOfIngredients);
    }


    public CreateSandwichDTO() {
    }

    //Getter and setter


    public UUID getSandwichId() {
        return sandwichId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        if (publicKey == null || publicKey.isEmpty()) {
            throw new IllegalArgumentException("'publicKey' is a mandatory attribute of Sandwich");
        }
        if (publicKey.length()<3){
            throw new IllegalArgumentException("'publicKey' has a minimum of 3 characters");
        }
        if (publicKey.length()>5){
            throw new IllegalArgumentException("'publicKey' has a maximum of 5 characters");
        }
        this.publicKey = publicKey;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.isEmpty()) {
            throw new IllegalArgumentException("'Designation' is a mandatory attribute of Sandwich");
        }
        if (designation.length()>50){
            throw new IllegalArgumentException("'Designation' has a maximum of 50 characters");
        }
        if (designation.trim().length() == 0){
            throw new IllegalArgumentException("'Designation' cannot have only white spaces");
        }

        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("'Description' is a mandatory attribute of Sandwich");
        }

        if (description.length()>1024){
            throw new IllegalArgumentException("'Description' has a maximum of 1024 characters");
        }
        if (description.trim().length() == 0){
            throw new IllegalArgumentException("'Description' cannot have only white spaces");
        }

        this.description = description;
    }

    public List<IngredientKeyDTO> getListOfIngredients() {
        return listOfIngredients;
    }

    public void setListOfIngredients(List<IngredientKeyDTO> listOfIngredients) {
        if(listOfIngredients.size()<2){
            throw new IllegalArgumentException("'listOfIngredients' is a mandatory attribute of Sandwich and you must choose at least two. For example: Pão com queijo");
        }
        this.listOfIngredients = listOfIngredients;
    }
}
