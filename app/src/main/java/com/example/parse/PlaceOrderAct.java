package com.example.parse;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceOrderAct extends AppCompatActivity {

    ImageView iView ;
    TextView name,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        iView = (ImageView)findViewById(R.id.imgView);
        name = (TextView)findViewById(R.id.itemName);
        location = (TextView)findViewById(R.id.location);

        Bundle bundle = getIntent().getExtras();
        Bitmap b = (Bitmap) bundle.getParcelable("ImageBitmap");
        iView.setImageBitmap(b);

        String location_ = bundle.getString("Location");
        name.setText(location_);

        String itemName_ = bundle.getString("itemName");
        location.setText(itemName_);




    }

}
