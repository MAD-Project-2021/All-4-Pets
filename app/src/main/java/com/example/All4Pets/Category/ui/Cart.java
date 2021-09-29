package com.example.All4Pets.Category.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.All4Pets.Category.adapters.MyCartAdapter;
import com.example.All4Pets.Category.models.MyCartModel;
import com.example.All4Pets.Category.models.PetsModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    int totalmyCartAmount;
    TextView totalCartAmount;
    Button payment;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    RecyclerView cart;
    MyCartAdapter myCartAdapter;
    List<MyCartModel> myCartModelList;

    int totalReciept = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //get Data
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(myMessageReceiver, new IntentFilter("MyTotalAmount"));

        payment = findViewById(R.id.btn_payment);
        totalCartAmount = findViewById(R.id.Reciept);
        cart = findViewById(R.id.cart_rec);
        cart.setLayoutManager(new LinearLayoutManager(this));
        myCartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(this,myCartModelList);
        cart.setAdapter(myCartAdapter);



        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this,CAddress.class);
                intent.putExtra("total",totalReciept);
                startActivity(intent);

            }
        });
        //auth.getCurrentUser().getUid()

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        String documentId = document.getId();

                        MyCartModel myCartModel = document.toObject(MyCartModel.class);

                        myCartModel.setDocumentId(documentId);
                        myCartModelList.add(myCartModel);
                        myCartAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                    Log.d("errors", task.getException().getMessage());
                }
            }
        });

    }
    public BroadcastReceiver myMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            totalReciept = intent.getIntExtra("totalAmount",0);
            totalCartAmount.setText("Total Amount : Rs." +totalReciept+"/=");
        }

    };
}

