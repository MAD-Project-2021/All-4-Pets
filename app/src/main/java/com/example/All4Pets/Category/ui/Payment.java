package com.example.All4Pets.Category.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.All4Pets.R;

import org.w3c.dom.Text;

public class Payment extends AppCompatActivity {
    TextView subTotal,shipping,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        double deliverFee = 150;
        double atotal = 0.0;


        double amount = 0.0;
        amount = getIntent().getDoubleExtra("amount",  0.0);
        atotal = amount + deliverFee;

        subTotal = findViewById(R.id.sub_total);
        shipping = findViewById(R.id.shipping);
        total = findViewById(R.id.total_fee);

        subTotal.setText("Rs" +amount);
        shipping.setText("Rs" +deliverFee);
        total.setText("Rs" +atotal);

    }
}