package com.example.All4Pets.Doctors.models;

public class FavouriteModel {

    String Doctor_name;
    String Doctor_speciality;
    String Doctor_price;
    String CurrentTime;
    String CurrentDate;


    public FavouriteModel() {
    }

    public FavouriteModel(String doctor_name, String doctor_speciality, String doctor_price, String CurrentTime, String CurrentDate) {
        Doctor_name = doctor_name;
        Doctor_speciality = doctor_speciality;
        Doctor_price = doctor_price;
        this.CurrentTime = CurrentTime;
        this.CurrentDate = CurrentDate;
    }

    public String getDoctor_name() {
        return Doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        Doctor_name = doctor_name;
    }

    public String getDoctor_speciality() {
        return Doctor_speciality;
    }

    public void setDoctor_speciality(String doctor_speciality) {
        Doctor_speciality = doctor_speciality;
    }

    public String getDoctor_price() {
        return Doctor_price;
    }

    public void setDoctor_price(String doctor_price) {
        Doctor_price = doctor_price;
    }

    public String getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(String CurrentTime) {
        this.CurrentTime = CurrentTime;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String CurrentDate) {
        this.CurrentDate = CurrentDate;
    }
}