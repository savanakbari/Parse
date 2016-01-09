package com.example.parse;

import android.app.Application;
import android.content.Intent;

import com.parse.Parse;
import com.parse.ParseUser;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "LeAZUl1WfJWgfWkaVCv79LqSyEBeXUaFsKi5iQQU", "sr9ReTPzHbi4SVExat2ULEfFhCz02sJxMVB4byMd");

        if(ParseUser.getCurrentUser()!= null){
            Intent intent= new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        }
    }
}
