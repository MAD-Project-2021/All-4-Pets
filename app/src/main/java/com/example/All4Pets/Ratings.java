package com.example.All4Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Ratings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        Spinner ratingsSpinner = (Spinner) findViewById(R.id.spinner_ratings);

        ArrayAdapter<String> ratingsAdapter = new ArrayAdapter<String>(Ratings.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner));

        ratingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingsSpinner.setAdapter(ratingsAdapter);

        //for buttons we use onClick Listener and for spinners we use onItemSelectedListener()
        ratingsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    startActivity(new Intent(Ratings.this,Add_Review.class));
                }
                else if(i==2){
                        startActivity(new Intent(Ratings.this,View_Feedback.class));
                } else if(i==3){
                        startActivity(new Intent(Ratings.this,FAQ.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}