package com.example.demas.friendlypassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewAccount extends AppCompatActivity
{

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    EditText NewAccountName;
    EditText NewAccountId;
    EditText NewAccountPassword;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        FirebaseUtility.openFbReference("NewAccountsClass");
        mFirebaseDatabase = FirebaseUtility.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtility.mDatabaseReference;
        NewAccountName=(EditText) findViewById(R.id.NewAccountName);
        NewAccountId = (EditText) findViewById(R.id.NewAccountId);
        NewAccountPassword = (EditText) findViewById(R.id.NewAccountPassword);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.save_menu://save menu was clicked
                saveAccount();
                Toast.makeText(this, "Account Saved", Toast.LENGTH_LONG).show();
                clean();// reset the context of the EditTexts
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) // to have save button as a menu top right corner of app
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }
    private void saveAccount()
    {
        String SaveNewAccountName = NewAccountName.getText().toString();
        String SaveNewAccountId = NewAccountId.getText().toString();
        String SaveNewAccountPassword = NewAccountPassword.getText().toString();
        NewAccountsClass NewAccount = new NewAccountsClass(SaveNewAccountName,SaveNewAccountId,SaveNewAccountPassword,""); // create NewAccount object
        mDatabaseReference.push().setValue(NewAccount);

    }
    private void clean()
    {
        NewAccountName.setText("");
        NewAccountId.setText("");
        NewAccountPassword.setText("");
        NewAccountName.requestFocus();
    }
}
