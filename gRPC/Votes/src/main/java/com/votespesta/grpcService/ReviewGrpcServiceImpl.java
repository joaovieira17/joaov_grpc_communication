package com.votespesta.grpcService;

import com.joao.reviews.ReviewRequest;
import com.joao.reviews.ReviewResponse;
import com.joao.reviews.ReviewServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewGrpcServiceImpl implements ReviewGrpcService{

    @GrpcClient("Review")
    private ReviewServiceGrpc.ReviewServiceBlockingStub reviewServiceStub;
    private ManagedChannel channel;

    public ReviewGrpcServiceImpl() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9092)
                .usePlaintext()
                .build();
    }

    @Override
    public ReviewResponse getReview(UUID reviewId) {
        reviewServiceStub = ReviewServiceGrpc.newBlockingStub(channel);
        ReviewResponse reviewResponse = reviewServiceStub.getReview(
                ReviewRequest.newBuilder()
                        .setReviewId(reviewId.toString())
                        .build()
        );
        return reviewResponse;
    }
}
