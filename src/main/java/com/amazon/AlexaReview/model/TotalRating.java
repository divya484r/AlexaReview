package com.amazon.AlexaReview.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "review_source",
        "rating",
        "total"
})
public class TotalRating {
    @JsonProperty("review_source")
    private String review_source;

    @JsonProperty("rating")
    private int rating;

    @JsonProperty("totalRating")
    private long totalRating;

    public String getReview_source() {
        return review_source;
    }

    public void setReview_source(String review_source) {
        this.review_source = review_source;
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

    public TotalRating(String review_source, int rating, long totalRating) {
        this.review_source = review_source;
        this.rating = rating;
        this.totalRating = totalRating;
    }
}
