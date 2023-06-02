package com.ingredient.grpcService;

import com.joao.category.CategoryResponse;

public interface CategoryGrpcService {

    CategoryResponse getCategory(String publicKey);
}
