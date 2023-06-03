package com.reviewspesta.grpcService;

import com.joao.sandwich.SandwichRequest;
import com.joao.sandwich.SandwichResponse;
import com.joao.sandwich.SandwichServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SandwichGrpcServiceImpl implements SandwichGrpcService{

    @GrpcClient("Sandwich")
    private SandwichServiceGrpc.SandwichServiceBlockingStub sandwichServiceStub;
    private ManagedChannel channel;

    public SandwichGrpcServiceImpl() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext()
                .build();
    }

    @Override
    public SandwichResponse getSandwich(UUID sandwichId) {
        sandwichServiceStub=SandwichServiceGrpc.newBlockingStub(channel);
        SandwichResponse sandwichResponse = sandwichServiceStub.getSandwich(
                SandwichRequest.newBuilder()
                        .setSandwichId(sandwichId.toString())
                        .build()
        );
        return sandwichResponse;
    }
}
