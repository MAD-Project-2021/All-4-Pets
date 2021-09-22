package com.example.All4Pets.Doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.All4Pets.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedCustomer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


//    EditText et_name , et_email, et_address, et_date,et_time;
//    Button btn_save;
//    FirebaseAuth fAuth;
//    ProgressBar pb_progressBar;
//    FirebaseFirestore fStore;//can create collections and documents
//    String cusID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_customer);

//        et_date = findViewById(R.id.et_date);
//
//        findViewById(R.id.et_date).setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

//    private void showDatePickerDialog() {
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                this,
//                (DatePickerDialog.OnDateSetListener) this,
//                Calendar.getInstance().get(Calendar.YEAR),
//                Calendar.getInstance().get(Calendar.MONTH),
//                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//        );
//        datePickerDialog.show();
//    }
//
//
//    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
//        String inDate = month + 1 + "/" + dayOfMonth + "/" + year;
//        et_date.setText(inDate);
//
//
//
//    }

    public void gotobookpage (View view){
        Intent intent = new Intent(DetailedCustomer.this, Book_Appointment.class);
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    }
