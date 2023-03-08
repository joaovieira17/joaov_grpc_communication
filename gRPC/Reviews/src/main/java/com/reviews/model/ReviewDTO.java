package com.reviews.model;

public class ReviewDTO {

    String text;
    int rating;

    public ReviewDTO(String text, int rating) {
        this.text = text;
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
