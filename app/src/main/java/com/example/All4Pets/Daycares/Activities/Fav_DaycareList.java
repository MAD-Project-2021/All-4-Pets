package com.example.All4Pets.Daycares.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.All4Pets.R;

public class Fav_DaycareList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_daycare_list);
    }

    public void gotodaycareresultback(View view) {
        Intent intent = new Intent(Fav_DaycareList.this, Daycare_Results.class);
        startActivity(intent);

    }


}