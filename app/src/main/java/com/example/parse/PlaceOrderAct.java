package com.example.parse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.HashMap;
import java.util.Map;

public class PlaceOrderAct extends AppCompatActivity {

    public static HashMap<String,Integer> map = new HashMap<>();

    ImageView iView ;
    TextView name,location;
    Button btn;
    EditText quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        iView = (ImageView)findViewById(R.id.imgView);
        name = (TextView)findViewById(R.id.itemName);
        location = (TextView)findViewById(R.id.location);
        btn = (Button) findViewById(R.id.btn);
        quantity = (EditText) findViewById(R.id.quantity);


        Bundle bundle = getIntent().getExtras();
        Bitmap b = (Bitmap) bundle.getParcelable("ImageBitmap");
        iView.setImageBitmap(b);

        final String location_ = bundle.getString("Location");
        name.setText(location_);

        final String itemName_ = bundle.getString("itemName");
        location.setText(itemName_);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int itemQuantity = Integer.parseInt(quantity.getText().toString());
                add(itemName_,itemQuantity);
                int size= map.size();
                Snackbar.make(findViewById(android.R.id.content),"Added to Cart : " + itemName_ + " & Quantity :" + itemQuantity  + "  ::: AND  SIZE IS ::: " + size ,Snackbar.LENGTH_LONG).show();
            }
        });



    }
    private  void add(String itemname, Integer quantities){
        map.put(itemname, quantities);

    }


}
