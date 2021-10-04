package com.example.All4Pets.Daycares.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.All4Pets.Daycares.Adapters.DaycareBookingAdapter;
import com.example.All4Pets.Daycares.models.DaycareBookingModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeBookingd extends AppCompatActivity {

    private Button btn_confirmbooking;
    TextView tv_custname,tv_phn,tv_cmail,chk_date,chkout_date;
    Button btn_edit, btn_del;

    private Thread documentSnapshot;
    FirebaseFirestore db;
    DatabaseReference mRef;

    String cusID;

    private List<DaycareBookingModel> list;
    private DaycareBookingAdapter daycareBookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bookingd);

        db = FirebaseFirestore.getInstance();

        //tv_dc_name = findViewById(R.id.tv_dc_name);
       // tv_locationd = findViewById(R.id.tv_locationd);
        tv_custname = findViewById(R.id.tv_custname);
        tv_phn = findViewById(R.id.tv_phn);
        tv_cmail = findViewById(R.id.tv_cmail);
        chk_date = findViewById(R.id.chk_date);
        chkout_date = findViewById(R.id.chkout_date);
        btn_confirmbooking = findViewById(R.id.btn_confirmbooking);
        btn_edit = findViewById(R.id.btn_edit);
        btn_del = findViewById(R.id.btn_del);


        //update onclick
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tv_custname.getText().toString();
                String contact = tv_phn.getText().toString();
                String email = tv_cmail.getText().toString();
                String checkin = chk_date.getText().toString();
                String checkout = chkout_date.getText().toString();

                tv_custname.setText("");
                tv_phn.setText("");
                tv_cmail.setText("");
                chk_date.setText("");
                chkout_date.setText("");

                btn_edit(name, contact,email,checkin,checkout );

            }
        });

        cusID = getIntent().getStringExtra("customer_id");
        db.collection("Day_Customer")
                .document(cusID)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DaycareBookingModel model = task.getResult().toObject(DaycareBookingModel.class);

                        tv_custname.setText(model.name);
                        tv_phn.setText(model.contact);
                        tv_cmail.setText(model.email);
                        chk_date.setText(model.checkin);
                        chkout_date.setText(model.checkout);
                    }
                });

        // on edit button click
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent editIntent = new Intent(ChangeBookingd.this, Daycare_Booking_Form.class);
                editIntent.putExtra("customer_id", cusID);
                finish();

            }
        });

        ////Retrieve user data from the db

//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                PolicyNode dataSnapshot = null;
//                for (Iterator<? extends PolicyNode> it = dataSnapshot.getChildren(); it.hasNext(); ) {
//                    DataSnapshot ds = (DataSnapshot) it.next();
//
//                    DaycareBookingModel list = ds.getValue(DaycareBookingModel.class);
//                    String cusName = DaycareBookingModel.getCusName();
//                    String cusCont = DaycareBookingModel.getCusCont();
//                    String cusEmail = DaycareBookingModel.getCusEmail();
//                    String cinDate = DaycareBookingModel.getCinDate();
//                    String coutDate = DaycareBookingModel.getCoutDate();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        list = new ArrayList<>();
       // daycareBookingAdapter= new DaycareBookingAdapter(getApplicationContext(), list, this);
        daycareBookingAdapter = new DaycareBookingAdapter(getApplicationContext(), list, this );

        //delete operation
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("Day_Customer")
                        .document(cusID)
                        .delete()
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(ChangeBookingd.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ChangeBookingd.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




//        btn_del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String cusName = tv_custname.getText().toString();
//                String cusCont = tv_phn.getText().toString();
//                String cusEmail = tv_cmail.getText().toString();
//                String cinDate = chk_date.getText().toString();
//                String coutDate = chkout_date.getText().toString();
//
//                tv_custname.setText("");
//                tv_phn.setText("");
//                tv_cmail.setText("");
//                chk_date.setText("");
//                chkout_date.setText("");
//
//                DeleteDate(cusName);
//                DeleteDate(cusCont);
//                DeleteDate(cusEmail);
//                DeleteDate(cinDate);
//                DeleteDate(coutDate);
//            }
//        });




        btn_confirmbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    //delete method
    private void DeleteDate(String tv_custname){

        db.collection("Day_Customer")
                .whereEqualTo("name", tv_custname)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty()){

                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    String documentID = documentSnapshot.getId();
                    db.collection("Day_Customer")
                            .document(documentID)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(ChangeBookingd.this,"Successfully Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChangeBookingd.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{

                    Toast.makeText(ChangeBookingd.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Update Operation method

    private void btn_edit(String cusName,String cusCont, String cusEmail, String cinDate, String coutDate){

        Map<String,Object> UserDetails = new HashMap<>();
        UserDetails.put("name",cusName);

        db.collection("Day_Customer")
                .whereEqualTo("name", cusName)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty()){

                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);       //snapshots
                    String documentId = documentSnapshot.getId();
                    db.collection("Day_Customer")
                            .document(documentId)
                            .update(UserDetails)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(ChangeBookingd.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChangeBookingd.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(ChangeBookingd.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void backtoform(View view){
       Intent intent = new Intent(ChangeBookingd.this, Daycare_Booking_Form.class);
       startActivity(intent);

   }

    public void gotofirst(View view){
        Intent intent = new Intent(ChangeBookingd.this, Find_Daycare.class);
        startActivity(intent);

    }


    private void showDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_custom_dialog);

        ImageView imageView_close = dialog.findViewById(R.id.imageView_close);
        imageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button btn_ok = dialog.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}