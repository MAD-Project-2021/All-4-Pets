package com.example.All4Pets.Ratings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.R;

import java.util.ArrayList;

public class Ratings extends AppCompatActivity {

    //initialize variables
    RecyclerView recyclerView;

    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        //spinner

        Spinner ratingsSpinner = (Spinner) findViewById(R.id.spinner_ratings);

        ArrayAdapter<String> ratingsAdapter = new ArrayAdapter<String>(Ratings.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner));

        ratingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingsSpinner.setAdapter(ratingsAdapter);

        //for buttons we use onClick Listener and for spinners we use onItemSelectedListener()
        ratingsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    startActivity(new Intent(Ratings.this, Add_Review.class));
                }
                else if(i==2){
                        startActivity(new Intent(Ratings.this, View_Feedback.class));
                } else if(i==3){
                        startActivity(new Intent(Ratings.this, FAQ.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //horizontal recycler view

        //assign variables
        recyclerView = findViewById(R.id.recycler_view);

        //create an integer array
        Integer[] gallery ={R.drawable.pvet1,R.drawable.pdaycare1,R.drawable.petitem1,R.drawable.pvet2,R.drawable.pdaycare2,R.drawable.pitem2};

        //create string array
        String[] description={"PetVet1" , "PetDayCare1" , "PetItem1" , "PetVet2" , "PetDayCare2" , "PetItem2" };

        //initialize arrayList
        mainModels = new ArrayList<>();

        for (int i=0; i<gallery.length; i++){
            MainModel model = new MainModel(gallery[i] , description[i]);
            mainModels.add(model);
        }

        //design horizontal layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(
            Ratings.this,LinearLayoutManager.HORIZONTAL,false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //initialize mainadapter
        mainAdapter = new MainAdapter(Ratings.this,mainModels);

        //set MainAdapter to MyAdapter
        recyclerView.setAdapter(mainAdapter);
    }
}