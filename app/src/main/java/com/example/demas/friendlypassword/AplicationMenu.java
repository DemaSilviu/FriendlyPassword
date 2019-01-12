package com.example.demas.friendlypassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AplicationMenu extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private FirebaseUser User;
    private TextView TextViewProfile;
    private ImageView ImageViewProfile;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplication_menu);
        firebaseAuth = FirebaseAuth.getInstance();
        User= firebaseAuth.getCurrentUser();
        TextViewProfile = (TextView) findViewById(R.id.textViewName);
        ImageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);
        LoadInfo();
        LogOutClicked();
        EditProfileClicked();
        SitesClicked();
    }
    private void LogOutClicked()
    {
        Button LogOut = (Button) findViewById(R.id.LogOut);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AplicationMenu.this,Main.class));
            }
        });
    }

    private void SitesClicked()
    {
        Button Sites = (Button) findViewById(R.id.Sites);
        Sites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AplicationMenu.this,Sites.class));
            }
        });
    }
    private void EditProfileClicked()
    {
        Button EditProfile = (Button) findViewById(R.id.EditProfile);
        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AplicationMenu.this,UserInformation.class));
            }
        });

    }
    private void LoadInfo()
    {
        if(User != null)
        {
            if (User.getPhotoUrl() != null)
            {
                String Photo = User.getPhotoUrl().toString();
                Glide.with(this)
                        .load(User.getPhotoUrl())
                        .into(ImageViewProfile);
            }
            if (User.getDisplayName() != null)
            {
                String DisplayName = User.getDisplayName();
                TextViewProfile.setText(DisplayName);
            }
        }
        else
        {
            Toast.makeText(this,"firebaseUser is null !",Toast.LENGTH_SHORT).show();
        }


    }

}
