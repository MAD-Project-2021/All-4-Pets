package com.example.All4Pets.Daycares.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.All4Pets.Daycares.models.DaycareBookingModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Daycare_Booking_Form extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //declare the variables

    EditText et_cname ,et_contact, et_cemail, et_chkdate2, et_chkdate;
    Button btn_sve;
    //FirebaseAuth fAuth;
    //FirebaseFirestore db ;  //can create collections and documents
    String cusID;

    DatePickerDialog.OnDateSetListener setListener;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daycare_booking_form);

        //db = FirebaseFirestore.getInstance();

        et_chkdate2 = findViewById(R.id.et_chkdate2);
        et_cname = findViewById(R.id.et_cname);
        et_cemail = findViewById(R.id.et_cemail);
        et_contact = findViewById(R.id.et_contact);
        et_chkdate = findViewById(R.id.et_chkdate);
        btn_sve = findViewById(R.id.btn_sve);

        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
//


        et_chkdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Daycare_Booking_Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;
                        et_chkdate2.setText(date);
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });

        et_chkdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Daycare_Booking_Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        String date1 = day+"/"+month+"/"+year;
                        et_chkdate.setText(date1);
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });


//        findViewById(R.id.et_chkdate).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        cusID = getIntent().getStringExtra("customer_id");
        if(cusID!=null){
            db.collection("Day_Customer")
                    .document(cusID)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DaycareBookingModel model = task.getResult().toObject(DaycareBookingModel.class);
                                et_cname.setText(model.name);
                                et_contact.setText(model.contact);
                                et_cemail.setText(model.address);
                                et_chkdate2.setText(model.checkin);
                                et_chkdate.setText(model.checkout);
                            }
                        }
                    });
        } else {
            et_cname.setText("");
            et_contact.setText("");
            et_cemail.setText("");
            et_chkdate2.setText("");
            et_chkdate.setText("");
        }



        btn_sve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //retrieve components
                String name = et_cname.getText().toString().trim();
                String email = et_cemail.getText().toString().trim();
                String contact = et_contact.getText().toString();
                String date1 = et_chkdate.getText().toString();
                String date2 = et_chkdate2.getText().toString();


                //if user doesn't enter any value in name field the error will be displayed
                if (TextUtils.isEmpty(name)) {
                    et_cname.setError("name is required");
                    return;
                }

                //if user doesn't enter any value in email field the error will be displayed
                if (TextUtils.isEmpty(email)) {
                    et_cemail.setError("email is required");
                    return;
                }

                //if user doesn't enter any value in address field the error will be displayed
                if (TextUtils.isEmpty(contact)) {
                    et_contact.setError("address is required");
                    return;
                }

                //if user doesn't enter any value in date field the error will be displayed
                if (TextUtils.isEmpty(date1)) {
                    et_chkdate.setError("date is required");
                    return;
                }


                //if user doesn't enter any value in time field the error will be displayed
                if (TextUtils.isEmpty(date2)) {
                    et_chkdate2.setError("date is required");
                    return;
                }

                //store data using hashmap
                Map<String, Object> data = new HashMap<>();
                data.put("name", name);
                data.put("email", email);
                data.put("contact", contact);
                data.put("checkin", date1);
                data.put("checkout", date2);

                //create collection called 'Customer' and inside it create documents which are having cusID as the identifier
                //retrieve customerID of the currently registered in user
                DocumentReference daycRef;
                if (cusID!=null)
                    daycRef = db.collection("Day_Customer").document(cusID);
                else
                    daycRef = db.collection("Day_Customer").document();
                daycRef.set(data)
                    .addOnCompleteListener(task ->{
                        if (task.isSuccessful()){
                            cusID = daycRef.getId();
                            Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Daycare_Booking_Form.this, ChangeBookingd.class);
                            intent.putExtra("customer_id", cusID);
                            startActivity(intent);

                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_SHORT).show());


                //Add the customer to the database
         /*       fAuth(et_cname,et_cemail,et_contact,et_chkdate,et_chkdate2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Daycare_Booking_Form.this, "User added", Toast.LENGTH_LONG).show();


                            cusID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Customers").document(cusID);
                            //store data using hashmap
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", name);
                            user.put("email", email);
                            user.put("address", contact);
                            user.put("date", date1);
                            user.put("time", date2);

                            //insert to firestore database
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created for" + cusID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), View_Profile.class));

                        } else {
                            Toast.makeText(Daycare_Booking_Form.this, "Please fill the Form", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/



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

//            private Task<AuthResult> fAuth(EditText et_cname, EditText et_cemail, EditText et_contact, EditText et_chkdate, EditText et_chkdate2) {
//                //return null;
//            }
//
//            private void addOnCompleteListener(OnCompleteListener<AuthResult> authResultOnCompleteListener) {
//
//            }








        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void gotoedit (View view){
        Intent intent = new Intent(Daycare_Booking_Form.this, ChangeBookingd.class);
        startActivity(intent);
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        db.collection("Day_Customer").document(cusID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    if (task.getResult().exists()) {
                        DaycareBookingModel model = task.getResult().toObject(DaycareBookingModel.class);
                        et_cname.setText(model.name);
                        et_contact.setText(model.contact);
                        et_cemail.setText(model.address);
                        et_chkdate2.setText(model.checkin);
                        et_chkdate.setText(model.checkout);


                    } else {
                        cusID = null;

                        et_cname.setText("");
                        et_contact.setText("");
                        et_cemail.setText("");
                        et_chkdate2.setText("");
                        et_chkdate.setText("");
                    }

                }
            }
        });
    }
}