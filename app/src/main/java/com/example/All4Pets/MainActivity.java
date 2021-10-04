package com.example.All4Pets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.All4Pets.Category.ui.petShop;
import com.example.All4Pets.Daycares.Activities.Find_Daycare;
import com.example.All4Pets.Doctors.Activities.Vet_Results;
import com.example.All4Pets.Ratings.Ratings;
import com.example.All4Pets.Ratings.View_Profile;


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



    public void gotofindvet(View view){
        Intent intent = new Intent(MainActivity.this, Vet_Results.class);
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


    public void gotoprofile(View view){
        Intent intent = new Intent(MainActivity.this, View_Profile.class);
        startActivity(intent);
    }






}