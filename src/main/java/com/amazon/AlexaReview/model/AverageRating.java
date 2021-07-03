package com.amazon.AlexaReview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "review_source",
        "month",
        "averageRating"
})
public class AverageRating {
    @JsonProperty("review_source")
    private String review_source;

    @JsonProperty("month")
    private int month;

    @JsonProperty("averageRating")
    private double averageRating;

    public String getReview_source() {
        return review_source;
    }

    public void setReview_source(String review_source) {
        this.review_source = review_source;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(long averageRating) {
        this.averageRating = averageRating;
    }

    public AverageRating(String review_source, int month, double averageRating) {
        this.review_source = review_source;
        this.month = month;
        this.averageRating = averageRating;
    }
}
