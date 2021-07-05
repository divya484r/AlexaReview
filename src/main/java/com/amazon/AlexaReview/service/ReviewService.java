package com.amazon.AlexaReview.service;

import com.amazon.AlexaReview.entity.Reviews;
import com.amazon.AlexaReview.model.AverageRating;
import com.amazon.AlexaReview.model.Review;
import com.amazon.AlexaReview.model.ReviewResult;
import com.amazon.AlexaReview.model.TotalRating;
import com.amazon.AlexaReview.repository.ReviewsRepository;
import com.amazon.AlexaReview.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class ReviewService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    public ReviewService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }


    public ReviewResult searchReviews(String reviewDate, Integer rating, String storeType) throws ParseException {
        ReviewResult reviewResult;
        List<Review> lstReviews = new ArrayList<>();
        Date reviewedDate = null;
        if (reviewDate != null) {
            reviewedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(reviewDate);
        }
        if (rating != null && rating.intValue() == 0) {
            rating = null;
        }
        if (null != storeType && storeType.length() == 0) {
            storeType = null;
        }
        System.out.println("rating " + rating + "reviewDate=" + reviewDate + "storeType=" + storeType);
        List<Reviews> lstResult = reviewsRepository.findMyReviews(reviewedDate, rating, storeType);
        for (Reviews res : lstResult) {
            Review review = new Review();
            review.setReviewVal(res.getReview());
            review.setAuthor(res.getAuthor());
            review.setProductName(res.getProductName());
            review.setRating(res.getRating());
            review.setTitle(res.getTitle());
            review.setReviewSource(res.getReviewSource());

            review.setReviewedDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(res.getReviewedDate()));
            lstReviews.add(review);
        }
        if (lstReviews.size() > 0) {
            reviewResult = new ReviewResult();
            reviewResult.setReview(lstReviews);
            return reviewResult;
        }
        return null;
    }

    public Reviews createReview(Review review) throws ParseException {
        String formattedDate = DateUtil.formartDate(review.getReviewedDate());


        Reviews reviews = reviewsRepository
                .save(new Reviews(review.getReviewVal(), review.getAuthor(), review.getReviewSource(), review.getRating(),
                        review.getTitle(), review.getProductName(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(formattedDate)));
        return reviews;
    }

    public List<TotalRating> getTotalRating() {
        return reviewsRepository.getTotalRatingsByCategory();
    }

    public List<AverageRating> getAverageRating() {
        return reviewsRepository.getAverageRating();
    }

    public List<AverageRating> getAverageRatingByStore(String reviewSource) {
        return reviewsRepository.getAverageRatingBySource(reviewSource);
    }

    public void createReviewDump(ReviewResult reviewResult) throws ParseException {
        List<Review> reviews = reviewResult.getReview();
        for (Review review : reviews) {
            reviewsRepository.save(new Reviews(review.getReviewVal(), review.getAuthor(), review.getReviewSource(), review.getRating(),
                    review.getTitle(), review.getProductName(),
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(review.getReviewedDate())));
        }
    }

    public ReviewResult getAll() {
        List<Reviews> reviews = reviewsRepository.findAll();
        List<Review> lstReviews = new ArrayList<>();
        for (Reviews res : reviews) {
            Review review1 = new Review();
            review1.setReviewVal(res.getReview());
            review1.setAuthor(res.getAuthor());
            review1.setProductName(res.getProductName());
            review1.setRating(res.getRating());
            review1.setTitle(res.getTitle());
            review1.setReviewSource(res.getReviewSource());

            review1.setReviewedDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(res.getReviewedDate()));
            lstReviews.add(review1);
        }
        ReviewResult reviewResult = new ReviewResult();
        reviewResult.setReview(lstReviews);
        return reviewResult;
    }
}
