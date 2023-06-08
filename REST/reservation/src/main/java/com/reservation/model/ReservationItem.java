package com.reservation.model;

import jakarta.persistence.Embeddable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Embeddable
public class ReservationItem {

    @NotNull
    @NotBlank
    private UUID sandwichId;

    @NotNull
    @NotBlank
    private int quantity;

    public UUID getSandwichId() {
        return sandwichId;
    }

    public void setSandwichId(UUID sandwichId) {
        if (sandwichId.toString().trim().length()==0){
            throw new IllegalArgumentException("Sandwich Id cannot be white spaces or empty");
        }
        this.sandwichId = sandwichId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity<=0){
            throw new IllegalArgumentException("Quantity should be a positive integer");
        }
        this.quantity = quantity;
    }
}
