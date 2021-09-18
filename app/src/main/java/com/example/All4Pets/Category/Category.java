package com.example.All4Pets.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.All4Pets.R;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }
    //Intent
    public void btn_pets (View view){
        Intent intent = new Intent(Category.this, Pets.class);
        startActivity(intent);

    }
    //Intent
    public void btn_petsItems (View view){
        Intent intent = new Intent(Category.this, petsItems.class);
        startActivity(intent);

    }
    //Intent
    public void btn_supliments(View view){
        Intent intent = new Intent(Category.this, petsItems.class);
        startActivity(intent);

    }

}
