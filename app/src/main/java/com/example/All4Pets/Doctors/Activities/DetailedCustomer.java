package com.example.All4Pets.Doctors.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.All4Pets.R;
import com.example.All4Pets.Ratings.View_Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DetailedCustomer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //declare the variables

    EditText et_name , et_email, et_address, et_date,et_time;
    Button btn_save;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore ;  //can create collections and documents
    String cusID;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_customer);

        et_date = findViewById(R.id.et_date);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_time = findViewById(R.id.et_time);
        btn_save = findViewById(R.id.btn_save);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //retrieve components
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
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


                //Add the customer to the database
                fAuth(et_name,et_address,et_email,et_date,et_time).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DetailedCustomer.this, "User added", Toast.LENGTH_LONG).show();

                            //create collection called 'Customer' and inside it create documents which are having cusID as the identifier
                            //retrieve customerID of the currently registered in user
                            cusID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Customers").document(cusID);

                            //store data using hashmap
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", name);
                            user.put("email", email);
                            user.put("address", address);
                            user.put("date", date);
                            user.put("time", time);

                            //insert to firestore database
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created for" + cusID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), View_Profile.class));
                        } else {
                            Toast.makeText(DetailedCustomer.this, "Please fill the Form", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



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
//
//                public void gotobookpage (View view){
//                    Intent intent = new Intent(DetailedCustomer.this, Book_Appointment.class);
//                    startActivity(intent);
//                }
            }

            private void addOnCompleteListener(OnCompleteListener<AuthResult> authResultOnCompleteListener) {

            }

        });
    }

    private Task<AuthResult> fAuth(EditText et_name, EditText et_address, EditText et_email, EditText et_date, EditText et_time) {


        return null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


