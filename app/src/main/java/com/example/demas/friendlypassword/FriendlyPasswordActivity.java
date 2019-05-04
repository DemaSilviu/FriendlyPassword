package com.example.demas.friendlypassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FriendlyPasswordActivity extends AppCompatActivity {
    Button MyAccountsButton;
    Button AddNewAccountButton;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendly_password);
        MyAcccountsClicked();
        AddNewAccountClicked();
    }
    private void MyAcccountsClicked()
    {
        Button MyAccountsButton = (Button) findViewById(R.id.MyAccountsButton);
        MyAccountsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FriendlyPasswordActivity.this,MyAccounts.class));
            }
        });
    }
    private void AddNewAccountClicked()
    {
        Button AddNewAccountButton = (Button) findViewById(R.id.AddNewAccountButton);
        AddNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FriendlyPasswordActivity.this,NewAccount.class));
            }
        });
    }
}
