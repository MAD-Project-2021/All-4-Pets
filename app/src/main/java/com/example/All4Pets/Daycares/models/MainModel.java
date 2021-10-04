package com.example.All4Pets.Daycares.models;


import android.widget.TextView;

import java.io.Serializable;

public class MainModel implements Serializable {
        String name;
        String location;
        String price;
        float rate;
        String img_url;
        //
        String description;
        String contact;
        public MainModel() {

        }

        public MainModel(String name, String location, String price, float rate, String img_url, String description, String contact) {
            this.name = name;
            this.location = location;
            this.price = price;
            this.rate = rate;
            this.img_url = img_url;
            this.contact = contact;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}


