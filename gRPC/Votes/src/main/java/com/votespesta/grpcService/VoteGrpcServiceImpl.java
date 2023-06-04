package com.votespesta.grpcService;

import com.joao.votes.VotesRequest;
import com.joao.votes.VotesResponse;
import com.joao.votes.VotesServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VoteGrpcServiceImpl implements VoteGrpcService{

    @GrpcClient("Vote")
    private VotesServiceGrpc.VotesServiceBlockingStub votesServiceStub;
    private ManagedChannel channel;

    public VoteGrpcServiceImpl() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9092)
                .usePlaintext()
                .build();
    }

    @Override
    public VotesResponse updateVote(UUID reviewId, boolean vote) {
        votesServiceStub = VotesServiceGrpc.newBlockingStub(channel);
        VotesResponse votesResponse = votesServiceStub.updateVote(
                VotesRequest.newBuilder()
                        .setReviewId(reviewId.toString())
                        .setVote(vote)
                        .build()
        );
        return votesResponse;
    }
}
