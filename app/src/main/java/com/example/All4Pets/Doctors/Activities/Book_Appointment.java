package com.example.All4Pets.Doctors.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.All4Pets.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Book_Appointment extends AppCompatActivity {

    private Button btn_confirm;
    TextView cus_name,cus_address,cus_email,cus_date,cus_time;
    Button et_edit;
    Button et_delete;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    String cusID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);


        cus_name = findViewById(R.id.cus_name);
        cus_address = findViewById(R.id.cus_address);
        cus_email = findViewById(R.id.cus_email);
        cus_date = findViewById(R.id.cus_date);
        cus_time = findViewById(R.id.cus_time);



        btn_confirm = findViewById(R.id.confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                showDialog();
            }
        });

        //instantiate firebase firestore and fAuth
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //cusID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = db.collection( "customer").document(cusID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                //using document snap shot we can retrieve data from database
                cus_name.setText(documentSnapshot.getString("Name"));
                cus_address.setText(documentSnapshot.getString("Address"));
                cus_email.setText(documentSnapshot.getString("Email"));
                cus_date.setText(documentSnapshot.getString("Sheduled_Date"));
                cus_time.setText(documentSnapshot.getString("Sheduled_Time"));
            }
        });

    }


    private void showDialog(){
        Dialog dialog = new Dialog(this,R.style.DialogStyle);
        dialog.setContentView(R.layout.confirm_dialog);

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);

        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });


        dialog.show();
    }



    public void gotofindvet (View view){
        Intent intent = new Intent(Book_Appointment.this, Find_Veterinary.class);
        startActivity(intent);

    }
    public void redirecttoshowmore (View view){
        Intent intent = new Intent(Book_Appointment.this, Vet_ShowMore.class);
        startActivity(intent);

    }

    public void gotodetailed (View view){
        Intent intent = new Intent(Book_Appointment.this, DetailedCustomer.class);
        startActivity(intent);

    }

}