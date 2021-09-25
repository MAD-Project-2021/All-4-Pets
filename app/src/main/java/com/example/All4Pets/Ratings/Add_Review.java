package com.example.All4Pets.Ratings;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.All4Pets.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Add_Review extends AppCompatActivity {

    //variables
    EditText feedback;
    RatingBar rating;
    Button send ;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;//can create collections and documents
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        feedback = findViewById(R.id.et_feedback);
        rating = findViewById(R.id.rb_rating);
        send = findViewById(R.id.btn_send);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


    }
}