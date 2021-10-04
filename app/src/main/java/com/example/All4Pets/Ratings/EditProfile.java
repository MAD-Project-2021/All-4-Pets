package com.example.All4Pets.Ratings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText profileEmail, profileFullName , profilePhone;
    ImageView profileImage;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button saveButton;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent data = getIntent();
        String email = data.getStringExtra("email");
        String fullName = data.getStringExtra("fullName");
        String phone = data.getStringExtra("phone");


        profileEmail = findViewById(R.id.et_email);
        profileFullName = findViewById(R.id.et_fullname);
        profilePhone = findViewById(R.id.et_phone);
        profileImage = findViewById(R.id.profile_photo);
        saveButton = findViewById(R.id.btn_save);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();


        profileImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(EditProfile.this, "Profile Image Clicked", Toast.LENGTH_LONG).show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //first extract the data entered by user
                if(profileEmail.getText().toString().isEmpty() || profileFullName.getText().toString().isEmpty() || profilePhone.getText().toString().isEmpty()){
                    Toast.makeText(EditProfile.this, "One or many fields are empty", Toast.LENGTH_SHORT).show();
                    return;

                }

                //updating details
                String email = profileEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("email",email);
                        edited.put("fName",profileFullName.getText().toString());
                        edited.put("phone",profilePhone.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditProfile.this, "Your profile has been updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),View_Profile.class));
                                finish();
                            }
                        });


                        Toast.makeText(EditProfile.this, "Details updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        profileEmail.setText(email);
        profileFullName.setText(fullName);
        profilePhone.setText(phone);


        Log.d(TAG,"onCreate :" + email + " "+fullName +" "+phone);
    }
}