package com.example.All4Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Daycare_Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daycare_results);
    }

    public void gotofavpage2(View view) {
        Intent intent = new Intent(Daycare_Results.this, Fav_DaycareList.class);
        startActivity(intent);


    }

    public void gotoshowmore(View view) {
        Intent intent = new Intent(Daycare_Results.this, Daycare_ShowMore.class);
        startActivity(intent);

    }
}