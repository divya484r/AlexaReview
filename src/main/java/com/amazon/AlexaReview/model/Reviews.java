package com.amazon.AlexaReview.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    @Column(name = "review")
    private String review;

    @Column(name = "author")
    private String author;

    @Column(name = "review_source")
    private String review_source;

    @Column(name = "rating")
    private int rating;

    @Column(name = "title")
    private String title;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "reviewed_date")
    private String reviewed_date;

    public Reviews() {

    }


    public long getId() {
        return id;
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

    public String getReview_source() {
        return review_source;
    }

    public void setReview_source(String review_source) {
        this.review_source = review_source;
    }

    public String getReviewed_date() {
        return reviewed_date;
    }

    public void setReviewed_date(String reviewed_date) {
        this.reviewed_date = reviewed_date;
    }

    public Reviews(String review, String author, String review_source, int rating, String title, String product_name, String reviewed_date) {
        this.review = review;
        this.author = author;
        this.review_source = review_source;
        this.rating = rating;
        this.title = title;
        this.product_name = product_name;
        this.reviewed_date = reviewed_date;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", author='" + author + '\'' +
                ", review_source='" + review_source + '\'' +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                ", product_name='" + product_name + '\'' +
                ", reviewed_date='" + reviewed_date + '\'' +
                '}';
    }
}
