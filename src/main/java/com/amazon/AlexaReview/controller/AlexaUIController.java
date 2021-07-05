package com.amazon.AlexaReview.controller;


import com.amazon.AlexaReview.model.AverageRating;
import com.amazon.AlexaReview.model.Review;
import com.amazon.AlexaReview.model.ReviewResult;
import com.amazon.AlexaReview.model.TotalRating;
import com.amazon.AlexaReview.service.ReviewService;
import com.amazon.AlexaReview.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/alexa")
public class AlexaUIController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/index")
    public String showUserList(Model model) {
        ReviewResult reviewResult = reviewService.getAll();
        List<Review> reviews = reviewResult.getReview();
        model.addAttribute("reviews", reviews);
        return "index";
    }

    @GetMapping("/add")
    public String addNewReview(Model model) {
        model.addAttribute("review", new Review());
        return "addreview";
    }

    @PostMapping("/addReviews")
    public String createNewReview(Review review) throws ParseException {
        reviewService.createReview(review);
        return "redirect:/alexa/index";

    }


    @RequestMapping(value = "/averageMonthlyRatingView", method = RequestMethod.GET, produces = {"application/json"})
    public String viewForAverageMonthlyRating(Model model) {
        List<AverageRating> monthlyAverageRatings = reviewService.getAverageRating();
        model.addAttribute("averageRating", monthlyAverageRatings);
        model.addAttribute("review", new Review());
        return "average-rating-display";
    }


    @RequestMapping(value = "/getAverageMonthly", method = RequestMethod.GET, produces = {"application/json"})
    public String getAverageMonthlyRating(Review review, Model model) {
        List<AverageRating> monthlyAverageRatings = reviewService.getAverageRatingByStore(review.getReviewSource());
        model.addAttribute("averageRating", monthlyAverageRatings);
        model.addAttribute("review", review);
        return "average-rating-display";
    }


    @RequestMapping(value = "/totalRatingView", method = RequestMethod.GET, produces = {"application/json"})
    public String getTotalRating(Model model) {
        List<TotalRating> totalRatings = reviewService.getTotalRating();
        model.addAttribute("totalRatings", totalRatings);
        return "total-rating-display";
    }

    @GetMapping("/searchPage")
    public String searchPage(Model model) {
        model.addAttribute("review", new Review());
        return "search-rating";
    }

    @RequestMapping("/searchReviews")
    public String searchReviews(Review review, Model model) throws ParseException {
        ReviewResult reviewsList = reviewService.searchReviews(DateUtil.formartDate(review.getReviewedDate()), review.getRating(),
                review.getReviewSource());
        if (null != reviewsList) {
            model.addAttribute("reviewsList", reviewsList.getReview());
        } else {
            model.addAttribute("reviewsList", null);
        }
        return "search-rating";
    }
}
