package com.example.All4Pets.Ratings;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.All4Pets.Login;
import com.example.All4Pets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class View_Profile extends AppCompatActivity {
    TextView fullName,email,phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    ImageView profileImage;
    Button changeProfileImage , changeProfilePhoto;

    private Uri imageUri;
    private static final int IMAGE_REQUEST = 2;
    private int requestCode;
    private int resultCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        email = findViewById(R.id.et_email);
        fullName = findViewById(R.id.et_fullname);
        phone = findViewById(R.id.et_phone);
        changeProfileImage= findViewById(R.id.btn_editprofile);//temporary image
        changeProfilePhoto=findViewById(R.id.btn_add);


        //instantiate firebase firestore and fAuth
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                //using document snap shot we can retrieve data from database
                email.setText(documentSnapshot.getString("email"));
                fullName.setText(documentSnapshot.getString("fName"));
                phone.setText(documentSnapshot.getString("phone"));
            }
        });

        changeProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });

        changeProfileImage.setOnClickListener((v)->{

            Intent i = new Intent(v.getContext(),EditProfile.class);
            i.putExtra("email", email.getText().toString());
            i.putExtra("fullName", fullName.getText().toString());
            i.putExtra("phone",phone.getText().toString());
            startActivity(i);

        });

    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("Image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent,IMAGE_REQUEST);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        super.onActivityResult(requestCode,resultCode,data);


        if(requestCode==IMAGE_REQUEST && resultCode ==RESULT_OK){
            imageUri = data.getData();
            uploadImage();
        }

    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("uploading");
        pd.show();

        if(imageUri != null){
            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("uploads").child(System.currentTimeMillis()+"."+ getFileExtension(imageUri));
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();

                            Log.d("DownloadUrl" , url);
                            pd.dismiss();
                            Toast.makeText(View_Profile.this,"Image upload successful",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }
    }


    public void profileBack(View view){
        //log out from application
        FirebaseAuth.getInstance().signOut();

        //once log out they are sent to Login page
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}