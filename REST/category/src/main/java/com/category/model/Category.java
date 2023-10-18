package com.category.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Category implements Serializable {

    @Id
    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true)
    private UUID categoryId = UUID.randomUUID();


    @Column(nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(min = 3, max = 5)
    private String publicKey;

    @Column(nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 40)
    private String name;


    public Category(UUID categoryId, String publicKey, String name) {
        this.categoryId = categoryId;
        setPublicKey(publicKey);
        setName(name);
    }

    public Category(String publicKey, String name) {
        setPublicKey(publicKey);
        setName(name);
    }

    public Category() {
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        if (publicKey == null || publicKey.isEmpty()) {
            throw new IllegalArgumentException("'publicKey' is a mandatory attribute of Category");
        }
        if (publicKey.length()<3){
            throw new IllegalArgumentException("'publicKey' has a minimum of 3 characters");
        }
        if (publicKey.length()>5){
            throw new IllegalArgumentException("'publicKey' has a maximum of 5 characters");
        }
        if (!publicKey.matches("[a-zA-Z0-9]+")){
            throw new IllegalArgumentException("'publicKey' has invalid characters");
        }
        if (publicKey.trim().length() == 0){
            throw new IllegalArgumentException("'Name' cannot have only white spaces");
        }
        this.publicKey = publicKey.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("'Name' is a mandatory attribute of Category");
        }
        if (name.length()>40){
            throw new IllegalArgumentException("'Name' has a maximum of 40 characters");
        }
        if (name.trim().length() == 0){
            throw new IllegalArgumentException("'Name' cannot have only white spaces");
        }
        this.name = name;
    }
}
