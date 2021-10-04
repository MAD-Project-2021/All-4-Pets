package com.example.All4Pets.Category.models;

import java.io.Serializable;

public class PetsModel implements Serializable {

    String name;
    int price;
    String description;
    String type;
    String phone;
    String img_url;

    public PetsModel() {
    }

    public PetsModel(String name, int price, String description, String type,String phone, String img_url) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.phone = phone;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
