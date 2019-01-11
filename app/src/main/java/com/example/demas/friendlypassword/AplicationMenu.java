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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AplicationMenu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplication_menu);
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



}
