package com.amazon.AlexaReview.controller;

import com.amazon.AlexaReview.model.Reviews;
import com.amazon.AlexaReview.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/alexa")
public class AlexaReviewController {

    @Autowired
    ReviewsRepository reviewsRepository;

    @GetMapping("/reviews")
    public ResponseEntity<?> getReviews(@RequestParam(required = false) String reviewDate,
                                                         @RequestParam(required = false) Integer rating,
                                                         @RequestParam(required = false) String storeType) {

        List<Reviews> reviewsList = reviewsRepository.findMyReviews(reviewDate, rating, storeType);
        System.out.println("reviewsList size="+ reviewsList.size());
        if(reviewsList.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return (ResponseEntity<List<Reviews>>) reviewsList;


    }

    @PostMapping("/reviews")
    public ResponseEntity<Reviews> createTutorial(@RequestBody Reviews reviews) {
        try {
            Reviews reviews1 = reviewsRepository
                    .save(new Reviews(reviews.getReview(), reviews.getAuthor(), reviews.getReview_source(), reviews.getRating(),
                            reviews.getTitle(), reviews.getProduct_name(), reviews.getReviewed_date()));
            return new ResponseEntity<>(reviews1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
