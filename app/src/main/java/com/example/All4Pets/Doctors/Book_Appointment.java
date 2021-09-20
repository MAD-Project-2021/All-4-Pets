package com.example.All4Pets.Doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.All4Pets.R;

public class Book_Appointment extends AppCompatActivity {

    private Button btn_confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                showDialog();
            }
        });

    }

    private void showDialog(){
        Dialog dialog = new Dialog(this,R.style.DialogStyle);
        dialog.setContentView(R.layout.confirm_dialog);

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);

        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });




        dialog.show();
    }



    public void gotofindvet (View view){
        Intent intent = new Intent(Book_Appointment.this, Find_Veterinary.class);
        startActivity(intent);

    }
    public void redirecttoshowmore (View view){
        Intent intent = new Intent(Book_Appointment.this, Vet_ShowMore.class);
        startActivity(intent);

    }

}