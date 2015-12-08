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

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = (EditText) findViewById(R.id.signUpEmail);
        etPassword = (EditText) findViewById(R.id.signUpPass);
        btRegister = (Button) findViewById(R.id.btnRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ParseUser user = new ParseUser();
                user.setUsername(etEmail.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.setEmail(etEmail.getText().toString());


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast.makeText(SignUpActivity.this, parseError(e), Toast.LENGTH_SHORT).show();

                        } else {
                            Intent intent = new Intent(getBaseContext(), SignInActivity.class);
                            //      intent.putExtra("username", userName );
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            Toast.makeText(getBaseContext(), "Verify email before Logging in", Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }
        });
    }
    private String parseError(ParseException e) {
        switch (e.getCode()) {
            case ParseException.PASSWORD_MISSING:
                return "Enter the password";
            case ParseException.USERNAME_TAKEN:
            case ParseException.EMAIL_TAKEN:
                return "Email already in use";
            default:
                return "Error/Try Again";
        }

    }

}
