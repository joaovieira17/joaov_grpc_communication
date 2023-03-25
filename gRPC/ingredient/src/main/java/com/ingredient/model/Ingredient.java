package com.ingredient.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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
    @Size(min = 1, max = 25)
    private String name;

    //Construtor
    public Ingredient(String name) {
        setName(name);
    }

    public Ingredient(UUID ingredientId, String name) {
        this.ingredientId = ingredientId;
        setName(name);
    }

    public Ingredient() {

    }

    //Getter and setter


    public UUID getIngredientId() {
        return ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("'Name' is a mandatory attribute of Sandwich");
        }
        if (name.length()>25){
            throw new IllegalArgumentException("'Name' has a maximum of 25 characters");
        }
        if (name.trim().length() == 0){
            throw new IllegalArgumentException("'Name' cannot have only white spaces");
        }
        this.name = name;
    }
}


