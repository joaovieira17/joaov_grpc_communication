package com.sandwich.controllers;

import com.sandwich.dtos.CreateSandwichDTO;
import com.sandwich.model.Catalog;
import com.sandwich.model.Sandwich;
import com.sandwich.services.SandwichService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

//@Tag(name = "Sandwiches", description = "Endpoints for managing sandwiches")
@RestController
@RequestMapping("/sandwich")
public class SandwichController {

    @Autowired
    private SandwichService service;

    //@Operation(summary = "Get Local Catalog of Sandwiches")
    @GetMapping("/catalog")
    public Iterable<Catalog> getCatalog() {
        return service.getCatalog();
    }

    //@Operation(summary = "Gets a specific Local product by its SKU")
    @GetMapping("/{sandwichId}")
    public ResponseEntity<Sandwich> getBySandwichId(@PathVariable("sandwichId") final UUID sandwichId) {
        final Sandwich sandwich = service.getBySandwichId(sandwichId);
        return ResponseEntity.ok().body(sandwich);
    }

    @GetMapping("/GetByKey/{publicKey}")
    public ResponseEntity<Sandwich> getByKey(@PathVariable("publicKey") final String publicKey) {
        final Sandwich sandwich = service.getByPublicKey(publicKey);
        return ResponseEntity.ok().body(sandwich);
    }

    @GetMapping("/existence/{sandwichId}")
    public ResponseEntity<Boolean> checkSandwichExistence(@PathVariable("sandwichId") final UUID sandwichId) {
        boolean exists = service.sandwichExistence(sandwichId);
        return ResponseEntity.ok().body(exists);
    }

    @GetMapping("/existenceByKey/{publicKey}")
    public ResponseEntity<Boolean> checkSandwichExistenceByKey(@PathVariable("publicKey") final String publicKey) {
        boolean exists = service.sandwichExistenceByKey(publicKey);
        return ResponseEntity.ok().body(exists);
    }

    //@Operation(summary = "Create a sandwich")
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Sandwich> createSandwich(@Valid @RequestBody final CreateSandwichDTO createSandwichDTO) throws IOException, InterruptedException {
        final Sandwich sandwich = service.createSandwich(createSandwichDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sandwich);
    }

    //@Operation(summary = "Deletes a sandwich")
    @DeleteMapping(value = "/delete/{sandwichId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteSandwich(@PathVariable("sandwichId")final UUID sandwichId){
        return ResponseEntity.ok(service.deleteSandwich(sandwichId));
    }





}
