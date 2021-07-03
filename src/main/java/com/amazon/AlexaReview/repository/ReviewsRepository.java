package com.amazon.AlexaReview.repository;

import com.amazon.AlexaReview.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    @Query("SELECT c FROM Reviews c WHERE (:reviewed_date is null or c.reviewed_date = :reviewed_date) " +
            "and (:rating is null or c.rating = :rating)" +
            "and (:review_source is null or c.review_source = :review_source)")
    List<Reviews> findMyReviews(@Param("reviewed_date") String reviewed_date,
                                                           @Param("rating") int rating,
                                                           @Param("review_source")String review_source);

}
