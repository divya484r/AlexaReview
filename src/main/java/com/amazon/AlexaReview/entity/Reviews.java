package com.amazon.AlexaReview.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

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
    private String reviewSource;

    @Column(name = "rating")
    private int rating;

    @Column(name = "title")
    private String title;

    @Column(name = "product_name")
    private String productName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reviewed_date")
    private Date reviewedDate;

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

    public String getReviewSource() {
        return reviewSource;
    }

    public void setReviewSource(String reviewSource) {
        this.reviewSource = reviewSource;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getReviewedDate() {
        return (Date) reviewedDate.clone();
    }

    public void setReviewedDate(Date reviewedDate) {
        this.reviewedDate = (Date) reviewedDate.clone();
    }

    public Reviews(String review, String author, String reviewSource, int rating, String title, String productName, Date reviewedDate) {
        this.review = review;
        this.author = author;
        this.reviewSource = reviewSource;
        this.rating = rating;
        this.title = title;
        this.productName = productName;
        this.reviewedDate = (Date) reviewedDate.clone();
    }

    @Override
    public String toString() {
        return "Reviews{"
                + "id=" + id
                + ", review='" + review + '\''
                + ", author='" + author + '\''
                + ", review_source='" + reviewSource + '\''
                + ", rating=" + rating
                + ", title='" + title + '\''
                + ", product_name='" + productName + '\''
                + ", reviewed_date='" + reviewedDate + '\''
                + '}';
    }
}
