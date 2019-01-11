package com.example.demas.friendlypassword;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import  com.example.demas.friendlypassword.R.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserInformation extends AppCompatActivity {

    private static final int CHOOSE_PICTURE = 101 ;
    ImageView ProfilePictureImg;
    EditText DisplayName;
    Button SaveInfo;
    Uri uriProfilePicture;
    String ProfilePictureUrl;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        ProfilePictureImg = (ImageView) findViewById(R.id.ProfilePictureImg);
        DisplayName = (EditText) findViewById(R.id.DisplayName);
        SaveInfo = (Button) findViewById(id.SaveInfo) ;
        mAuth = FirebaseAuth.getInstance();
        ProfilePictureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ShowImage();
            }
        });
        SaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SAVE();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CHOOSE_PICTURE && resultCode == RESULT_OK && data != null && data.getData()!= null)
        {
            uriProfilePicture = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfilePicture);
                ProfilePictureImg.setImageBitmap(bitmap);
                uploadPictureToFirebase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void SAVE()
    {
        String NameText = DisplayName.getText().toString().trim();
        if(NameText.isEmpty())
        {
            DisplayName.setError("NAME CANT BE EMPTY !,try again");
            DisplayName.requestFocus();
            return; // stop further execution
        }
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null && uriProfilePicture != null)
        {
            UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(NameText)
                    .setPhotoUri(uriProfilePicture)
                    .build();
            user.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(UserInformation.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void uploadPictureToFirebase()
    {
        StorageReference ProfilePictureReference = FirebaseStorage.getInstance().getReference("profilePictures/" +System.currentTimeMillis() + ".jpg" );
        //here we put our file into this reference
        if(uriProfilePicture != null)
        {
            ProfilePictureReference.putFile(uriProfilePicture).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    ProfilePictureUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    Toast.makeText(UserInformation.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }


    }

    private void ShowImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Picture"),CHOOSE_PICTURE);
    }
}
