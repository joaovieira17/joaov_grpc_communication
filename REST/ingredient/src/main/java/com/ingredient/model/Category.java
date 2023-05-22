package com.ingredient.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Category {

    private String categoryName;

    //Construtor
    public Category(String categoryName) {
        setCategoryName(categoryName);
    }

    public Category() {

    }

    //Getter and setter



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("'Name' is a mandatory attribute of Category");
        }
        if (categoryName.length()>50){
            throw new IllegalArgumentException("'Name' has a maximum of 50 characters");
        }
        if (categoryName.trim().length() == 0){
            throw new IllegalArgumentException("'Name' cannot have only white spaces");
        }
        this.categoryName = categoryName;
    }
}
