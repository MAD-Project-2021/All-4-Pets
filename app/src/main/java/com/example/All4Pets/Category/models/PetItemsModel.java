package com.example.All4Pets.Category.models;

import java.io.Serializable;

public class PetItemsModel implements Serializable {
    String name;
    int price;
    String description;
    String img_url;


    public PetItemsModel() {
    }


    public PetItemsModel(String name, int price, String description, String img_url) {
        this.name = name;
        this.price = price;
        this.description = description;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
