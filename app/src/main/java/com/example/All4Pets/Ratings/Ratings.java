package com.example.All4Pets.Ratings;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Ratings extends AppCompatActivity {

    //initialize variables
    RecyclerView recyclerView;

    //variables for add_review dialog box
    EditText feedback;
    RatingBar rating;
    Button send , no;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;//can create collections and documents
    String userID;

    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);


        feedback = findViewById(R.id.et_feedback);
        rating = findViewById(R.id.rb_rating);
        send = findViewById(R.id.btn_send);
        no = findViewById(R.id.btn_no);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


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

//                    startActivity(new Intent(Ratings.this, Add_Review.class));
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Ratings.this);

                    View view1 = LayoutInflater.from(Ratings.this).inflate(R.layout.activity_add_review,null);
                    dialogBuilder.setView(view1);


                    final AlertDialog alertDialog = dialogBuilder.create();

                    EditText feedback = view1.findViewById(R.id.et_feedback);
                    Button btnNo = view1.findViewById(R.id.btn_no);
                    Button btnSend = view1.findViewById(R.id.btn_send);

                    btnNo.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    btnSend.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                            Toast.makeText(Ratings.this,"Send feedback" , Toast.LENGTH_LONG).show();
                            alertDialog.dismiss();
                        }
                    });

                    alertDialog.show();

                }

                else if(i==2){

                    startActivity(new Intent(Ratings.this, View_Feedback.class));
                }
                else if(i==3){

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
        Integer[] gallery ={R.drawable.vet_updated,R.drawable.product_updated,R.drawable.pro_updated};

        //create string array
        String[] description={
                "" ,
                "" };

        //initialize arrayList
        mainModels = new ArrayList<>();

        for (int i=0; i<gallery.length; i++){
            MainModel model = new MainModel(gallery[i] );
            mainModels.add(model);
        }

        //design horizontal layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                Ratings.this,LinearLayoutManager.HORIZONTAL,false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //initialize main adapter
        mainAdapter = new MainAdapter(Ratings.this,mainModels);

        //set MainAdapter to MyAdapter
        recyclerView.setAdapter(mainAdapter);
    }


    private void openFeedbackDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_add_review);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        EditText feedback = dialog.findViewById(R.id.et_feedback);
        Button btnNo = dialog.findViewById(R.id.btn_no);
        Button btnSend = dialog.findViewById(R.id.btn_send);

        btnNo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(Ratings.this,"Send feedback" , Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }

}