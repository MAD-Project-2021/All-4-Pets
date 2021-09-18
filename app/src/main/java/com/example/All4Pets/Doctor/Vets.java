package com.example.All4Pets.Doctor;

public class Vets {

    static String name;
    private static String price;
    private static String speciality;
    private static String rate;


    public static String getName() {
        return name;
    }

    public static String getSpeciality() {
        return speciality;
    }

    public static String getPrice() {
        return price;
    }

    public static String getRate() {
        return rate;
    }

    public static void setPrice(String price) {
        Vets.price = price;
    }

    public static void setSpeciality(String speciality) {
        Vets.speciality = speciality;
    }

    public static void setRate(String rate) {
        Vets.rate = rate;
    }
}
