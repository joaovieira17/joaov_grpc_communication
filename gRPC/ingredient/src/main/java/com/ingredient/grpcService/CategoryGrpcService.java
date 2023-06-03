package com.ingredient.grpcService;

import com.joao.category.CategoryResponse;

import java.util.UUID;

public interface CategoryGrpcService {

    CategoryResponse getCategory(UUID privateKey);
}
