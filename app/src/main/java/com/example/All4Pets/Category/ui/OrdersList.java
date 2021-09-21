package com.example.All4Pets.Category.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.All4Pets.R;

public class OrdersList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
    }

    public void gotoorderspage (View view){
        Intent intent = new Intent(this, Orders.class);
        startActivity(intent);

    }
}