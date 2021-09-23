package com.example.All4Pets.Category.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Category.models.PetItemsModel;
import com.example.All4Pets.Category.models.SupplementsModel;
import com.example.All4Pets.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedItems extends AppCompatActivity {

    ImageView detailedImg;
    TextView name, description,price;
    Button addToCart,buyNow;
    ImageView addItems,removeItems;

    // PetsItems

    PetItemsModel petItemsModel = null;

    //Supplements
    SupplementsModel supplementsModel= null;


    private FirebaseFirestore firestore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_items);

        firestore = FirebaseFirestore.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof PetItemsModel){
            petItemsModel = (PetItemsModel) obj;
        }else if (obj instanceof SupplementsModel){
            supplementsModel = (SupplementsModel) obj;
        }

        detailedImg = findViewById(R.id.detailed_img);
        name = findViewById(R.id.detailed_name);
        description = findViewById(R.id.detailed_desc);
        price = findViewById(R.id.detailed_price);

        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);

        addItems = findViewById(R.id.add_item);
        removeItems = findViewById(R.id.remove_item);

        //Pet Items

        if (petItemsModel != null){
            Glide.with(getApplicationContext()).load(petItemsModel.getImg_url()).into(detailedImg);
            name.setText(petItemsModel.getName());
            description.setText(petItemsModel.getDescription());
            price.setText(String.valueOf(petItemsModel.getPrice()));
        }


        //Supplements

        if (supplementsModel!= null){
            Glide.with(getApplicationContext()).load(supplementsModel.getImg_url()).into(detailedImg);
            name.setText(supplementsModel.getName());
            description.setText(supplementsModel.getDescription());
            price.setText(String.valueOf(supplementsModel.getPrice()));
        }



    }



}