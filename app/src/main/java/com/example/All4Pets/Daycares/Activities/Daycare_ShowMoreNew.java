package com.example.All4Pets.Daycares.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.All4Pets.Daycares.models.MainModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

//import com.example.All4Pets.Daycares.models.ModelNew;

public class Daycare_ShowMoreNew extends AppCompatActivity {

    ImageView dimg;
    TextView dname,  dlocation, moreInfo, ddescription, contactdetails,dcontact,call;
    Button dbooking;
    RatingBar drate;
    ImageView addfav2;

    MainModel mainModel=null;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daycare_show_more_new);

        addfav2 = findViewById(R.id.addfav2);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        final Object object = getIntent().getSerializableExtra("daycare_show_more_new");

        if(object instanceof MainModel){
            mainModel = (MainModel) object;
        }

        dimg = findViewById(R.id.dimg);
        dname = findViewById(R.id.dname);
        dlocation = findViewById(R.id.dlocation);
        //moreInfo = findViewById(R.id.moreInfo);
        ddescription = findViewById(R.id.ddescription);
        //contactdetails = findViewById(R.id.contactdetails);
        dcontact = findViewById(R.id.dcontact);
        //call = findViewById(R.id.call);
        dbooking = findViewById(R.id.dbooking);
        drate = findViewById(R.id.drate);


        if (mainModel!=null){
            Glide.with(getApplicationContext()).load(mainModel.getImg_url()).into(dimg);
            dname.setText(mainModel.getName());
            dlocation.setText(mainModel.getLocation());
            ddescription.setText(mainModel.getDescription());
            dcontact.setText(mainModel.getContact());
            //call.setText(mainModel.getName());
            //dbooking.setText(mainModel.get());
            drate.setRating(mainModel.getRate());



        }

        addfav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addfav2();
            }
        });

    }


    private void addfav2() {

            String saveCurrentTime,saveCurrentDate;
            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
            saveCurrentDate = currentDate.format(calForDate.getTime());
            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime = currentTime.format(calForDate.getTime());
            final HashMap<String,Object> favdMap = new HashMap<>();
                 favdMap.put("DaycareName",dname.getText().toString());
                 favdMap.put("DaycareLocation",dlocation.getText().toString());
                 favdMap.put("DaycareContact",dcontact.getText().toString());
                 //favdMap.put("Doctor rate",rate.getText().toString());
                 favdMap.put("CurrentTime",saveCurrentTime);
                 favdMap.put("CurrentDate",saveCurrentDate);

            db.collection("AddToFavourite").document(auth.getCurrentUser().getUid())
                    .collection("user").add(favdMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(Daycare_ShowMoreNew.this, "Added To Favourite list", Toast.LENGTH_SHORT).show();
                    finish();
                }


            });





        }

    public void gotoform(View view) {
        Intent intent = new Intent(Daycare_ShowMoreNew.this, Daycare_Booking_Form.class);
        startActivity(intent);

    }

}