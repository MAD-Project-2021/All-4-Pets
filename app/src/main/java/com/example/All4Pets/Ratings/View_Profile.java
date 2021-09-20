package com.example.All4Pets.Ratings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.All4Pets.Login;
import com.example.All4Pets.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class View_Profile extends AppCompatActivity {
    TextView fullName,email,phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    ImageView profileImage;
    Button changeProfileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        email = findViewById(R.id.et_email);
        fullName = findViewById(R.id.et_fullname);
        phone = findViewById(R.id.et_phone);
        profileImage = findViewById(R.id.profile_photo);
        changeProfileImage=findViewById(R.id.btn_editprofile);


        //instantiate firebase firestore and fAuth
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                //using document snap shot we can retrieve data from database
                email.setText(documentSnapshot.getString("email"));
                fullName.setText(documentSnapshot.getString("fName"));
                phone.setText(documentSnapshot.getString("phone"));
            }
        });

        changeProfileImage.setOnClickListener((v)->{

                //open gallery
                //Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(openGalleryIntent,1000);

                Intent i = new Intent(v.getContext(),EditProfile.class);
                i.putExtra("email", email.getText().toString());
                i.putExtra("fullName", fullName.getText().toString());
                i.putExtra("phone",phone.getText().toString());
                startActivity(i);

        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //gallery is invoking onActivity result
       if(requestCode ==1000){
           if(resultCode== Activity.RESULT_OK){
               //get the uri of image from gallery
               Uri imageUri = data.getData();
               profileImage.setImageURI(imageUri);
           }
       }
    }

    public void profileBack(View view){
        //log out from application
        FirebaseAuth.getInstance().signOut();

        //once log out they are sent to Login page
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}