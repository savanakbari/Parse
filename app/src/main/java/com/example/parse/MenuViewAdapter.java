package com.example.parse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by Savan on 11/24/2015.
 */
public class MenuViewAdapter extends ParseQueryAdapter<ParseObject> {

    public  MenuViewAdapter(Context context){

        super(context,new ParseQueryAdapter.QueryFactory<ParseObject>(){
            public ParseQuery create(){
                ParseQuery query = new ParseQuery(OrderSelection.locationId);

                return query;
            }
        });
    }

    @Override
    public View getItemView( ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.menu_list_view, null);
        }

        super.getItemView(object, v, parent);

        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.image);
        ParseFile imageFile = object.getParseFile("Picture");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
            todoImage.setDrawingCacheEnabled(true);
            todoImage.buildDrawingCache(true);
        }



        TextView item_name = (TextView) v.findViewById(R.id.itemName);
        item_name.setText(object.get("Name").toString());


        TextView price_item = (TextView) v.findViewById(R.id.price);
        price_item.setText("Price:" + object.get("Price").toString());
        TextView timetoPrepare = (TextView) v.findViewById(R.id.timeToPrepare);
        timetoPrepare.setText("Wait time:" + object.get("TimeToPrepare").toString());



        return v;
    }



}
