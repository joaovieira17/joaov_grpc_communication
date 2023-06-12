package com.reviewspesta.grpcService;

import com.joao.votes.VotesRequest;
import com.joao.votes.VotesResponse;
import com.joao.votes.VotesServiceGrpc;
import com.reviewspesta.model.Review;
import com.reviewspesta.repositories.ReviewRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@GrpcService
public class VoteGrpcServiceImpl extends VotesServiceGrpc.VotesServiceImplBase {

    @Autowired
    private ReviewRepository repository;

    @Override
    public void updateVote(VotesRequest request, StreamObserver<VotesResponse> responseObserver) {

        Review review = repository.getReviewById(UUID.fromString(request.getReviewId()));

        review.updateVote(request.getVote());
        repository.save(review);

        VotesResponse response = VotesResponse.newBuilder()
                .setCode(200)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        //super.updateVote(request, responseObserver);
    }
}
