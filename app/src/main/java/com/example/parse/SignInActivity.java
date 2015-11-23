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
        char[] name = name1.toCharArray();
        int index = Arrays.asList(name).indexOf('@');
        final String userName = name1.substring(0,index);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(name1, etPass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e!=null){
                            Toast.makeText(getBaseContext(), "Error",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            //intent.putExtra("username",userName);
                            startActivity(intent);
                        }
                    }
                });


            }
        });


    }

}
