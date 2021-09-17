package com.example.All4Pets.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.All4Pets.R;

public class Vet_ShowMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_show_more);
    }
    public void redirecttoresults (View view){
        Intent intent = new Intent(Vet_ShowMore.this, Vet_Results.class);
        startActivity(intent);

    }
    public void gotobookpage (View view){
        Intent intent = new Intent(Vet_ShowMore.this, Book_Appointment.class);
        startActivity(intent);

    }
}
