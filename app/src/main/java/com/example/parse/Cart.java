package com.example.parse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Cart extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView cart = (TextView) findViewById(R.id.cartTxt);
        cart.setSingleLine(false);

        Set keys = PlaceOrderAct.map.keySet();

        for (Iterator i = keys.iterator(); i.hasNext();)
        {
            String key = (String) i.next();
            Integer value = PlaceOrderAct.map.get(key);
            cart.append(key + " = " + value + "\n" );

        }


    }

}
