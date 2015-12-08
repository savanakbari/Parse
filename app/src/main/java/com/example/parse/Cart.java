package com.example.parse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Cart extends AppCompatActivity {

    String order = " ";
    TextView cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart = (TextView) findViewById(R.id.cartTxt);
        Button button = (Button) findViewById(R.id.btn);
        cart.setSingleLine(false);

        Set keys = PlaceOrderAct.map.keySet();

        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            Integer value = PlaceOrderAct.map.get(key);
            cart.append(key + " = " + value + "\n");
            order = order + key + " = " + value + ",";

        }


        ParseUser user = ParseUser.getCurrentUser();
        final String username = user.getUsername();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject orderItem = new ParseObject("Order");
                orderItem.put("Name", username);
                orderItem.put("Orders", order);
                orderItem.saveInBackground();
                PlaceOrderAct.map.clear();
                cart.setText("(Empty Cart)");
                Snackbar.make(getWindow().getDecorView().getRootView(),"Order placed Successfully",Snackbar.LENGTH_LONG).show();

            }
        });



    }

}
