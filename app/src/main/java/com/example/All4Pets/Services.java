package com.example.All4Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
    }
    public void btn_vet (View view){
        Intent intent = new Intent(Services.this, Find_Veterinary.class);
        startActivity(intent);

    }

    public void btn_daycare (View view){
        Intent intent = new Intent(Services.this, Find_Daycare.class);
        startActivity(intent);

    }
}