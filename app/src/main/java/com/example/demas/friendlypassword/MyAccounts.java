package com.example.demas.friendlypassword;

import android.accounts.Account;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAccounts extends AppCompatActivity
{
    ArrayList<NewAccountsClass> NewAccounts;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accounts);
        RecyclerView AccountsView = (RecyclerView) findViewById(R.id.AccountsView);
        final AccountAdp AccountAdapter = new AccountAdp();
        AccountsView.setAdapter(AccountAdapter);
        LinearLayoutManager AccLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        AccountsView.setLayoutManager(AccLayoutManager);
    }
}
