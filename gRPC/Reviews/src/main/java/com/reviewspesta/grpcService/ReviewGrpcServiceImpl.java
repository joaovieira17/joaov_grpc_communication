package com.reviewspesta.grpcService;

import com.joao.reviews.ReviewRequest;
import com.joao.reviews.ReviewResponse;
import com.joao.reviews.ReviewServiceGrpc;
import com.reviewspesta.model.Review;
import com.reviewspesta.repositories.ReviewRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@GrpcService
public class ReviewGrpcServiceImpl extends ReviewServiceGrpc.ReviewServiceImplBase {

    @Autowired
    private ReviewRepository repository;

    @Override
    public void getReview(ReviewRequest request, StreamObserver<ReviewResponse> responseObserver) {
        Review review = repository.getReviewById(UUID.fromString(request.getReviewId()));

        if(review != null){
            ReviewResponse response = ReviewResponse.newBuilder()
                    .setCode(200)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        else {
            ReviewResponse response = ReviewResponse.newBuilder()
                    .setCode(404)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        //super.getReview(request, responseObserver);
    }
}
