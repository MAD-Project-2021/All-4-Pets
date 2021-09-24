package com.example.All4Pets.Daycares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.All4Pets.R;

public class Daycare_ShowMore extends AppCompatActivity {

    //ImageView img_dc;
    //TextView tv_daycarename, tv_address, tv_intro3, tv_daycareinfo2, tv_details, tv_contact;
    //Button btn_booking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daycare_show_more);

      /*  img_dc = findViewById(R.id.img_dc);
        tv_daycarename  = findViewById(R.id.tv_daycarename);
        tv_address = findViewById(R.id.tv_address);
        tv_intro3 = findViewById(R.id.tv_intro3);
        tv_daycareinfo2 = findViewById(R.id.tv_daycareinfo2);
        tv_details = findViewById(R.id.tv_details);
        tv_contact = findViewById(R.id.tv_contact);
        btn_booking = findViewById(R.id.btn_booking);
*/
    }



    //intent part
    public void redirecttoresults2 (View view){
        Intent intent = new Intent(Daycare_ShowMore.this, Daycare_Results.class);
        startActivity(intent);

    }

    public void gotobookdaycarepg (View view){
        Intent intent = new Intent(Daycare_ShowMore.this, Book_Daycare.class);
        startActivity(intent);

    }

}