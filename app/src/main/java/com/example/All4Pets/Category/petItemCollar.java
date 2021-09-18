package com.example.All4Pets.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.All4Pets.R;

public class petItemCollar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_item_collar);
    }
    //Intent
    public void btn_backword (View view){
        Intent intent = new Intent(petItemCollar.this, petsItems.class);
        startActivity(intent);

    }
}