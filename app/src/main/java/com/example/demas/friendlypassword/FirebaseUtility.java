package com.example.demas.friendlypassword;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtility
{
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtility firebaseUtility;
    public static ArrayList<NewAccountsClass> mAcc;
    private FirebaseUtility(){}; // avoid this class being instantiated from outside of the class
    public static void openFbReference(String ref)//generic static method that will open a ref. of the child that is passed as a parameter
    {
        if(firebaseUtility == null)
        {
            firebaseUtility = new FirebaseUtility(); // create a single instance of itself
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mAcc = new ArrayList<NewAccountsClass>();
        }
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }
}
