package com.category.repositories;

import com.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT f FROM Category f WHERE f.categoryId= :categoryId")
    Category getByCategoryId(@Param("categoryId") UUID categoryId);

    @Query("SELECT  f FROM Category f")
    List<Category> getListOfCategories();

    @Query("SELECT f FROM Category f WHERE LOWER(f.name) like %:name%")
    List<Category> getByName(@Param("name") String name);

    @Query("SELECT f FROM Category f WHERE LOWER(f.publicKey) like %:publicKey%")
    Category getByPublicKey(@Param("publicKey") String publicKey);
}
