package com.sandwich.model;

//import jakarta.persistence.Embeddable;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class Ingredient implements Serializable {


    private String name;

    private String category;

    //Construtor
    public Ingredient(String name, String category) {
        setName(name);
        this.category = category;
    }

    public Ingredient() {

    }

    //Getter and setter



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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}


