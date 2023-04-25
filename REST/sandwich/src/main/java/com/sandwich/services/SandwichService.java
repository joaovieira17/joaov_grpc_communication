package com.sandwich.services;

import com.sandwich.dtos.CreateSandwichDTO;
import com.sandwich.model.Catalog;
import com.sandwich.model.Sandwich;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface SandwichService {

    //gets the catalog of sandwiches
    List<Catalog> getCatalog();

    //gets a specific sandwich by its ID
    Sandwich getBySandwichId(UUID sandwichId);

    //gets a specific sandwich by its public key
    Sandwich getByPublicKey (String publicKey);

    //verifies if the sandwich exists
    boolean sandwichExistence(UUID sandwichId);

    //creates a sandwich
    Sandwich createSandwich(CreateSandwichDTO sandwichDTO) throws IOException, InterruptedException;

    //deletes a sandwich
    String deleteSandwich (UUID sandwichId);
}
