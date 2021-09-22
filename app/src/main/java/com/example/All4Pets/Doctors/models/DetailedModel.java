package com.example.All4Pets.Doctors.models;

public class DetailedModel {

    String et_name;
    String et_address;
    String et_email;
    String et_date;
    String et_time;

    public DetailedModel() {
    }

    public DetailedModel(String et_name, String et_address, String et_email, String et_date, String et_time) {
        this.et_name = et_name;
        this.et_address = et_address;
        this.et_email = et_email;
        this.et_date = et_date;
        this.et_time = et_time;
    }

    public String getEt_name() {
        return et_name;
    }

    public void setEt_name(String et_name) {
        this.et_name = et_name;
    }

    public  String getEt_address() {
        return et_address;
    }

    public void setEt_address(String et_address) {
        this.et_address = et_address;
    }

    public String getEt_email() {
        return et_email;
    }

    public void setEt_email(String et_email) {
        this.et_email = et_email;
    }

    public String getEt_date() {
        return et_date;
    }

    public void setEt_date(String et_date) {
        this.et_date = et_date;
    }

    public String getEt_time() {
        return et_time;
    }

    public void setEt_time(String et_time) {
        this.et_time = et_time;
    }
}
