package com.example.demas.friendlypassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

public class AplicationMenu extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private FirebaseUser User;
    private TextView TextViewProfile;
    private Button FriendlyPasswordButton;
    private StorageReference storageReference;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplication_menu);
        firebaseAuth = FirebaseAuth.getInstance();
        User= firebaseAuth.getCurrentUser();
        LogOutClicked();
        FriendlyPasswordClicked();
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

    private void FriendlyPasswordClicked()
    {
        Button FriendlyPasswordButton = (Button) findViewById(R.id.FriendlyPasswordButton);
        FriendlyPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AplicationMenu.this,FriendlyPasswordActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed()
    {

    }
}
