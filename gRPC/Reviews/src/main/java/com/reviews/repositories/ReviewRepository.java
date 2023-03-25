package com.reviews.repositories;

import com.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.reviewId = :reviewId")
    Review getReviewById(@Param("reviewId") UUID reviewId);

    @Query("SELECT r FROM Review r WHERE r.sandwichId = :sku  ORDER BY r.date desc")
    List <Review> getReviewsBySandwichOrderByDateWithoutPage(@Param("sandwichId") UUID sandwichId);

    @Query("SELECT r FROM Review r WHERE r.sandwichId = :sku and r.status = 'APPROVED' ORDER BY r.upVotes desc, r.date desc")
    List <Review> getReviewsBySandwichOrderByVotesWithoutPage(@Param("sandwichId") UUID sandwichId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.sandwichId = :sku")
    Float getAggregatedRating(@Param("sandwichId") UUID sandwichId);

    @Query("SELECT r FROM Review r WHERE r.status ='PENDING'")
    List <Review> getAllPendingReviews();

    @Query("SELECT r FROM Review r WHERE r.userId = :userId")
    List<Review> getAllMyReviews(@Param("userId") Long userId);

    @Query("SELECT r FROM Review r WHERE r.sandwichId =:sku")
    List<Review> getReviewsBySandwich(@Param("sandwichId") UUID sandwichId);

}
