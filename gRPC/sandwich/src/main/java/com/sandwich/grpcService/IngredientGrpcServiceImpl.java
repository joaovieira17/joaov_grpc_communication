package com.sandwich.grpcService;


import com.joao.ingredient.IngredientRequest;
import com.joao.ingredient.IngredientResponse;
import com.joao.ingredient.IngredientServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class IngredientGrpcServiceImpl implements IngredientGrpcService{
    @GrpcClient("Ingredient")
    private IngredientServiceGrpc.IngredientServiceBlockingStub ingredientServiceStub;
    private ManagedChannel channel;

    public IngredientGrpcServiceImpl() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }

    @Override
    public IngredientResponse getIngredient(String nameOfIngredient) {
        ingredientServiceStub= IngredientServiceGrpc.newBlockingStub(channel);
        IngredientResponse ingredientResponse = ingredientServiceStub.getIngredient(
                IngredientRequest.newBuilder()
                        .setNameOfIngredient(nameOfIngredient)
                        .build()
        );
        return ingredientResponse;
    }
}
