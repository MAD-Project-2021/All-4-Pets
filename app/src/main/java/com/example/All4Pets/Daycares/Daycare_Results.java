package com.example.All4Pets.Daycares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.All4Pets.Daycares.models.MainModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Daycare_Results extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<MainModel> categoryModelList;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daycare_results);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.rv_daycare_items);

        //popular items
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        categoryModelList = new ArrayList<>();
        myAdapter = new MyAdapter(getApplicationContext(),categoryModelList);
        recyclerView.setAdapter(myAdapter);

        db.collection("Day Cares")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                MainModel mainModel = document.toObject(MainModel.class);
                                categoryModelList.add(mainModel);
                                myAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }

                });


    }

    public void gotofavpage2(View view) {
        Intent intent = new Intent(Daycare_Results.this, Fav_DaycareList.class);
        startActivity(intent);


    }


    public void gotoshowmore(View view) {
        Intent intent = new Intent(Daycare_Results.this, Daycare_ShowMore.class);
        startActivity(intent);
    }

    public void gotofinddaycarepage(View view) {
        Intent intent = new Intent(Daycare_Results.this, Find_Daycare.class);
        startActivity(intent);
    }
}