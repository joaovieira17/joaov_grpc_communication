package com.products.repositories;

import com.products.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.products.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT f FROM Product f where f.sku= :sku")
    Product getBySku(@Param("sku") String sku);

    @Query("SELECT f FROM Product f where f.designation like %:skuOrDesignation% or f.sku = :skuOrDesignation")
    Page <Product> getBySkuOrDesignation(@Param("skuOrDesignation") String skuOrDesignation,Pageable pageable);

    @Query("SELECT  f FROM Product f ")
    List <Catalog> getCatalog();

    @Query("SELECT f FROM Product f where f.designation like %:skuOrDesignation% or f.sku = :skuOrDesignation")
    List<Product> getBySkuOrDesignationSemPage(@Param("skuOrDesignation") String skuOrDesignation);
}
