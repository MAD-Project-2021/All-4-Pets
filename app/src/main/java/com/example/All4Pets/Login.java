package com.example.All4Pets;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.All4Pets.Ratings.View_Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    TextView createAccount,tv_forgotPassword;
    ImageView viewProfile;
    EditText et_Email, et_password;
    Button btn_loginBtn;
    ProgressBar pb_progressBar;
    FirebaseAuth fAuth;
    //String emailPattern = "\"^[A-Za-z0-9+_.-]+@(.+)$\";";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_Email = findViewById(R.id.Email);
        et_password = findViewById(R.id.password);
        btn_loginBtn = findViewById(R.id.loginBtn);
        fAuth = FirebaseAuth.getInstance();
        createAccount = findViewById(R.id.createText);
        tv_forgotPassword=findViewById(R.id.forgotPassword);
        pb_progressBar = findViewById(R.id.progressBar);


        btn_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_Email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                //if user doesn't enter any value in email field the error will be displayed
                if (TextUtils.isEmpty(email)) {
                    et_Email.setError("Email is required");
                    return;
                }

                //if user doesn't enter any value in password field the error will be displayed
                if (TextUtils.isEmpty(password)) {
                    et_password.setError("Password is required");
                    return;
                }

                //password should be at least 6 characters length
                if (password.length() < 6) {
                    et_password.setError("Password must be >=6 characters");
                    return;
                }


                //if all the conditions are satisfied progress bar will be visible to the user
                pb_progressBar.setVisibility(View.VISIBLE);



                //authenticate the user
                fAuth = FirebaseAuth.getInstance();
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //check whether login is successful or not
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged In Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "You've not previously registered!", Toast.LENGTH_SHORT).show();
                            pb_progressBar.setVisibility(View.GONE);
                        }
                    }
                });


            }


        });


        tv_forgotPassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

               EditText resetMail = new EditText(v.getContext());
               AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
               passwordResetDialog.setTitle("Reset Password ?");
               passwordResetDialog.setMessage("Enter your email to get the reset link");
               passwordResetDialog.setView(resetMail);

               //alert dialog has two options. If user click on no, user will exit from alert dialog and navigated
                //to login screen. If user click on yes, the email will be taken from edit text and a reset mail
                //will be sent.

                passwordResetDialog.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Reset Link Sent To Your Email",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error! Reset Link is NOt Sent"+e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });



                passwordResetDialog.setNegativeButton("No",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close dialog

                    }
                });
                passwordResetDialog.create().show();
            }
        });

    }


    public void onRegisterClick(View v) {

        startActivity(new Intent(this, Register.class));

    }

    public void onviewProfile(View view){
        Intent intent = new Intent(Login.this, View_Profile.class);
        startActivity(intent);
    }


}