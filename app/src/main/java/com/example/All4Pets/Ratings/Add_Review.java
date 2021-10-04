package com.example.All4Pets.Ratings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.All4Pets.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Review extends AppCompatActivity {

    TextView showrateing,ratecount,commen;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    String temp;
    String comment;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        ratecount = (TextView)findViewById(R.id.rateCount);
        review = (EditText)findViewById(R.id.review);
        submit = (Button)findViewById(R.id.submit);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        firebaseAuth=FirebaseAuth.getInstance();



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();

                if (rateValue<=1 && rateValue>0){
                    ratecount.setText("Terrible " +rateValue + "/5");
                }else if (rateValue<=2 && rateValue>1){
                    ratecount.setText("Normal " +rateValue + "/5");
                }else if (rateValue<=3 && rateValue>2){
                    ratecount.setText("Good " +rateValue + "/5");
                }else if (rateValue<=4 && rateValue>3){
                    ratecount.setText("Perfect " +rateValue + "/5");
                }else if (rateValue<=5 && rateValue>4){
                    ratecount.setText("Awesome " +rateValue + "/5");
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference("Ratings");

                temp = ratecount.getText().toString();
                comment = review.getText().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                String userid = user.getUid();

                //adding data to real time database
                ModelRatings ratings = new ModelRatings(temp, comment);
                reference.child(userid).setValue(ratings);

                System.out.println(ratings);

                Toast.makeText(Add_Review.this, "Ratings Added!!\nThank you for your feedback. We value it!!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
