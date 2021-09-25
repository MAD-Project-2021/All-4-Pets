package com.example.All4Pets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.example.All4Pets.Category.ui.Orders;
import com.example.All4Pets.Category.ui.petShop;
import com.example.All4Pets.Daycares.Find_Daycare;
import com.example.All4Pets.Doctors.Activities.Find_Veterinary;
import com.example.All4Pets.Ratings.Ratings;


//import com.google.firebase.FirebaseApp;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void gotofirstpage (View view){
        Intent intent = new Intent(MainActivity.this, petShop.class);
        startActivity(intent);

    }

    public void gotoorderspage (View view){
        Intent intent = new Intent(MainActivity.this, Orders.class);
        startActivity(intent);

    }

    public void gotofindvet(View view){
        Intent intent = new Intent(MainActivity.this, Find_Veterinary.class);
        startActivity(intent);
    }

    public void gotoratingspage(View view){
        Intent intent = new Intent(MainActivity.this, Ratings.class);
        startActivity(intent);
    }

    public void gotofinddaycarepage(View view){
        Intent intent = new Intent(MainActivity.this, Find_Daycare.class);
        startActivity(intent);
    }



}