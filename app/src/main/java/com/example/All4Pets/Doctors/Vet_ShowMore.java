package com.example.All4Pets.Doctors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Doctors.models.MainModel;
import com.example.All4Pets.Doctors.models.ViewShowMoreModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Vet_ShowMore extends AppCompatActivity {

      ImageView img;
      TextView name,speciality,description,price,exp,ratings,location;
      Button appointment;

      ViewShowMoreModel viewShowMoreModel = null;
      private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_show_more);
        db = FirebaseFirestore.getInstance();

        final Object object = getIntent().getSerializableExtra("vet_show_more");
        if(object instanceof ViewShowMoreModel){
            viewShowMoreModel = (ViewShowMoreModel) object;
        }


        img = findViewById(R.id.img1);
        name = findViewById(R.id.name);
        speciality = findViewById(R.id.speciality);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        exp = findViewById(R.id.exp);
        ratings = findViewById(R.id.ratings);
        location = findViewById(R.id.location);
        appointment = findViewById(R.id.appointment);


        if(viewShowMoreModel != null){
//            Glide.with(getApplicationContext()).load(viewShowMoreModel.getImg_url()).into(img);
//            ratings.setText(viewShowMoreModel.getRate());
//            description.setText(viewShowMoreModel.getDescription());
//            price.setText("Price : "+viewShowMoreModel.getPrice());
//            exp.setText(viewShowMoreModel.getExp());
//            location.setText(viewShowMoreModel.getLocation());
//            name.setText(viewShowMoreModel.getName());
//            speciality.setText(viewShowMoreModel.getSpeciality());

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
            ratings.setText((String)doc.get("rate"));
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

    }


    public void redirecttoresults (View view){
        Intent intent = new Intent(Vet_ShowMore.this, Vet_Results.class);
        startActivity(intent);

    }
    public void gotodetailedpage (View view){
        Intent intent = new Intent(Vet_ShowMore.this, DetailedCustomer.class);
        startActivity(intent);

    }


}
