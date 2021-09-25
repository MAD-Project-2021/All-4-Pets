package com.example.All4Pets.Daycares;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.Daycares.models.MainModel;
import com.example.All4Pets.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Daycare_Results extends AppCompatActivity {



    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<MainModel> categoryModelList;
    MyAdapter myAdapter;

    private static final String DAYCARE = "Day Care";
    private static final String TAG = "Daycare_Results";

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
//search

        /*
        EditText et_search=findViewById(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d(TAG, "Search has changed to: " + s.toString());
                Query query;
                if (s.toString().isEmpty()) {
                    query = db.collection(DAYCARE)
                            .orderBy("createdTime", Query.Direction.ASCENDING);
                } else {
                    query = db.collection(DAYCARE)
                            .whereEqualTo("location", s.toString())
                            .orderBy("createdTime", Query.Direction.ASCENDING);
                }
                FirestoreRecyclerOptions<MainModel> options = new FirestoreRecyclerOptions.Builder<MainModel>()
                        .setQuery(query, MainModel.class)
                        .build();

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot doc: task.getResult().getDocuments()) {
                           // categoryModelList.add(doc.)
                        }
                    }
                });

              // myAdapter.updateOptions(options);



            }
        });
*/
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
/*
    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
*/
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