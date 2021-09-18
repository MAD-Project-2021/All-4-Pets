package com.example.All4Pets.Daycares;

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

public class Find_Daycare extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {




        Button btn_checkin;
        TextView tv_checkin;
        //Button btn_checkout;
        //TextView tv_checkout;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_find_daycare);

            btn_checkin = findViewById(R.id.btn_checkin);
            //btn_checkout = findViewById(R.id.btn_checkout);
            //checkout button and text view
            tv_checkin = findViewById(R.id.tv_checkin);
            //tv_checkout = findViewById(R.id.tv_checkout);

            findViewById(R.id.btn_checkin).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog();
                }
            });
/*
        findViewById(R.id.btn_checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
*/

        }

        private void showDatePickerDialog() {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        }

        public void gotoresults2(View view) {
            Intent intent = new Intent(Find_Daycare.this, Daycare_Results.class);
            startActivity(intent);

        }


        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            String inDate = month + 1 + "/" + dayOfMonth + "/" + year;
            tv_checkin.setText(inDate);

            //String outDate = month +1+ "/" + dayOfMonth + "/" + year;
            // tv_checkout.setText(outDate);


        }
    }
