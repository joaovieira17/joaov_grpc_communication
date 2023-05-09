package com.ingredient.dtos;

import com.ingredient.model.Category;

import java.io.Serializable;
import java.util.UUID;

public class CreateIngredientDTO implements Serializable {

    private UUID ingredientId = UUID.randomUUID();

    private String publicKey;

    private String name;

    //private CategoryKeyDTO categoryKeyDTO;
    private String categoryKey;

    //Construtor
    public CreateIngredientDTO(String publicKey , String name, String categoryKey) {
        setPublicKey(publicKey);
        setName(name);
        setCategoryKey(categoryKey);
    }

    public CreateIngredientDTO(UUID ingredientId, String publicKey, String name, String categoryKey) {
        this.ingredientId = ingredientId;
        setPublicKey(publicKey);
        setName(name);
        setCategoryKey(categoryKey);
    }

    public CreateIngredientDTO() {

    }

    //Getter and setter


    public UUID getIngredientId() {
        return ingredientId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        if (publicKey == null || publicKey.isEmpty()) {
            throw new IllegalArgumentException("'publicKey' is a mandatory attribute of Ingredient");
        }
        if (publicKey.length()<3){
            throw new IllegalArgumentException("'publicKey' has a minimum of 3 characters");
        }
        if (publicKey.length()>5){
            throw new IllegalArgumentException("'publicKey' has a maximum of 5 characters");
        }
        this.publicKey = publicKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("'Name' is a mandatory attribute of Ingredient");
        }
        if (name.length()>25){
            throw new IllegalArgumentException("'Name' has a maximum of 25 characters");
        }
        if (name.trim().length() == 0){
            throw new IllegalArgumentException("'Name' cannot have only white spaces");
        }
        this.name = name;
    }
/*
    public CategoryKeyDTO getCategoryKeyDTO() {
        return categoryKeyDTO;
    }

    public void setCategoryKeyDTO(CategoryKeyDTO categoryKeyDTO) {
        this.categoryKeyDTO = categoryKeyDTO;
    }*/

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }
}
