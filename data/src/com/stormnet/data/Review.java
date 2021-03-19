package com.stormnet.data;

import java.util.Objects;

public class Review {

    private Long id;

    private String telephoneNumber;

    private String reviewText;

    public Review(String telephoneNumber, String reviewText) {
        this.telephoneNumber = telephoneNumber;
        this.reviewText = reviewText;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(reviewText, review.reviewText) &&
                Objects.equals(telephoneNumber, review.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewText, telephoneNumber);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewText='" + reviewText + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
