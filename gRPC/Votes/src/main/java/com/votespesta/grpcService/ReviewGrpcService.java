package com.votespesta.grpcService;

import com.joao.reviews.ReviewResponse;

import java.util.UUID;

public interface ReviewGrpcService {

    ReviewResponse getReview (UUID reviewId);
}
