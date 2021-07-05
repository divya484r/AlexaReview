package com.amazon.AlexaReview.repository;

import com.amazon.AlexaReview.entity.Reviews;
import com.amazon.AlexaReview.model.AverageRating;
import com.amazon.AlexaReview.model.TotalRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    @Query("SELECT c FROM Reviews c WHERE (:reviewedDate is null or c.reviewedDate = :reviewedDate) "
            + "and (:rating is null or c.rating = :rating)"
            + "and (:reviewSource is null or c.reviewSource = :reviewSource)")
    List<Reviews> findMyReviews(@Param("reviewedDate") Date reviewedDate,
                                @Param("rating") Integer rating,
                                @Param("reviewSource") String reviewSource);

//    @Query("select review_source, rating, count(rating) as totalRating from Reviews group by  review_source, rating")
//    List<TotalRating> getTotalRatingsByCategory();

    @Query("SELECT new com.amazon.AlexaReview.model.TotalRating(c.reviewSource, c.rating, COUNT(c.rating)) "
            + "FROM Reviews c GROUP BY c.reviewSource, c.rating ")
    List<TotalRating> getTotalRatingsByCategory();


    @Query("select new com.amazon.AlexaReview.model.AverageRating(c.reviewSource, "
            + "EXTRACT(YEAR from c.reviewedDate), "
            + "EXTRACT(MONTH from c.reviewedDate), avg(rating))"
            + " from Reviews c"
            + " GROUP BY c.reviewSource,EXTRACT(YEAR from c.reviewedDate), extract(month from c.reviewedDate)")
    List<AverageRating> getAverageRating();


    @Query("select new com.amazon.AlexaReview.model.AverageRating(c.reviewSource, "
            + "EXTRACT(YEAR from c.reviewedDate), "
            + "EXTRACT(MONTH from c.reviewedDate), avg(rating))"
            + " from Reviews c where c.reviewSource = :reviewSource"
            + " GROUP BY c.reviewSource,EXTRACT(YEAR from c.reviewedDate), extract(month from c.reviewedDate)")
    List<AverageRating> getAverageRatingBySource(@Param("reviewSource") String reviewSource);
}
