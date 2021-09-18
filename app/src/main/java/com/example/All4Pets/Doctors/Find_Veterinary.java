package com.example.All4Pets.Doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.All4Pets.R;

public class Find_Veterinary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_veterinary);
    }

    public void gotoresults (View view){
        Intent intent = new Intent(Find_Veterinary.this, Vet_Results.class);
        startActivity(intent);

    }
}