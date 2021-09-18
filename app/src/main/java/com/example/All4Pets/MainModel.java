package com.example.All4Pets;

public class MainModel {
    Integer gallery;
    String description;

    public MainModel(Integer gallery, String description){
        this.gallery = gallery;
        this.description = description;
    }

    public Integer getGallery(){
        return gallery;
    }

    public String getDescription(){
        return description;
    }
}
