package com.example.All4Pets.Daycares.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.Daycares.Adapters.FavdAdapter;
import com.example.All4Pets.Daycares.models.FavdModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Fav_DaycareList extends AppCompatActivity {

    RecyclerView recyclerView;
    List<FavdModel> favdModelList;
    FavdAdapter favdAdapter;

    private FirebaseAuth auth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_daycare_list);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.fvlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favdModelList = new ArrayList<>();
        favdAdapter = new FavdAdapter(this, favdModelList);

        recyclerView.setAdapter(favdAdapter);

        db.collection("AddToFavourite").document(auth.getCurrentUser().getUid())
                .collection("user").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot docs : task.getResult().getDocuments()) {

                        FavdModel favdModel = docs.toObject(FavdModel.class);
                        favdModelList.add(favdModel);
                        favdAdapter.notifyDataSetChanged();
                    }
                }

            }
        });


    }






    public void gotodaycareresultback(View view) {
        Intent intent = new Intent(Fav_DaycareList.this, Daycare_Results.class);
        startActivity(intent);

    }


}