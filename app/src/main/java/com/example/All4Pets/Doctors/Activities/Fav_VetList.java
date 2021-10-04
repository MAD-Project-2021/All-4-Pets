package com.example.All4Pets.Doctors.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.Doctors.Adapters.FavAdapter;
import com.example.All4Pets.Doctors.models.FavouriteModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Fav_VetList extends AppCompatActivity {


        FirebaseFirestore db;
        RecyclerView recyclerView;
        List<FavouriteModel> favouriteModelList  ;
        FavAdapter favAdapter;



        FirebaseAuth auth;    //authentication

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fav_vet_list);

            db = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();

            recyclerView = findViewById(R.id.fav_rec);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            favouriteModelList = new ArrayList<>();
            favAdapter = new FavAdapter(this, favouriteModelList);
            recyclerView.setAdapter(favAdapter);


            db.collection("AddToFavouriteDoctor").document(auth.getCurrentUser().getUid())
                    .collection("user").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot ds : task.getResult().getDocuments()) {

                            FavouriteModel myfavmodel = ds.toObject(FavouriteModel.class);
                            favouriteModelList.add(myfavmodel);
                            favAdapter.notifyDataSetChanged();
                        }
                        }

                    else {
                        Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }

}
