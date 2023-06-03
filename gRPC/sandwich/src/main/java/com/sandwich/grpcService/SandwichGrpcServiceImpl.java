package com.sandwich.grpcService;

import com.joao.sandwich.SandwichRequest;
import com.joao.sandwich.SandwichResponse;
import com.joao.sandwich.SandwichServiceGrpc;
import com.sandwich.services.SandwichService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@GrpcService
public class SandwichGrpcServiceImpl extends SandwichServiceGrpc.SandwichServiceImplBase {

    @Autowired
    private SandwichService service;

    @Override
    public void getSandwich(SandwichRequest request, StreamObserver<SandwichResponse> responseObserver) {
        boolean sandwichExistence= service.sandwichExistence(UUID.fromString(request.getSandwichId()));

        SandwichResponse response;
        if(sandwichExistence){
            response = SandwichResponse.newBuilder()
                    .setCode(200)
                    .build();
        }else{
            response = SandwichResponse.newBuilder()
                    .setCode(404)
                    .build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        super.getSandwich(request, responseObserver);
    }
}
