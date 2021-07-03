package com.amazon.AlexaReview.model;


import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@JsonPropertyOrder({

        "review",
        "author",
        "review_source",
        "rating",
        "title",
        "product_name",
        "reviewed_date"
})
public class Review {


    @JsonProperty("review")
    private String review;

    @JsonProperty("author")
    private String author;

    @JsonProperty("review_source")
    private String review_source;
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("title")
    private String title;
    @JsonProperty("product_name")
    private String product_name;
    @JsonProperty("reviewed_date")
    private String reviewed_date;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getReviewed_date() {
        return reviewed_date;
    }

    public void setReviewed_date(String reviewed_date) {
        this.reviewed_date = reviewed_date;
    }
}
