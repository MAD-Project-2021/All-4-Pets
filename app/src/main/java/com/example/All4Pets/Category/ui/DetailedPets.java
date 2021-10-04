package com.example.All4Pets.Category.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Category.models.PetItemsModel;
import com.example.All4Pets.Category.models.PetsModel;
import com.example.All4Pets.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedPets extends AppCompatActivity {

    ImageView detailedImg;
    TextView name, description,price;
    Button call;

    //new Pets
    PetsModel petsModel = null;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_pets);

        firestore = FirebaseFirestore.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed1");

        if (obj instanceof PetsModel){
            petsModel = (PetsModel) obj;
        }

        detailedImg = findViewById(R.id.detailed_img);
        name = findViewById(R.id.detailed_name1);
        description = findViewById(R.id.detailed_desc1);
        price = findViewById(R.id.detailed_price1);

        //call = findViewById(R.id.call);

        //new pets

        if (petsModel != null){
            Glide.with(getApplicationContext()).load(petsModel.getImg_url()).into(detailedImg);
            name.setText(petsModel.getName());
            description.setText(petsModel.getDescription());
            price.setText(String.valueOf(petsModel.getPrice()));

        }
    }
}