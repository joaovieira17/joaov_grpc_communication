package com.reservation.model;

import jakarta.persistence.Embeddable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class ReservationItem {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 5)
    private String sandwichPublicKey;

    @NotNull
    @NotBlank
    private int quantity;

    public String getSandwichPublicKey() {
        return sandwichPublicKey;
    }

    public void setSandwichPublicKey(String sandwichPublicKey) {
        if (sandwichPublicKey == null || sandwichPublicKey.isEmpty()) {
            throw new IllegalArgumentException("'sandwichPublicKey' is a mandatory attribute of Category");
        }
        if (sandwichPublicKey.length()<3){
            throw new IllegalArgumentException("'sandwichPublicKey' has a minimum of 3 characters");
        }
        if (sandwichPublicKey.length()>5){
            throw new IllegalArgumentException("'sandwichPublicKey' has a maximum of 5 characters");
        }
        if (!sandwichPublicKey.matches("[a-zA-Z0-9]+")){
            throw new IllegalArgumentException("'sandwichPublicKey' has invalid characters");
        }
        this.sandwichPublicKey = sandwichPublicKey;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
