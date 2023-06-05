package com.ingredient.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Ingredient implements Serializable {

    @Id
    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true)
    private UUID ingredientId = UUID.randomUUID();

    @Column(nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(min = 3, max = 5)
    private String publicKey;

    @Column(nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 25)
    private String name;

    @Column(nullable = false)
    private Category category;

    //Construtor
    public Ingredient(String publicKey ,String name, Category category) {
        setPublicKey(publicKey);
        setName(name);
        setCategory(category);
    }

    public Ingredient(UUID ingredientId,String publicKey, String name, Category category) {
        this.ingredientId = ingredientId;
        setPublicKey(publicKey);
        setName(name);
        setCategory(category);
    }

    public Ingredient() {

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
        if (publicKey.trim().length() == 0){
            throw new IllegalArgumentException("'publicKey' cannot have white spaces");
        }
        if (!publicKey.matches("[a-zA-Z0-9]+")){
            throw new IllegalArgumentException("'publicKey' has invalid characters");
        }
        this.publicKey = publicKey.trim();
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}


