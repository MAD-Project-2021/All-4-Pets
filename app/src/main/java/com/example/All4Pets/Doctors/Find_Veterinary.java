package com.example.All4Pets.Doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.All4Pets.Daycares.Daycare_Results;
import com.example.All4Pets.Daycares.Find_Daycare;
import com.example.All4Pets.R;

import java.util.ArrayList;
import java.util.Calendar;

public  class Find_Veterinary extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        EditText et_date;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_find_veterinary);

//            Spinner spinner = findViewById(R.id.spinner1);
//            ArrayList<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.time_Slots, android.R.layout.sample_spinner_item);
//            MyAdapter.setDropDownViewResources(android.R.layout.sample_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
//            spinner.setOnItemSelectedListener(this);


            et_date = findViewById(R.id.et_date);

            findViewById(R.id.et_date).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog();
                }
            });

        }

        private void showDatePickerDialog() {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (DatePickerDialog.OnDateSetListener) this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        }




        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            String inDate = month + 1 + "/" + dayOfMonth + "/" + year;
            et_date.setText(inDate);

            //String outDate = month +1+ "/" + dayOfMonth + "/" + year;
            // tv_checkout.setText(outDate);


        }



    public void gotoresults (View view){
        Intent intent = new Intent(getApplicationContext(), Vet_Results.class);
        startActivity(intent);

    }



//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String text = parent.getItemAtPosition(position).toString();
//       Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}