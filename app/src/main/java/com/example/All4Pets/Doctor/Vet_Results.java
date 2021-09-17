package com.example.All4Pets.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.All4Pets.R;

public class Vet_Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_results);
    }

    public void gotoshowmore (View view){
        Intent intent = new Intent(Vet_Results.this, Vet_ShowMore.class);
        startActivity(intent);

    }

    public void gotofavpage (View view){
        Intent intent = new Intent(Vet_Results.this, Fav_VetList.class);
        startActivity(intent);

    }
}