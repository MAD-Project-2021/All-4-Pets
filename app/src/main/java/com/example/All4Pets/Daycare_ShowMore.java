package com.example.All4Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Daycare_ShowMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daycare_show_more);
    }
    public void redirecttoresults2 (View view){
        Intent intent = new Intent(Daycare_ShowMore.this, Daycare_Results.class);
        startActivity(intent);

    }

    public void gotobookdaycarepg (View view){
        Intent intent = new Intent(Daycare_ShowMore.this, Book_Daycare.class);
        startActivity(intent);

    }

}