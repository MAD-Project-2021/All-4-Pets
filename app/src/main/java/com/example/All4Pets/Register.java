package com.example.All4Pets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register<createLogin> extends AppCompatActivity {

    TextView createLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        createLogin = (TextView) findViewById(R.id.createText);
        createLogin.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Register.this,Login.class);
            startActivity(intent);

            Toast.makeText(Register.this, "Welcome to All 4 Pets", Toast.LENGTH_LONG).show();
            };
            });

    }


}