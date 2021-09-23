package com.example.All4Pets.Category.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.All4Pets.Category.adapters.PetsItemsAdapter;
import com.example.All4Pets.Category.adapters.SupplementsAdapter;
import com.example.All4Pets.Category.adapters.petsAdapters;
import com.example.All4Pets.Category.models.PetItemsModel;
import com.example.All4Pets.Category.models.PetsModel;
import com.example.All4Pets.Category.models.SupplementsModel;
import com.example.All4Pets.MainActivity;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class petShop extends AppCompatActivity {

    TextView petsShowAll, PetItemsShowAll, SuplimentsShowAll;

    RecyclerView petsRec, petItemsRec, supplementsRec;
    FirebaseFirestore db;
    //Pets
    List<PetsModel> petsModelList;
    petsAdapters petsAdapters;

    //Pets Items
    List<PetItemsModel> petItemsModelList;
    PetsItemsAdapter petsItemsAdapter;

    //Supplements
    List<SupplementsModel> supplementsModelList;
    SupplementsAdapter supplementsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_shop);



        db = FirebaseFirestore.getInstance();
        petsRec = findViewById(R.id.pets_rec);
        petItemsRec = findViewById(R.id.petItems_rec);
        supplementsRec = findViewById(R.id.supplements_rec);
        petsShowAll = findViewById(R.id.view_all_pets);
        PetItemsShowAll = findViewById(R.id.view_all_petItems);
        SuplimentsShowAll= findViewById(R.id.view_all_supliments);

        petsShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowAllPets.class);
                startActivity(intent);
            }
        });
        PetItemsShowAll .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowAllItems.class);
                startActivity(intent);
            }
        });

        petsShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowAllPets.class);
                startActivity(intent);
            }
        });

        SuplimentsShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowSup.class);
                startActivity(intent);
            }
        });









        //pets
        petsRec.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
        petsModelList = new ArrayList<>();
        petsAdapters = new petsAdapters(getApplicationContext(),petsModelList);
        petsRec.setAdapter(petsAdapters);

        db.collection("Pets")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PetsModel petsModel = document.toObject(PetsModel.class);
                                petsModelList.add(petsModel);
                                petsAdapters.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("errors", task.getException().getMessage());
                        }


                    }
                });

        //pets items
        petItemsRec.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
        petItemsModelList= new ArrayList<>();
        petsItemsAdapter = new PetsItemsAdapter(getApplicationContext(),petItemsModelList);
        petItemsRec.setAdapter(petsItemsAdapter);

        db.collection("Pets_Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PetItemsModel petItemsModel = document.toObject(PetItemsModel.class);
                                petItemsModelList.add(petItemsModel);
                                petsItemsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("errors", task.getException().getMessage());
                        }


                    }
                });

        //Supplements
        supplementsRec.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
        supplementsModelList = new ArrayList<>();
        supplementsAdapter = new SupplementsAdapter(getApplicationContext(),supplementsModelList);
        supplementsRec.setAdapter(supplementsAdapter);

        db.collection("Supplements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                SupplementsModel supplementsModel= document.toObject(SupplementsModel.class);
                                supplementsModelList.add(supplementsModel);
                                supplementsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("errors", task.getException().getMessage());
                        }


                    }
                });
    }

    public void gotoCart(View view){
        Intent intent = new Intent(petShop.this, Cart.class);
        startActivity(intent);

    }
}