package com.example.All4Pets.Daycares.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.All4Pets.R;


import java.util.Calendar;

public class Find_Daycare extends AppCompatActivity {

        //variable firebase





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_find_daycare);



/*
        findViewById(R.id.btn_checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
*/

        }





        public void gotoresults2(View view) {
            Intent intent = new Intent(Find_Daycare.this, Daycare_Results.class);
            startActivity(intent);

        }



    }
