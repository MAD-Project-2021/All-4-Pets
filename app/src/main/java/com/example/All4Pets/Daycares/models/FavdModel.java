package com.example.All4Pets.Daycares.models;

public class FavdModel {
    String DaycareName;
    String DaycareLocation;
    String DaycareContact;
    String CurrentTime;
    String CurrentDate;


    public FavdModel() {
    }

    public FavdModel(String daycareName, String daycareLocation, String daycareContact, String currentTime, String currentDate) {
        DaycareName = daycareName;
        DaycareLocation = daycareLocation;
        DaycareContact = daycareContact;
        CurrentTime = currentTime;
        CurrentDate = currentDate;
    }

    public String getDaycareName() {
        return DaycareName;
    }

    public void setDaycareName(String daycareName) {
        DaycareName = daycareName;
    }

    public String getDaycareLocation() {
        return DaycareLocation;
    }

    public void setDaycareLocation(String daycareLocation) {
        DaycareLocation = daycareLocation;
    }

    public String getDaycareContact() {
        return DaycareContact;
    }

    public void setDaycareContact(String daycareContact) {
        DaycareContact = daycareContact;
    }

    public String getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(String currentTime) {
        CurrentTime = currentTime;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.CurrentDate = currentDate;
    }
}
