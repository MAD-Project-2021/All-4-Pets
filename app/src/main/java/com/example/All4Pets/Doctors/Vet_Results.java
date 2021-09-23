package com.example.All4Pets.Doctors;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
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

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<MainModel> categoryModelList;
    MyAdapter myAdapter;
//    DatabaseReference databaseReference,fvrtref,fvrt_listRef;
//    Boolean fvrtChechker = false;
//
//    vet_List vet;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_results);
        //View root = inflater.inflate(R.layout.vet_results,container,false);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.rv);

        //popular items
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        categoryModelList = new ArrayList<>();
        myAdapter = new MyAdapter(getApplicationContext(), categoryModelList);
        recyclerView.setAdapter(myAdapter);

        db.collection("Doctors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MainModel mainModel = document.toObject(MainModel.class);
                                mainModel.id = document.getId();
                                categoryModelList.add(mainModel);
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
}


    // create a toolbar for vet realts page
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search, menu);
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) item.getActionView();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                txtSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                txtSearch(query);
//                return false;
////            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }


//    private void txtSearch(String str){
//       FirebaseRecyclerView<MainModel>option=
//               new FirebaseRecyclerOptions.Builder<MainModel>()
//               .setQuery(FirebaseDatabase.getInstance().getReference().child("Doctors").orderByChild("name").startAt(str).endAt(str+"~"),MainModel.class)
//               .build();
//
//       myAdapter = new MyAdapter(option);
//       myAdapter.startListening();
//       recyclerView.setAdapter(myAdapter);
//    }
//}


