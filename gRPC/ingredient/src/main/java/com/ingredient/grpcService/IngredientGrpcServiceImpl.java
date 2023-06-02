package com.ingredient.grpcService;

import com.ingredient.dtos.IngredientToSend;
import com.ingredient.model.Ingredient;
import com.ingredient.repositories.IngredientRepository;
import com.ingredient.services.IngredientService;
import com.ingredient.utils.StringUtils;
import com.joao.ingredient.IngredientRequest;
import com.joao.ingredient.IngredientResponse;
import com.joao.ingredient.IngredientServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@GrpcService
public class IngredientGrpcServiceImpl extends IngredientServiceGrpc.IngredientServiceImplBase {

    @Autowired
    private IngredientService service;

    @Override
    public void getIngredient(IngredientRequest request, StreamObserver<IngredientResponse> responseObserver) {
        IngredientToSend ingredient = service.getByPublicKey(request.getPublicKey());

        if (ingredient != null){
            IngredientResponse response = IngredientResponse.newBuilder()
                    .setCode(200)
                    .setName(ingredient.getName())
                    .setCategory(ingredient.getCategory())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }else{
            IngredientResponse response = IngredientResponse.newBuilder()
                    .setCode(404)
                    .setName("")
                    .setCategory("")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        super.getIngredient(request, responseObserver);
    }
}
