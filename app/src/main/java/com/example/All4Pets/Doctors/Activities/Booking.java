package com.example.All4Pets.Doctors.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.All4Pets.Doctors.Adapters.BookingAdapter;
import com.example.All4Pets.Doctors.models.BookingModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.DocumentDelete;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Booking extends AppCompatActivity {


    TextView tv_name, tv_address, tv_email, tv_date, tv_time;
    Button btn_edit, btn_delete1, btn_confirm;

    String customerId;

    FirebaseFirestore db;
    DatabaseReference mRef;
    private List<BookingModel> list;
    private BookingAdapter bookingAdapter;
    private Thread documentSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        db = FirebaseFirestore.getInstance();     //FirebaseFirestore connection


        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        tv_email = findViewById(R.id.tv_email);
        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        btn_edit = findViewById(R.id.btn_edit);
        btn_delete1 = findViewById(R.id.btn_delete1);
        btn_confirm = findViewById(R.id.btn_confirm);


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


        //update onclick

        btn_edit.setOnClickListener(new View.OnClickListener() {    //update button onclick
            @Override
            public void onClick(View view) {

                String customerName = tv_name.getText().toString();   //when user inputs
                String customerAddress = tv_address.getText().toString();
                String customerEmail = tv_email.getText().toString();
                String scheduledDate = tv_date.getText().toString();
                String scheduledTime = tv_time.getText().toString();

                tv_name.setText("");          //set the user inputs for the variables
                tv_address.setText("");
                tv_email.setText("");
                tv_date.setText("");
                tv_time.setText("");

                btn_edit(customerName, customerAddress, customerEmail, scheduledDate, scheduledTime);

            }
        });


        customerId = getIntent().getStringExtra("customer_id");
        db.collection("Customer")
                .document(customerId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        BookingModel model = task.getResult().toObject(BookingModel.class);

                        tv_name.setText(model.name);
                        tv_address.setText(model.address);
                        tv_email.setText(model.email);
                        tv_date.setText(model.date);
                        tv_time.setText(model.time);
                    }
                });


        // on edit button click
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent editIntent = new Intent(Booking.this, CusDetailed.class);
                editIntent.putExtra("customer_id", customerId);
                finish();

            }
        });


        //Retrieve user data from the db

//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                PolicyNode dataSnapshot = null;
//                for (Iterator<? extends PolicyNode> it = dataSnapshot.getChildren(); it.hasNext(); ) {
//                    DataSnapshot ds = (DataSnapshot) it.next();
//
//                    BookingModel model = ds.getValue(BookingModel.class);
//                    String tv_name = BookingModel.getTv_name();
//                    String tv_address = BookingModel.getTv_address();
//                    String tv_email = BookingModel.getTv_email();
//                    String tv_date = BookingModel.getTv_date();
//                    String tv_time = BookingModel.getTv_time();
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//
//        });

        list = new ArrayList<>();
        bookingAdapter = new BookingAdapter(getApplicationContext(), list, this);



        //Delete operation


        btn_delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("Customer")
                        .document(customerId)
                        .delete()
                        .addOnSuccessListener(unused -> {                 //if successfully deleted
                            Toast.makeText(Booking.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Booking.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }


    //delete method


    private void DeleteData(String tv_name) {

        db.collection("Customer")
                .whereEqualTo("name", tv_name)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful() && !task.getResult().isEmpty()) {

                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    String documentID = documentSnapshot.getId();
                    db.collection("Customer")
                            .document(documentID)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {                 //if successfully deleted
                                    Toast.makeText(Booking.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {               // if there is an error

                            Toast.makeText(Booking.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {

                    Toast.makeText(Booking.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

        });


    }


    //Update Operation method

    private void btn_edit(String customerName, String customerAddress, String customerEmail, String scheduledDate, String scheduledTime) {

        Map<String, Object> UserDetails = new HashMap<>();       //while updating data choose arguments for updation in to the hashmap
        UserDetails.put("Name", customerName);

        db.collection("User")        // create and store data in db
                .whereEqualTo("Name", customerName)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {     //after complete check if else


            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && !task.getResult().isEmpty()) {

                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);       //snapshots
                    String documentId = documentSnapshot.getId();
                    db.collection("User")
                            .document(documentId)
                            .update(UserDetails)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Booking.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Booking.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(Booking.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void showDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.confirm_dialog);
        ImageView imageView_close = dialog.findViewById(R.id.btn_close);
        imageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button btn_ok = dialog.findViewById(R.id.btn_gotit);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    public void detailed(View view) {
        Intent intent = new Intent(Booking.this, CusDetailed.class);
        startActivity(intent);

    }

    public void gotofindvet(View view) {
        Intent intent = new Intent(Booking.this, CusDetailed.class);
        startActivity(intent);

    }


}