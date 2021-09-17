package com.example.All4Pets.Daycare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.All4Pets.R;

public class Find_Daycare extends AppCompatActivity {

    Button btn_checkin;
    TextView tv_checkin;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    //Calender calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_daycare);

         btn_checkin = findViewById(R.id.btn_checkin);
         tv_checkin = findViewById(R.id.tv_checkin);
        //date button
        btn_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calendar = Calendar.getInstance();
                //year = calender.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Find_Daycare.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                            }
                        }, 0, 0, 0);
                datePickerDialog.show();

            }


        });



    }
    public void gotoresults2 (View view){
        Intent intent = new Intent(Find_Daycare.this, Daycare_Results.class);
        startActivity(intent);

    }


}