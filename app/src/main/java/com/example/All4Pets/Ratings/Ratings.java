package com.example.All4Pets.Ratings;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.All4Pets.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

                //add review
                if(i==1){

                    startActivity(new Intent(Ratings.this, Add_Review.class));

                }

                //view feedback
                else if(i==2){

                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Ratings.this);
                    View view1 = getLayoutInflater().inflate(R.layout.previous_ratings, null);
                    dialogBuilder.setView(view1);

                    final TextView textView1 = (TextView) view1.findViewById(R.id.showRating);
                    final TextView textView2 = (TextView) view1.findViewById(R.id.Comment);
                    Button button = (Button)view1.findViewById(R.id.ok);

                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    final String userid = user.getUid();

                    //view the ratings and feedback added by the user through database
                    //data snapshot get a snap for the  location at relative specified path

                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Ratings").child(userid);
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String rate = snapshot.child("rate").getValue().toString();
                            String comment = snapshot.child("comment").getValue().toString();

                            textView1.setText(rate);
                            textView2.setText(comment);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                }

                //faq page
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


        dialog.show();
    }

}