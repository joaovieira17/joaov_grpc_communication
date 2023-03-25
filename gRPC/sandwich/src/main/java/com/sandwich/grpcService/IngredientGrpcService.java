package com.sandwich.grpcService;

import com.joao.ingredient.IngredientResponse;

public interface IngredientGrpcService {

IngredientResponse getIngredient(String nameOfIngredient);
}
