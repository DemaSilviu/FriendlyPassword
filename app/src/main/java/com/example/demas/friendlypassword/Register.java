package com.example.demas.friendlypassword;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import  com.example.demas.friendlypassword.R.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText NameRegister;
    private EditText PasswordRegister;
    private EditText PasswordRegisterConfirm;
    private EditText EmailRegister;
    private Button CreateAccountRegister;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        NameRegister = (EditText) findViewById(R.id.NameRegister);
        PasswordRegister = (EditText) findViewById(R.id.PasswordRegister);
        PasswordRegisterConfirm = (EditText) findViewById(R.id.PasswordRegisterConfirm);
        EmailRegister = (EditText) findViewById(id.EmailRegister);
        CreateAccountRegister = (Button) findViewById(R.id.CreateAccountRegister);
        CreateAccountRegister.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    private void registerUser()
    {
        String userid = NameRegister.getText().toString().trim();
        String email  = EmailRegister.getText().toString().trim();
        String password = PasswordRegister.getText().toString().trim();
        String confirmpassword= PasswordRegisterConfirm.getText().toString().trim();
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";
        Matcher matcher= Pattern.compile(validemail).matcher(email);

        if(TextUtils.isEmpty(userid))
        {
            Toast.makeText(this,"Name is empty, try again",Toast.LENGTH_SHORT).show();
            return;
        }
        if(userid.length() <4)
        {
            Toast.makeText(this,"Name is too short, try again",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Email is empty, try again !",Toast.LENGTH_SHORT).show();
            return;
        }


        if (!(matcher.matches()))
        {
            Toast.makeText(getApplicationContext(),"Email invalid , try again",Toast.LENGTH_LONG).show();
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Password is empty, try again",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(confirmpassword))
        {
            Toast.makeText(this,"Confirm Password is empty, try again",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()< 6)
        {
            Toast.makeText(this,"Password too short, try again",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(password.equals(confirmpassword)))
        {
            Toast.makeText(this,"Passwords are not the same, try again",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("The user is now registering...");
        progressDialog.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progressDialog.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000); // close progress bar in 3 seconds

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //successfully registered and logged in !
                    Toast.makeText(Register.this,"Successfully registered !",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));


                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(Register.this, "Email already registered, try another valid email adress", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(Register.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }
    public void onClick(View view) {
        TextView Back_Register = (TextView) findViewById(R.id.BackToMain_Register);
        if(view == Back_Register)
        {
            startActivity(new Intent(Register.this, Main.class));
        }

        if(view == CreateAccountRegister)
        {
            registerUser();
        }
    }
}
