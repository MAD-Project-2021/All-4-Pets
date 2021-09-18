package com.example.All4Pets.Ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.All4Pets.R;

public class FAQ extends AppCompatActivity implements View.OnClickListener {

    private ImageView button3;

    Button btnQuestion1;
    Button btnQuestion2;
    TextView tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        button3 = (ImageView) findViewById(R.id.btn_back3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRatings();
            }
        });

        btnQuestion1=(Button)findViewById(R.id.btn_question1);
        btnQuestion2=(Button)findViewById(R.id.btn_question2);
        tvAnswer=(TextView) findViewById(R.id.tv_answer);

        //set event listeners
        //can detect when user click on any button
        btnQuestion1.setOnClickListener((View.OnClickListener) this);
        btnQuestion2.setOnClickListener((View.OnClickListener) this);


    }

    public void openActivityRatings(){
        Intent intent = new Intent(this , Ratings.class);
        startActivity(intent);
    }



    //when user click on a button onclick method executed
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_question1){
            tvAnswer.setText("You can reach our customers and check for comments. Further you can go through certifications we have achieved.");
        }

        if(view.getId()==R.id.btn_question2){
            tvAnswer.setText("Yes of course");
        }
    }
}