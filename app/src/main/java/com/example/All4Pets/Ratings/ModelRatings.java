package com.example.All4Pets.Ratings;

//store and manage UI-related data in a lifecycle
public class ModelRatings {

    String rate,comment;

    public ModelRatings() {
    }

    public ModelRatings(String rate, String comment) {
        this.rate = rate;
        this.comment = comment;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
