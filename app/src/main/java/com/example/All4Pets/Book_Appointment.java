package com.example.All4Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Book_Appointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
    }
    public void redirecttoshowmore (View view){
        Intent intent = new Intent(Book_Appointment.this, Vet_ShowMore.class);
        startActivity(intent);

    }
    public void gotoconfirmed (View view){
        Intent intent = new Intent(Book_Appointment.this, Confirmed_Booking.class);
        startActivity(intent);

    }
}