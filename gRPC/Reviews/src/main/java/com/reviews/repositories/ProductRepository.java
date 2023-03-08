package com.reviews.repositories;

import com.reviews.services.HttpRequestHelper;

import java.io.IOException;

public class ProductRepository {
    HttpRequestHelper helper = new HttpRequestHelper();

    public boolean isProduct1(String sku) throws IOException, InterruptedException {
        boolean product=helper.doesProductExist1(sku);
        return product;
    }

    public boolean isProduct2(String sku) throws IOException, InterruptedException {
        boolean product1=helper.doesProductExist2(sku);
        return product1;
    }

    /*public Product findProductBySky(String sku) throws IOException, InterruptedException {

        Product product= helper.getProduct(sku);
        return product;

    }*/
}
