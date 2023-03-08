package com.reviews.grpcService;

import com.joao.products.ProductResponse;

public interface ProductGrpcService {
    ProductResponse getProduct(String sku);
}
