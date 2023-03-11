package com.votes.repositories;

import com.votes.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.votes.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT f FROM Product f where f.sku= :sku")
    Product getBySku(@Param("sku") String sku);

    @Query("SELECT f FROM Product f where f.designation like %:skuOrDesignation% or f.sku = :skuOrDesignation")
    Page <Product> getBySkuOrDesignation(@Param("skuOrDesignation") String skuOrDesignation,Pageable pageable);

    @Query("SELECT  f FROM Product f ")
    Page <Catalog> getCatalog(Pageable pageable);
}
