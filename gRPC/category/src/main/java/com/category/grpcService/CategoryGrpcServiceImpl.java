package com.category.grpcService;

import com.category.dtos.CategoryToSend;
import com.category.services.CategoryService;
import com.joao.category.CategoryRequest;
import com.joao.category.CategoryResponse;
import com.joao.category.CategoryServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@GrpcService
public class CategoryGrpcServiceImpl extends CategoryServiceGrpc.CategoryServiceImplBase {

    @Autowired
    private CategoryService service;

    @Override
    public void getCategory(CategoryRequest request, StreamObserver<CategoryResponse> responseObserver) {

        CategoryToSend category = service.getToSend(UUID.fromString(request.getPrivateKey()));

        if (category != null){
            CategoryResponse response = CategoryResponse.newBuilder()
                    .setCode(200)
                    .setName(category.getName())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }else{
            CategoryResponse response = CategoryResponse.newBuilder()
                    .setCode(404)
                    .setName("")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        super.getCategory(request, responseObserver);
    }
}
