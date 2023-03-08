package com.reviews.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.reviews.model.RatingFrequency;
import com.reviews.model.Review;
import com.reviews.model.ReviewDTO;
import com.reviews.model.Vote;

public interface ReviewService {

    Review getReview(UUID reviewId);

    Review create(ReviewDTO rev, String sku) throws IOException, InterruptedException;

    List<Review> getLocalPendingReviews();

    List<Review> getReviewsByProduct(String sku) throws IOException, InterruptedException; //SIM

    RatingFrequency getRatingFrequencyOfProduct(String sku) throws IOException, InterruptedException;

    boolean approveRejectReview(UUID reviewId, boolean status);

    boolean goodToDel(Review review);

    boolean belongsToUser(Review review);

    boolean goodToVote(Review review);

    void upVote(UUID reviewId);

    void downVote(UUID reviewId);

    boolean reviewExistence(UUID reviewId);

    List<Review> getReviewsByProductOrderByDateWithoutPage(String sku) throws IOException, InterruptedException;

    List<Review> getReviewsByProductOrderByVotesWithoutPage(String sku) throws IOException, InterruptedException;

    void updateVotes(Vote vote, Review review);

    List<Review> getAllMyLocalReviews();
}

