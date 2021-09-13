package com.example.All4Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Add_Review extends AppCompatActivity {

    private ImageView button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        button1 = (ImageView) findViewById(R.id.btn_back1);

        button1.setOnClickListener(new View.OnClickListener() {
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