package com.example.All4Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void gotofirstpage (View view){
        Intent intent = new Intent(MainActivity.this, Category.class);
        startActivity(intent);

    }

    public void gotoorderspage (View view){
        Intent intent = new Intent(MainActivity.this, Orders.class);
        startActivity(intent);

    }

    public void gotoservicespage(View view){
        Intent intent = new Intent(MainActivity.this, Services.class);
        startActivity(intent);
    }

    public void gotoratingspage(View view){
        Intent intent = new Intent(MainActivity.this, Ratings.class);
        startActivity(intent);
    }



}