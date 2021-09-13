package com.example.All4Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class petsItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_items);
    }
    //Intent
    public void btn_petItem1 (View view){
        Intent intent = new Intent(petsItems.this, itemC.class);
        startActivity(intent);

    }
    //Intent
    public void btn_petItem4 (View view){
        Intent intent = new Intent(petsItems.this, petItemCollar.class);
        startActivity(intent);

    }

    //Intent
    public void btn_backword (View view){
        Intent intent = new Intent(petsItems.this, Category.class);
        startActivity(intent);

    }
}