package com.amazon.AlexaReview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "review_source",
        "month",
        "year",
        "averageRating"
})
public class AverageRating {
    @JsonProperty("review_source")
    private String reviewSource;

    @JsonProperty("month")
    private int month;

    @JsonProperty("year")
    private int year;

    @JsonProperty("averageRating")
    private double averageRating;

    public String getReviewSource() {
        return reviewSource;
    }

    public void setReviewSource(String reviewSource) {
        this.reviewSource = reviewSource;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public AverageRating(String reviewSource,  int year, int month, double averageRating) {
        this.reviewSource = reviewSource;
        this.month = month;
        this.year = year;
        this.averageRating = averageRating;
    }
}
