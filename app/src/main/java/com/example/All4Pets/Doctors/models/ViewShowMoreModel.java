package com.example.All4Pets.Doctors.models;

import java.io.Serializable;

public class ViewShowMoreModel implements Serializable {

    String name;
    String speciality;
    String price;
    String rate;
    String img_url;
    String description;
    String exp;
    String location;
    public String id;

    public ViewShowMoreModel() {

    }

    public ViewShowMoreModel(String name, String speciality, String price, String rate, String img_url, String description, String exp, String location) {
        this.name = name;
        this.speciality = speciality;
        this.price = price;
        this.rate = rate;
        this.img_url = img_url;
        this.description = description;
        this.exp = exp;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
