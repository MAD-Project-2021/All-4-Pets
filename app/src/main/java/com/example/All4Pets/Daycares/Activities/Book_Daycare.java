package com.example.All4Pets.Daycares.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.All4Pets.R;

public class Book_Daycare extends AppCompatActivity {

    private Button btn_bookingconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_daycare);

        btn_bookingconfirm = findViewById(R.id.btn_bookingconfirm);

        btn_bookingconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    private void showDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_custom_dialog);

        ImageView imageView_close = dialog.findViewById(R.id.imageView_close);
        imageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button btn_ok = dialog.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public void gotoconfirmeddaycarepg (View view){
        Intent intent = new Intent(Book_Daycare.this, Confirmed_DaycareBooking.class);
        startActivity(intent);

    }

    public void gotoshowmorepg (View view){
        Intent intent = new Intent(Book_Daycare.this, Daycare_ShowMoreNew.class);
        startActivity(intent);

    }
}