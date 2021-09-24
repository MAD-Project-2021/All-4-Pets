package com.example.All4Pets.Category.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.All4Pets.Category.adapters.PetsItemsAdapter;
import com.example.All4Pets.Category.adapters.ShowAllPetsAdapter;
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

public class ShowAllPets extends AppCompatActivity {

    RecyclerView showPetsRec, showPetItemsRec, showSupplementsRec;
    FirebaseFirestore db;

    //Pets
    List<PetsModel> petsModelList;
    ShowAllPetsAdapter showAllAdapter;

    //Pets Items
    List<PetItemsModel> petItemsModelList;
    PetsItemsAdapter petsItemsAdapter;

    //Supplements
    List<SupplementsModel> supplementsModelList;
    SupplementsAdapter supplementsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        db = FirebaseFirestore.getInstance();
        showPetsRec = findViewById(R.id.show_all_recP);




        //pets
        showPetsRec.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        petsModelList = new ArrayList<>();
        showAllAdapter = new ShowAllPetsAdapter(getApplicationContext(),petsModelList);
        showPetsRec.setAdapter(showAllAdapter);

        db.collection("Pets")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PetsModel petsModel = document.toObject(PetsModel.class);
                                petsModelList.add(petsModel);
                                showAllAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("errors", task.getException().getMessage());
                        }


                    }
                });
    }
}

