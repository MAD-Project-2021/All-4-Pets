package com.example.All4Pets.Category.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.All4Pets.Category.adapters.PetsItemsAdapter;
import com.example.All4Pets.Category.adapters.ShowAllItemsAdapter;
import com.example.All4Pets.Category.adapters.ShowAllPetsAdapter;
import com.example.All4Pets.Category.adapters.ShowAllSupAdepter;
import com.example.All4Pets.Category.adapters.SupplementsAdapter;
import com.example.All4Pets.Category.models.PetItemsModel;
import com.example.All4Pets.Category.models.PetsModel;
import com.example.All4Pets.Category.models.SupplementsModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllItems extends AppCompatActivity {

    RecyclerView  showPetItemsRec;
    FirebaseFirestore db;


    //Pets Items
    List<PetItemsModel> petItemsModelList;
    ShowAllItemsAdapter showAllItemsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_items);

        db = FirebaseFirestore.getInstance();
        showPetItemsRec = findViewById(R.id.show_all_recI);



        //pets items
        showPetItemsRec.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        petItemsModelList= new ArrayList<>();
        showAllItemsAdapter = new ShowAllItemsAdapter(getApplicationContext(),petItemsModelList);
        showPetItemsRec.setAdapter(showAllItemsAdapter);

        db.collection("Pets_Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PetItemsModel petItemsModel = document.toObject(PetItemsModel.class);
                                petItemsModelList.add(petItemsModel);
                                showAllItemsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("errors", task.getException().getMessage());
                        }


                    }
                });




    }
}