package com.ingredient.dtos;

public class IngredientToSend {

    private int code;

    private String name;

    private String category;

    public IngredientToSend(int code, String name, String category) {
        this.code = code;
        this.name = name;
        this.category = category;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
