package com.example.parse;

import android.app.Application;

import com.parse.Parse;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "LeAZUl1WfJWgfWkaVCv79LqSyEBeXUaFsKi5iQQU", "sr9ReTPzHbi4SVExat2ULEfFhCz02sJxMVB4byMd");
    }
}
