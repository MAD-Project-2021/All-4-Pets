package com.example.All4Pets.Category.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.All4Pets.R;

public class UpdateDispute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dispute);
    }

    public void gotodisputelistpage (View view){
        Intent intent = new Intent(this, DisputeList.class);
        startActivity(intent);

    }
}