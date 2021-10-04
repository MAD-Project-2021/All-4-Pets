package com.example.All4Pets.Doctors.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.All4Pets.Doctors.models.BookingModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;


import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CusDetailed extends AppCompatActivity  {

    //declare the variables

    EditText et_name, et_email, et_address, et_date, et_time;
    Button btn_save;
    String customerId;
    int hour,minute;

    DatePickerDialog.OnDateSetListener setListener;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_detailed);

        et_date = findViewById(R.id.et_date);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_time = findViewById(R.id.et_time);
        btn_save = findViewById(R.id.btn_save);



        //Date picker

        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CusDetailed.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;
                        et_date.setText(date);
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });



        customerId = getIntent().getStringExtra("customer_id");
        if (customerId != null) {
            db.collection("Customer")
                    .document(customerId)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                BookingModel model = task.getResult().toObject(BookingModel.class);

                                et_name.setText(model.name);
                                et_address.setText(model.address);
                                et_email.setText(model.email);
                                et_date.setText(model.date);
                                et_time.setText(model.time);
                            }
                        }
                    });
        } else {
            et_name.setText("");
            et_address.setText("");
            et_email.setText("");
            et_date.setText("");
            et_time.setText("");
        }


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //retrieve components
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String address = et_address.getText().toString();
                String date = et_date.getText().toString();
                String time = et_time.getText().toString();


                //if user doesn't enter any value in name field the error will be displayed
                if (TextUtils.isEmpty(name)) {
                    et_name.setError("name is required");
                    return;
                }

                //if user doesn't enter any value in email field the error will be displayed
                if (TextUtils.isEmpty(email)) {
                    et_email.setError("email is required");
                    return;
                }

                //if user doesn't enter any value in address field the error will be displayed
                if (TextUtils.isEmpty(address)) {
                    et_address.setError("address is required");
                    return;
                }

                //if user doesn't enter any value in date field the error will be displayed
                if (TextUtils.isEmpty(date)) {
                    et_date.setError("date is required");
                    return;
                }


                //if user doesn't enter any value in time field the error will be displayed
                if (TextUtils.isEmpty(time)) {
                    et_time.setError("time is required");
                    return;
                }

                //store data using hashmap (structure allowing one to store (key,value) items
                Map<String, Object> data = new HashMap<>();
                data.put("name", name);
                data.put("email", email);
                data.put("address", address);
                data.put("date", date);
                data.put("time", time);

                //create collection called 'Customer' and inside it create documents which are having cusID as the identifier
                //retrieve customerID of the currently registered in user
                DocumentReference docRef;
                if (customerId != null)
                    docRef = db.collection("Customer").document(customerId);
                else
                    docRef = db.collection("Customer").document();
                docRef.set(data)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                customerId = docRef.getId();
                                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(CusDetailed.this, Booking.class);
                                intent.putExtra("customer_id", customerId);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_SHORT).show());


            }
        });


    }


    //Time picker

    public void popTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                et_time.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));

            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style,onTimeSetListener,hour,minute,true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        db.collection("Customer").document(customerId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task){

                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        BookingModel model = task.getResult().toObject(BookingModel.class);

                        et_name.setText(model.name);
                        et_address.setText(model.address);
                        et_email.setText(model.email);
                        et_date.setText(model.date);
                        et_time.setText(model.time);

                    } else {
                        customerId = null;

                        et_name.setText("");
                        et_address.setText("");
                        et_email.setText("");
                        et_date.setText("");
                        et_time.setText("");
                    }
                }
            }
        });
    }
}

