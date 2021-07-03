package com.amazon.AlexaReview.service;

import com.amazon.AlexaReview.entity.Reviews;
import com.amazon.AlexaReview.model.AverageRating;
import com.amazon.AlexaReview.model.Review;
import com.amazon.AlexaReview.model.ReviewResult;
import com.amazon.AlexaReview.model.TotalRating;
import com.amazon.AlexaReview.repository.ReviewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ReviewService {
    @Autowired
    ReviewsRepository reviewsRepository;

    public ReviewResult searchReviews(String reviewDate, Integer rating, String storeType) throws ParseException {
        ReviewResult reviewResult;
        List<Review> lstReviews = new ArrayList<>();
        Date reviewedDate = null;
        if(reviewDate != null) {
            reviewedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(reviewDate);
        }
        List<Reviews> lstResult = reviewsRepository.findMyReviews(reviewedDate, rating, storeType);
        for(Reviews res: lstResult) {
            Review review = new Review();
            review.setReview(res.getReview());
            review.setAuthor(res.getAuthor());
            review.setProduct_name(res.getProduct_name());
            review.setRating(res.getRating());
            review.setTitle(res.getTitle());
            review.setReview_source(res.getReview_source());

            review.setReviewed_date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(res.getReviewed_date()));
            lstReviews.add(review);
        }
        if(lstReviews.size() >0) {
            reviewResult = new ReviewResult();
            reviewResult.setReview(lstReviews);
            return reviewResult;
        }
        return null;
    }

    public Reviews createReview(Review review) throws ParseException {
        return reviewsRepository
                .save(new Reviews(review.getReview(), review.getAuthor(), review.getReview_source(), review.getRating(),
                        review.getTitle(), review.getProduct_name(),
                        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(review.getReviewed_date())));
    }

    public List<TotalRating> getTotalRating() {
        return reviewsRepository.getTotalRatingsByCategory();
    }

    public List<AverageRating> getAverageRating() {
        return reviewsRepository.getAverageRating();
    }

    public void createReviewDump(ReviewResult reviewResult) throws ParseException {
        List<Review> reviews = reviewResult.getReview();
        for(Review review: reviews) {
            reviewsRepository.save(new Reviews(review.getReview(), review.getAuthor(), review.getReview_source(), review.getRating(),
                    review.getTitle(), review.getProduct_name(),
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(review.getReviewed_date())));
        }
    }
}
