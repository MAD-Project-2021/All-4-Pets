package com.example.All4Pets.Category.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddress extends AppCompatActivity {

    EditText name,address,city,postCode,phoneNumber;
    Button addAddressbtn;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.ady_name);
        address = findViewById(R.id.ady_address);
        city = findViewById(R.id.ady_city);
        postCode = findViewById(R.id.ady_postalCode);
        phoneNumber = findViewById(R.id.ady_phoneNumber);
        addAddressbtn = findViewById(R.id.add_your_address);


        addAddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = name.getText().toString();
                String userCity = city.getText().toString();
                String userAddress = address.getText().toString();
                String userPostCode = postCode.getText().toString();
                String userPhoneNumber = phoneNumber.getText().toString();

                if(userPhoneNumber.length() != 10) {
                    phoneNumber.setError("Phone number is not valid");
                    return;
                }

                String final_address = "";

                if (!userName.isEmpty()){
                    final_address+=userName + "\n";
                }
                if (!userCity.isEmpty()){
                    final_address+=userCity + "\n";
                }
                if (!userAddress.isEmpty()){
                    final_address+=userAddress + "\n";
                }
                if (!userPostCode.isEmpty()){
                    final_address+=userPostCode + "\n";
                }
                if (!userPhoneNumber.isEmpty()){
                    final_address+=userPhoneNumber + "\n";
                }
                if(!userName.isEmpty() && !userCity.isEmpty() && !userAddress.isEmpty() && !userPostCode.isEmpty() && !userPhoneNumber.isEmpty()){
                    Map<String,String> map = new HashMap<>();
                    map.put("userAddress",final_address);
                    //auth.getCurrentUser().getUid()

                    firestore.collection("AddCurrentUserAddress").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddAddress.this, "Address Added", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }
                    });

                }else{
                    Toast.makeText(AddAddress.this, "Please Fill All Field", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}