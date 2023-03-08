package com.reviews.grpcService;

import com.joao.products.ProductRequest;
import com.joao.products.ProductResponse;
import com.joao.products.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class ProductGrpcServiceImpl implements ProductGrpcService{

    @GrpcClient("Product")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceStub;
    private ManagedChannel channel;

    public ProductGrpcServiceImpl() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }


    @Override
    public ProductResponse getProduct(String sku) {
        productServiceStub = ProductServiceGrpc.newBlockingStub(channel);
        ProductResponse productResponse = productServiceStub.getProduct(
                ProductRequest.newBuilder()
                        .setSku(sku)
                        .build()
        );
        return productResponse;
    }
}
