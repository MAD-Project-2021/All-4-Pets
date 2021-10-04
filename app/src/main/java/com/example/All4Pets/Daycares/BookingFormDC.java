package com.example.All4Pets.Daycares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.All4Pets.Daycares.Activities.ChangeBookingd;
import com.example.All4Pets.Daycares.Activities.Daycare_Results;
import com.example.All4Pets.Daycares.Activities.Find_Daycare;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookingFormDC extends AppCompatActivity {

    Button btn_sve;
    EditText et_cname,et_contact,et_cemail,et_chkdate2,et_chkdate;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form_dc);

        btn_sve = findViewById(R.id.btn_sve);
        et_cname = findViewById(R.id.et_cname);
        et_contact = findViewById(R.id.et_contact);
        et_cemail = findViewById(R.id.et_cemail);
        et_chkdate2 = findViewById(R.id.et_chkdate2);
        et_chkdate = findViewById(R.id.et_chkdate);

        btn_sve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_cname.getText().toString();
                String contact = et_contact.getText().toString();
                String email = et_cemail.getText().toString();
                String date1 = et_chkdate2.getText().toString();
                String date2 = et_chkdate.getText().toString();

                Map<String,Object> data= new HashMap<>();
                data.put("name", name);
                data.put("contact", contact);
                data.put("email", email);
                data.put("chkindate", date1);
                data.put("chkoutdate", date2);



                db.collection("Day_Customer").document("user").set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(BookingFormDC.this, ChangeBookingd.class);
                                startActivity(intent);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "unsaved", Toast.LENGTH_LONG).show();

                            }
                        });
            }
        });
    }
}