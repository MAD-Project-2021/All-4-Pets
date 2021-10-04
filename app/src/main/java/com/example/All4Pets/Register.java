package com.example.All4Pets;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.All4Pets.Ratings.View_Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    //variables
    EditText et_fullName , et_Email, et_password, et_phone;
    Button btn_registerBtn;
    TextView createLogin;
    FirebaseAuth fAuth;
    ProgressBar pb_progressBar;
    FirebaseFirestore fStore;//can create collections and documents
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_fullName=findViewById(R.id.fullName);
        et_Email=findViewById(R.id.Email);
        et_password=findViewById(R.id.password);
        et_phone=findViewById(R.id.phone);
        btn_registerBtn=findViewById(R.id.registerBtn);
        createLogin = (TextView) findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        pb_progressBar=findViewById(R.id.progressBar);

        //if user is already logged in send them to login page
        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),View_Profile.class));
            finish();
        }



        btn_registerBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //retrieve values
            String email = et_Email.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            String fullName = et_fullName.getText().toString();
            String phone = et_phone.getText().toString();

            //if user doesn't enter any value in email field the error will be displayed
            if(TextUtils.isEmpty(email)){
                et_Email.setError("Email is required");
                return;
            }

            //if user doesn't enter any value in password field the error will be displayed
            if(TextUtils.isEmpty(password)){
                et_password.setError("Password is required");
                return;
            }

            //password should be at least 6 characters length
            if(password.length()<6){
                et_password.setError("Password must be >=6 characters");
                return;
            }


            //if all the conditions are satisfied progress bar will be visible to the user
            pb_progressBar.setVisibility(View.VISIBLE);





            //register the user
            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this,"User added",Toast.LENGTH_LONG).show();

                        //create collection called 'User' and inside it create documents which are having userID as the identifier
                        //retrieve userID of the currently registered in user
                        userID = fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("users").document(userID);

                        //store data using hashmap
                        Map<String,Object> user = new HashMap<>();
                        user.put("fName",fullName);
                        user.put("email" , email);
                        user.put("phone" , phone);

                        //insert to firestore database
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "onSuccess: user profile is created for" +userID);
                            }
                        });

                        startActivity(new Intent(getApplicationContext(),View_Profile.class));

                    }else{
                        Toast.makeText(Register.this, "You've already registered!", Toast.LENGTH_SHORT).show();
                        pb_progressBar.setVisibility(View.GONE);
                    }
                }
            });

              createLogin.setOnClickListener(new View.OnClickListener(){
                  public void onClick(View v){
                      startActivity(new Intent(getApplicationContext(),Login.class));
                  }
              });


            };
        });

    }

    public void onLoginClick(View v) {

        startActivity(new Intent(this, Login.class));

    }


}