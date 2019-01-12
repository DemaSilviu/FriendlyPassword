package com.example.demas.friendlypassword;

import android.app.Application;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private EditText EmailLogin;
    private EditText PasswordLogin;
    private Button Login_LoginButton;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EmailLogin = (EditText) findViewById(R.id.EmailLogin);
        PasswordLogin = (EditText) findViewById(R.id.PasswordLogin);
        LoginClicked();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

    }
    private void LoginClicked()
    {
        Button Login_LoginButton = (Button) findViewById(R.id.Login_LoginButton);
        Login_LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                UserLogin();
            }
        });
    }
    private void UserLogin()
    {
        String email  = EmailLogin.getText().toString().trim();
        String password = PasswordLogin.getText().toString().trim();
        //firebaseAuth.signInWithEmailAndPassword();
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";
        Matcher matcher= Pattern.compile(validemail).matcher(email);
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
        if(password.length()< 6)
        {
            Toast.makeText(this,"Password too short, try again",Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    finish(); // when press back button from phone (fix not to go back to login screen)
                    Intent intent = new Intent(Login.this,AplicationMenu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void onClick(View view) {
        TextView Back_Login = (TextView) findViewById(R.id.BackToMain_Login);
        if(view == Back_Login)
        {
            startActivity(new Intent(Login.this, Main.class));
        }
    }
}
