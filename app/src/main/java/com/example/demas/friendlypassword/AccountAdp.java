package com.example.demas.friendlypassword;

import android.accounts.Account;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

// Recycle view contains data so it needs to connect to a data source
//list of Accounts that we will retrieve from firebase
//in order to get data form a source we need an adaptor
// adaptor will send data to the recycle view through an object called view holder
//view holder describes and item of the recycle view
//adaptor should sub-class the viewholder ( they work togheter)
// + layout manager to deciede how the items are displayed in a recycle view
public class AccountAdp extends RecyclerView.Adapter<AccountAdp.AccountViewHld>
{
    ArrayList<NewAccountsClass> Accounts;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;
    //create constructor
    public AccountAdp()
    {
        FirebaseUtility.openFbReference("NewAccountsClass");
        mFirebaseDatabase = FirebaseUtility.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtility.mDatabaseReference;
        Accounts = FirebaseUtility.mAcc; // Populate the recycle view (retrieve them here into Accounts)
        mChildListener = new ChildEventListener()
        {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                NewAccountsClass Acc = dataSnapshot.getValue(NewAccountsClass.class);
                Log.d("Account",Acc.getAppSiteName());
                Acc.setId(dataSnapshot.getKey());//set id to the push id that was generated from firebase(can read later)
                Accounts.add(Acc);
                notifyItemInserted(Accounts.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot)
            {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);
    }
    @NonNull
    @Override
    public AccountViewHld onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        Context context = parent.getContext();
        View ItemView = LayoutInflater.from(context)
                .inflate(R.layout.fp_row,parent,false);
        return new AccountViewHld(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHld AccHolder, int position)
    {
        NewAccountsClass Acc = Accounts.get(position);
        AccHolder.bind(Acc);
    }

    @Override
    public int getItemCount() {
        return Accounts.size();
    }

    public class AccountViewHld extends RecyclerView.ViewHolder // use this class to describe how to bind the data to a single row
    {
        TextView AccountsView;
        public AccountViewHld(@NonNull View itemView)
        {
            super(itemView);
            AccountsView = (TextView) itemView.findViewById(R.id.fpSiteName);
        }
        //binding the data
        public void bind (NewAccountsClass Account)
        {
            AccountsView.setText(Account.getAppSiteName());
        }
    }

}
