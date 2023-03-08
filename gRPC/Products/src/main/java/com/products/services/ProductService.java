package com.products.services;

import com.products.model.Catalog;
import com.products.model.Product;
import org.springframework.data.domain.Page;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface ProductService {


    List<Catalog> getCatalog();

    Product getBySkuLocal(String sku);

    Page<Product> getBySkuOrDesignation (String skuOrDesignation,int offset,int pageSize) throws IOException, InterruptedException;

    BufferedImage generateEAN13BarcodeImage(String barcodeText);

    BufferedImage generateCode128BarcodeImage(String barcodeText);

    boolean productExistence(String sku);

    List<Product> getBySkuOrDesignationSemPage (String skuOrDesignation) ;

}
