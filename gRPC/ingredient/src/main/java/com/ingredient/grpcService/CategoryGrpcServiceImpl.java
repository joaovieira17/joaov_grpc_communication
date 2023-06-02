package com.ingredient.grpcService;

import com.joao.category.CategoryRequest;
import com.joao.category.CategoryResponse;
import com.joao.category.CategoryServiceGrpc;
import com.joao.ingredient.IngredientRequest;
import com.joao.ingredient.IngredientResponse;
import com.joao.ingredient.IngredientServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class CategoryGrpcServiceImpl implements CategoryGrpcService{

    @GrpcClient("Category")
    private CategoryServiceGrpc.CategoryServiceBlockingStub categoryServiceStub;
    private ManagedChannel channel;

    public CategoryGrpcServiceImpl() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9089)
                .usePlaintext()
                .build();
    }

    @Override
    public CategoryResponse getCategory(String publicKey) {
        categoryServiceStub= CategoryServiceGrpc.newBlockingStub(channel);
        CategoryResponse categoryResponse = categoryServiceStub.getCategory(
                CategoryRequest.newBuilder()
                        .setPublicKey(publicKey)
                        .build()
        );
        return categoryResponse;
    }
}
