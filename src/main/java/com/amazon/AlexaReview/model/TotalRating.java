package com.amazon.AlexaReview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "review_source",
        "rating",
        "total"
})
public class TotalRating {
    @JsonProperty("review_source")
    private String reviewSource;

    @JsonProperty("rating")
    private int rating;

    @JsonProperty("totalRating")
    private long totalRating;

    public String getReviewSource() {
        return reviewSource;
    }

    public void setReviewSource(String reviewSource) {
        this.reviewSource = reviewSource;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public TotalRating(String reviewSource, int rating, long totalRating) {
        this.reviewSource = reviewSource;
        this.rating = rating;
        this.totalRating = totalRating;
    }
}
