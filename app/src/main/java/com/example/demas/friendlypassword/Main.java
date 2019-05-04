package com.example.demas.friendlypassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import  com.example.demas.friendlypassword.R.*;


public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        RegisterClicked();
        LoginClicked();
    }
    private void RegisterClicked()
    {
        Button RegisterButton = (Button) findViewById(id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Main.this,Register.class));
            }
        });

    }
    private void LoginClicked()
    {
        Button LoginButton = (Button) findViewById(id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Main.this,Login.class));
            }
        });

    }
    @Override
    public void onBackPressed()
    {

    }
}

