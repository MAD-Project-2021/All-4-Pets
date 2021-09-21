package com.example.All4Pets.Doctors.models;

public class MainModel {
    String name;
    String speciality;
    String price;
    String rate;
    String img_url;

    public MainModel() {

    }

    public MainModel(String name, String speciality, String price, String rate, String img_url) {
        this.name = name;
        this.speciality = speciality;
        this.price = price;
        this.rate = rate;
        this.img_url = img_url;
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
}
