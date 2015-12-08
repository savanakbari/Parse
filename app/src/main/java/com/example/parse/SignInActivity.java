package com.example.parse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity {
EditText etEmail, etPass;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = (EditText)findViewById(R.id.signInEmail);
        etPass= (EditText) findViewById(R.id.signInPass);
        btLogin = (Button) findViewById(R.id.btnSignIn);


        final String name1  = etEmail.getText().toString();


        final String userName = "Test_user";
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        ParseUser.logInInBackground(etEmail.getText().toString(), etPass.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user!=null){
                    ParseUser users = ParseUser.getCurrentUser();
                    String display = users.toString();
                    Boolean verified = users.getBoolean("emailVerified");
                    if(verified){
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("username",userName);
                        startActivity(intent);
                    }
                    else{
                        Snackbar.make(findViewById(android.R.id.content), " Please verify your Email",Snackbar.LENGTH_SHORT).show();
                    }
                }
                else{
                    Snackbar.make(findViewById(android.R.id.content), parseError(e), Snackbar.LENGTH_SHORT).show();
                }
            }
        });


            }
        });


    }

    private String parseError(ParseException e){
        switch(e.getCode()){
            case ParseException.EMAIL_NOT_FOUND:
                return  "Email Not found";
            case ParseException.CONNECTION_FAILED:
                return "Connection Failed";
            case ParseException.PASSWORD_MISSING:
                return "Password Missing";
            default:
                return "Error/ Try Again";
        }

    }
}
