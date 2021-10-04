package com.example.All4Pets.Category.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.All4Pets.Category.adapters.CAddressAdapter;
import com.example.All4Pets.Category.models.CAddressModel;
import com.example.All4Pets.Category.models.PetItemsModel;
import com.example.All4Pets.Category.models.SupplementsModel;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CAddress extends AppCompatActivity implements CAddressAdapter.SelectedAddress {

    Button addAddress,paymentBtn;
    RecyclerView recyclerView;
    private List<CAddressModel> cAddressModelList;
    private CAddressAdapter cAddressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    String mAddress = "";
    int totalPrice = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caddress);


        totalPrice = getIntent().getIntExtra("total", -1);

        //get data
        Object obj = getIntent().getSerializableExtra("item");

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.address);
        addAddress = findViewById(R.id.Add_CAddress);
        paymentBtn = findViewById(R.id.btn_payment);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        cAddressModelList = new ArrayList<>();
//        cAddressAdapter = new CAddressAdapter(getApplicationContext(),cAddressModelList,this);
//        recyclerView.setAdapter(cAddressAdapter);
//
//
//
//
////auth.getCurrentUser().getUid()
//
//        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
//                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//               for (DocumentSnapshot doc : task.getResult().getDocuments()){
//                   CAddressModel cAddressModel = doc.toObject(CAddressModel.class);
//                   cAddressModelList.add(cAddressModel);
//                   cAddressAdapter.notifyDataSetChanged();
//               }
//            }
//        });


        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amount = 0.0;
                if(totalPrice >= 0) {
                    amount = totalPrice;
                } else if (obj instanceof PetItemsModel){
                    PetItemsModel petItemsModel = (PetItemsModel) obj;
                    amount = petItemsModel.getPrice();
                } if (obj instanceof SupplementsModel){
                    SupplementsModel supplementsModel = (SupplementsModel) obj;
                    amount = supplementsModel.getPrice();
                }
                Intent intent = new Intent(CAddress.this,Payment.class);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });


        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CAddress.this,AddAddress.class));


            }
        });
    }

    @Override
    public void setAddress(String address) {
        mAddress = address;

    }


    @Override
    protected void onResume() {
        super.onResume();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cAddressModelList = new ArrayList<>();
        cAddressAdapter = new CAddressAdapter(getApplicationContext(),cAddressModelList,this);
        recyclerView.setAdapter(cAddressAdapter);




//auth.getCurrentUser().getUid()

        firestore.collection("AddCurrentUserAddress").document(auth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                    CAddressModel cAddressModel = doc.toObject(CAddressModel.class);
                    cAddressModelList.add(cAddressModel);
                    cAddressAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}