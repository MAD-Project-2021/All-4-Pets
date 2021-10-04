package com.example.All4Pets.Category.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Category.models.PetItemsModel;
import com.example.All4Pets.Category.models.SupplementsModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedItems extends AppCompatActivity {

    ImageView detailedImg;
    TextView name, description,price,quantity;
    Button addToCart,buyNow;
    ImageView addItems,removeItems;

    int totalQuantity = 1;
    int totalPrice = 0;

    // PetsItems

    PetItemsModel petItemsModel = null;

    //Supplements
    SupplementsModel supplementsModel= null;

    FirebaseFirestore firestore;
    FirebaseAuth auth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_items);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof PetItemsModel){
            petItemsModel = (PetItemsModel) obj;
        }else if (obj instanceof SupplementsModel){
            supplementsModel = (SupplementsModel) obj;
        }

        detailedImg = findViewById(R.id.detailed_img);
        quantity = findViewById(R.id.quantity);
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

            totalPrice = petItemsModel.getPrice() * totalQuantity;
        }


        //Supplements

        if (supplementsModel!= null){
            Glide.with(getApplicationContext()).load(supplementsModel.getImg_url()).into(detailedImg);
            name.setText(supplementsModel.getName());
            description.setText(supplementsModel.getDescription());
            price.setText(String.valueOf(supplementsModel.getPrice()));

            totalPrice = supplementsModel.getPrice() * totalQuantity;
        }

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailedItems.this,CAddress.class);


                if (petItemsModel != null){
                    intent.putExtra("item",petItemsModel);
                }
                if (supplementsModel != null){
                    intent.putExtra("item",supplementsModel);
                }
                startActivity(intent);

            }
        });


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(totalQuantity<10){
                   totalQuantity++;
                   quantity.setText(String.valueOf(totalQuantity));

                   if(petItemsModel != null){
                       totalPrice = petItemsModel.getPrice() * totalQuantity;
                   }
                   if (supplementsModel != null){
                       totalPrice = supplementsModel.getPrice() * totalQuantity;
                   }
               }
            }
        });

        removeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(totalQuantity>1){
                        totalQuantity--;
                        quantity.setText(String.valueOf(totalQuantity));
                        if(petItemsModel != null){
                            totalPrice = petItemsModel.getPrice() * totalQuantity;
                        }
                        if (supplementsModel != null){
                            totalPrice = supplementsModel.getPrice() * totalQuantity;
                        }
                    }
                }
        });



    }

    private void addToCart() {

        String saveCurrentTime,saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",name.getText().toString());
        cartMap.put("ProductPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

//auth.getCurrentUser().getUid()
        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedItems.this, "Added To A Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}