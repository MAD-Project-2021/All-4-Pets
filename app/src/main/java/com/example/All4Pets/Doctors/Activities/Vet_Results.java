package com.example.All4Pets.Doctors.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.All4Pets.Doctors.Adapters.MyAdapter;
import com.example.All4Pets.Doctors.models.MainModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class Vet_Results extends AppCompatActivity {


    TextView name,speciality,price,rate;

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<MainModel> mainModels;
    MyAdapter myAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_results);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.rv);


        //popular Doctors

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        mainModels = new ArrayList<>();
        myAdapter = new MyAdapter(getApplicationContext(), mainModels);
        recyclerView.setAdapter(myAdapter);


        //Database connection

        db.collection("Doctors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MainModel mainModel = document.toObject(MainModel.class);
                                mainModel.id = document.getId();
                                mainModels.add(mainModel);
                                myAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }

                });

    }


    public void gotovetshowmore(View view) {
        Intent intent = new Intent(Vet_Results.this, Vet_ShowMore.class);
        startActivity(intent);
    }



    public void gotofavlist(View view) {
        Intent intent = new Intent(Vet_Results.this, Fav_VetList.class);
        startActivity(intent);
    }
}


