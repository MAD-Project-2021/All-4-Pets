package com.example.All4Pets.Category.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.All4Pets.Category.adapters.ShowAllSupAdepter;
import com.example.All4Pets.Category.models.SupplementsModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowSup extends AppCompatActivity {

    RecyclerView  showSupplementsRec;
    FirebaseFirestore db;

    //Supplements
    List<SupplementsModel> supplementsModelList;
    ShowAllSupAdepter showAllSupAdepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sup);

        db = FirebaseFirestore.getInstance();
        showSupplementsRec = findViewById(R.id.show_all_recS);

        //Supplements
        showSupplementsRec.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        supplementsModelList = new ArrayList<>();
        showAllSupAdepter = new ShowAllSupAdepter(getApplicationContext(),supplementsModelList);
        showSupplementsRec.setAdapter(showAllSupAdepter);

        db.collection("Supplements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                SupplementsModel supplementsModel= document.toObject(SupplementsModel.class);
                                supplementsModelList.add(supplementsModel);
                                showAllSupAdepter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("errors", task.getException().getMessage());
                        }


                    }
                });


    }
}