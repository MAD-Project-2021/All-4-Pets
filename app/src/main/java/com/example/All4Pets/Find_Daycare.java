package com.example.All4Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Find_Daycare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_daycare);
    }
    public void gotoresults2 (View view){
        Intent intent = new Intent(Find_Daycare.this, Daycare_Results.class);
        startActivity(intent);

    }
}