package com.products.grpcService;

import com.joao.products.ProductRequest;
import com.joao.products.ProductResponse;
import com.joao.products.ProductServiceGrpc;
import com.products.model.Product;
import com.products.repositories.ProductRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class ProductGrpcServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    @Autowired
    private ProductRepository repository;

    @Override
    public void getProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product product = repository.getBySku(request.getSku());
        if(product != null){
            ProductResponse response = ProductResponse.newBuilder()
                    .setSku(product.getSku())
                    .setDesignation(product.getDesignation())
                    .setDescription(product.getDescription())
                    .setImage(product.getImage())
                    .setStatus(200)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }else{
            ProductResponse response = ProductResponse.newBuilder()
                    .setStatus(404)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        super.getProduct(request, responseObserver);
    }
}
