package com.example.All4Pets.Daycares.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.Category.ui.DetailedItems;
import com.example.All4Pets.Daycares.Adapters.MyAdapter;
import com.example.All4Pets.Daycares.models.MainModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Daycare_Results extends AppCompatActivity {
/*
    ImageButton btn_favadd;
    TextView name, loaction, price;
    ImageView iv_dayc;
    RatingBar rate;

*/
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
        //FirebaseAuth auth;

        recyclerView = findViewById(R.id.rv_daycare_items);
        //fav btn
       // btn_favadd = findViewById(R.id.btn_favadd);

        //popular items
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        categoryModelList = new ArrayList<>();
        myAdapter = new MyAdapter(getApplicationContext(),categoryModelList);
        recyclerView.setAdapter(myAdapter);

        //fav
  /*     name = findViewById(R.id.tv_dname);
        loaction = findViewById(R.id.tv_location);
        price = findViewById(R.id.tv_price);
        rate = findViewById(R.id.tv_rate);
*/
        //fav
     /*   btn_favadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_favadd();
            }
        });

        btn_favadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/


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
    private void btn_favadd(){
        String saveCurrentTime, saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> favMap = new HashMap<>();

        favMap.put("name",name.getText().toString());
        favMap.put("price",price.getText().toString());
        favMap.put("currentTime",saveCurrentTime);
        favMap.put("currentDate",saveCurrentDate);
        favMap.put("rate",rate.getRating());
       // favMap.put("totalPrice",totalPrice);

//auth.getCurrentUser().getUid()
        db.collection("AddToFavDayCare").document("9XAfykklEvZAMsYDn392rPaqtgv2")
                .collection("User").add(favMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Daycare_Results.this, "Successfully Added to fav list", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

 */

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

/*
    public void gotoshowmore(View view) {
        Intent intent = new Intent(Daycare_Results.this, Daycare_ShowMoreNew.class);
        startActivity(intent);
    }
*/
    public void gotofinddaycarepage(View view) {
        Intent intent = new Intent(Daycare_Results.this, Find_Daycare.class);
        startActivity(intent);
    }

}