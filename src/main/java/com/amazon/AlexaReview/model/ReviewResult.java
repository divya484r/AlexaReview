package com.amazon.AlexaReview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import java.util.List;


@JsonPropertyOrder({
        "reviews"
})
public class ReviewResult {
    @JsonProperty("reviews")
    @JsonPropertyDescription("reviews")
     private List<Review> review = null;

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }
}
