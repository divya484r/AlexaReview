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

    @Query("SELECT c FROM Reviews c WHERE (:reviewed_date is null or c.reviewed_date = :reviewed_date) " +
            "and (:rating is null or c.rating = :rating)" +
            "and (:review_source is null or c.review_source = :review_source)")
    List<Reviews> findMyReviews(@Param("reviewed_date") Date reviewed_date,
                                                           @Param("rating") Integer rating,
                                                           @Param("review_source")String review_source);

//    @Query("select review_source, rating, count(rating) as totalRating from Reviews group by  review_source, rating")
//    List<TotalRating> getTotalRatingsByCategory();

    @Query("SELECT new com.amazon.AlexaReview.model.TotalRating(c.review_source, c.rating, COUNT(c.rating)) "
            + "FROM Reviews c GROUP BY c.review_source, c.rating ")
    List<TotalRating> getTotalRatingsByCategory();


    @Query("select new com.amazon.AlexaReview.model.AverageRating(c.review_source, EXTRACT(MONTH from c.reviewed_date), avg(rating))" +
            " from Reviews c" +
            " GROUP BY c.review_source,extract(month from c.reviewed_date)")
    List<AverageRating> getAverageRating();


}
