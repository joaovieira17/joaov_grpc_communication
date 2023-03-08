package com.reviewspesta.repositories;

import com.reviewspesta.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.reviewId = :reviewId")
    Review getReviewById(@Param("reviewId") UUID reviewId);

    @Query("SELECT r FROM Review r WHERE r.productSku = :sku  ORDER BY r.date desc")
    List <Review> getReviewsByProductOrderByDateWithoutPage(@Param("sku") String sku);

    @Query("SELECT r FROM Review r WHERE r.productSku = :sku and r.status = 'APPROVED' ORDER BY r.upVotes desc, r.date desc")
    List <Review> getReviewsByProductOrderByVotesWithoutPage(@Param("sku") String sku);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.productSku = :sku")
    Float getAggregatedRating(@Param("sku") String sku);

    @Query("SELECT r FROM Review r WHERE r.status ='PENDING'")
    List <Review> getAllPendingReviews();

    @Query("SELECT r FROM Review r WHERE r.userId = :userId")
    List<Review> getAllMyReviews(@Param("userId") Long userId);

    @Query("SELECT r FROM Review r WHERE r.productSku =:sku")
    List<Review> getReviewsByProduct(@Param("sku") String sku);

}
