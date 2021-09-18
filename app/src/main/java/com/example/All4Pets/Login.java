package com.example.All4Pets;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.All4Pets.Ratings.View_Profile;

/*import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;*/

public class Login extends AppCompatActivity {

    TextView createAccount;
    private ImageView viewprofile;
    EditText Email,password;
    Button loginBtn;
    String emailPattern = "\"^[A-Za-z0-9+_.-]+@(.+)$\";";
    ProgressDialog progressDialog;
    //ProgressDialog progressDialog = new ProgressDialog(this);

   /* FirebaseAuth mAuth;
    FirebaseUser mUser;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewprofile = (ImageView) findViewById(R.id.btn_gotoprofile);

        viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityView_Profile();
            }
        });

        createAccount=(TextView) findViewById(R.id.createText);

        Email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        loginBtn=findViewById(R.id.loginBtn);
        progressDialog = new ProgressDialog( this);
        /*FirebaseApp.initializeApp(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();*/

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToNextActivity();


            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String email = Email.getText().toString();
        String pass_word = password.getText().toString();

        if (email.matches(emailPattern)) {
            Email.setError("Enter correct email");
        } else if (pass_word.isEmpty() || password.length() < 6) {
            password.setError("Enter proper password");
        }else{
            progressDialog.setMessage("Login on progress...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            /*mAuth.createUserWithEmailAndPassword(email, pass_word).addOnCompleteListener(new OnCompleteListener<AuthResult>(

            ) */{
               /* @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        //sendUserToNextActivity();
                        Toast.makeText(Login.this,"Login successful",Toast.LENGTH_LONG).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Login.this,""+task.getException(),Toast.LENGTH_LONG).show();
                    }
                }*/


            };
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(Login.this,Register.class);
        startActivity(intent);
    }

    public void openActivityView_Profile(){
                Intent intent = new Intent(this , View_Profile.class);
                startActivity(intent);
            }



}