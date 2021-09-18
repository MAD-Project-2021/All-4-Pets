package com.example.All4Pets.Ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.All4Pets.R;

public class View_Feedback extends AppCompatActivity {

    private ImageView button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        button2 = (ImageView) findViewById(R.id.btn_back);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityRatings();
            }
        });

    }

    public void openActivityRatings(){
        Intent intent = new Intent(this , Ratings.class);
        startActivity(intent);
    }


}