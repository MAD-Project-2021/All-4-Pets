package com.example.All4Pets.Doctors.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Doctors.models.ViewShowMoreModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Vet_ShowMore extends AppCompatActivity {


    //Declare variables

      ImageView img,addfavourite;
      TextView name,speciality,description,price,exp,ratings,location;
      Button appointment;
      FirebaseAuth auth;
      ViewShowMoreModel viewShowMoreModel = null;
      private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_show_more);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("vet_show_more");
        if(object instanceof ViewShowMoreModel){
            viewShowMoreModel = (ViewShowMoreModel) object;
        }


        //connects database (backend code) with the Id

        img = findViewById(R.id.img1);
        name = findViewById(R.id.name);
        speciality = findViewById(R.id.speciality);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        exp = findViewById(R.id.exp);
        ratings = findViewById(R.id.ratings);
        location = findViewById(R.id.location);
        appointment = findViewById(R.id.appointment);
        addfavourite = findViewById(R.id.addfavourite);


        if(viewShowMoreModel != null){
            setValues(viewShowMoreModel.id);
        }


    }

    private void setValues(String id) {

        FirebaseFirestore instant = FirebaseFirestore.getInstance();
        instant.collection("Doctors").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot doc = task.getResult();

            Glide.with(getApplicationContext()).load(doc.get("img_url")).into(img);
            ratings.setText(doc.get("rate").toString());
            description.setText((String)doc.get("description"));
            price.setText((String)doc.get("price"));
            exp.setText((String)doc.get("exp"));
            location.setText((String)doc.get("location"));
            name.setText((String)doc.get("name"));
            speciality.setText((String)doc.get("speciality"));

                } else {
                    Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        // Add to favourite list

        addfavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addfavourite();
            }
        });
    }

    private void addfavourite() {

        String saveCurrentTime,saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy");   //set the formats
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> favMap = new HashMap<>();

        favMap.put("Doctor_name",name.getText().toString());      //store data in database
        favMap.put("Doctor_speciality",speciality.getText().toString());
        favMap.put("Doctor_price",price.getText().toString());
        favMap.put("CurrentTime",saveCurrentTime);
        favMap.put("CurrentDate",saveCurrentDate);



        db.collection("AddToFavouriteDoctor").document(auth.getCurrentUser().getUid())
                .collection("user").add(favMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Vet_ShowMore.this, "Added To Favourite list", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void redirecttoresults (View view){
        Intent intent = new Intent(Vet_ShowMore.this, Vet_Results.class);
        startActivity(intent);

    }
    public void gotodetailedpage (View view){
        Intent intent = new Intent(Vet_ShowMore.this, CusDetailed.class);
        startActivity(intent);

    }


}
