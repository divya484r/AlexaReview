package com.amazon.AlexaReview.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
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
    private String reviewVal;

    @JsonProperty("author")
    private String author;

    @JsonProperty("review_source")
    private String reviewSource;
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("title")
    private String title;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("reviewed_date")
    private String reviewedDate;

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

    public String getReviewVal() {
        return reviewVal;
    }

    public void setReviewVal(String reviewVal) {
        this.reviewVal = reviewVal;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getReviewedDate() {
        return reviewedDate;
    }

    public void setReviewedDate(String reviewedDate) {
        this.reviewedDate = reviewedDate;
    }
}
