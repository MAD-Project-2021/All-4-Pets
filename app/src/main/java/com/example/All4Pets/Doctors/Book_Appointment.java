package com.example.All4Pets.Doctors;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.All4Pets.Login;
import com.example.All4Pets.R;
import com.example.All4Pets.Ratings.EditProfile;
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

        cusID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Customers").document(cusID);
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

//        changeProfileImage.setOnClickListener((v)->{
//
//            //open gallery
//            //Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            //startActivityForResult(openGalleryIntent,1000);
//
//            Intent i = new Intent(v.getContext(), EditProfile.class);
//            i.putExtra("email", email.getText().toString());
//            i.putExtra("fullName", fullName.getText().toString());
//            i.putExtra("phone",phone.getText().toString());
//            startActivity(i);
//
//        });


    }

//    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //gallery is invoking onActivity result
//        if(requestCode ==1000){
//            if(resultCode== Activity.RESULT_OK){
//                //get the uri of image from gallery
//                Uri imageUri = data.getData();
//                profileImage.setImageURI(imageUri);
//            }
//        }
//    }

//    public void profileBack(View view){
//        //log out from application
//        FirebaseAuth.getInstance().signOut();
//
//        //once log out they are sent to Login page
//        startActivity(new Intent(getApplicationContext(), Login.class));
//        finish();
//    }

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