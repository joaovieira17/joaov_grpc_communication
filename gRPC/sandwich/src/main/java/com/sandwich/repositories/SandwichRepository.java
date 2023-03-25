package com.sandwich.repositories;

import com.sandwich.model.Catalog;
import com.sandwich.model.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SandwichRepository extends JpaRepository<Sandwich, UUID> {

    @Query("SELECT f FROM Sandwich f WHERE f.sandwichId= :sandwichId")
    Sandwich getBySandwichId(@Param("sandwichId") UUID sandwichId);

    @Query("SELECT  f FROM Sandwich f")
    List<Catalog> getCatalog();

    @Query("SELECT f FROM Sandwich f WHERE f.designation= :designation")
    Sandwich getSandwichByDesignation(@Param("designation") String designation);


}
