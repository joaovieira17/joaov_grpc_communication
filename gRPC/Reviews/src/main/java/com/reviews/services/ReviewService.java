package com.reviews.services;

import java.util.List;
import java.util.UUID;

import com.reviews.model.RatingFrequency;
import com.reviews.model.Review;
import com.reviews.model.ReviewDTO;

public interface ReviewService {

    Review getReview(UUID reviewId);

    Review create(ReviewDTO rev, UUID sandwichId);

    List<Review> getLocalPendingReviews();

    List<Review> getReviewsBySandwich(UUID sandwichId);

    RatingFrequency getRatingFrequencyOfSandwich(UUID sandwichId);

    boolean approveRejectReview(UUID reviewId, boolean status);

    boolean goodToDel(Review review);

    boolean belongsToUser(Review review);

    void upVote(UUID reviewId);

    void downVote(UUID reviewId);

    boolean reviewExistence(UUID reviewId);

    List<Review> getReviewsBySandwichOrderByDateWithoutPage(UUID sandwichId);

    List<Review> getReviewsBySandwichOrderByVotesWithoutPage(UUID sandwichId);

    List<Review> getAllMyLocalReviews();
}

