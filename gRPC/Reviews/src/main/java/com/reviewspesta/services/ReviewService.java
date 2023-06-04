package com.reviewspesta.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.reviewspesta.model.*;

public interface ReviewService {

    List<Review> getEveryReview();

    Review getReview(UUID reviewId);

    Review create(ReviewDTO rev, UUID sandwichId);

    List<Review> getReviewsBySandwich(UUID sandwichId);

    RatingFrequency getRatingFrequencyOfSandwich(UUID sandwichId);

    boolean goodToDel(Review review);

    boolean belongsToUser(Review review);

    boolean goodToVote(Review review);

    void upVote(UUID reviewId);

    void downVote(UUID reviewId);

    boolean reviewExistence(UUID reviewId);

    List<Review> getReviewsBySandwichOrderByDateWithoutPage(UUID sandwichId);

    List<Review> getReviewsBySandwichOrderByVotesWithoutPage(UUID sandwichId);

    void updateVotes(Vote vote, Review review);

    List<Review> getAllMyReviews();

    List<Review> getReviewByLanguage(String language);
}

