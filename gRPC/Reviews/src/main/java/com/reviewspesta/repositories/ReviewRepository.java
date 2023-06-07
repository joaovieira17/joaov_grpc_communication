package com.reviewspesta.repositories;

import com.reviewspesta.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface ReviewRepository extends JpaRepository<Review, UUID> {

    @Query("SELECT r FROM Review r")
    List<Review> getEveryReview();

    @Query("SELECT r FROM Review r WHERE r.reviewId = :reviewId")
    Review getReviewById(@Param("reviewId") UUID reviewId);

    @Query("SELECT r FROM Review r WHERE r.sandwichId = :sandwichId  ORDER BY r.date desc")
    List <Review> getReviewsBySandwichOrderByDateWithoutPage(@Param("sandwichId") UUID sandwichId);

    @Query("SELECT r FROM Review r WHERE r.sandwichId = :sandwichId ORDER BY r.upVotes desc, r.date desc")
    List <Review> getReviewsBySandwichOrderByVotesWithoutPage(@Param("sandwichId") UUID sandwichId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.sandwichId = :sandwichId")
    Float getAggregatedRating(@Param("sandwichId") UUID sandwichId);

    @Query("SELECT r FROM Review r WHERE r.userId = :userId")
    List<Review> getAllMyReviews(@Param("userId") Long userId);

    @Query("SELECT r FROM Review r WHERE r.sandwichId =:sandwichId")
    List<Review> getReviewsBySandwich(@Param("sandwichId") UUID sandwichId);

    @Query("SELECT r FROM Review r WHERE r.language = UPPER(:language) ")
    List<Review> getReviewByLanguage(@Param("language") String language);

}
