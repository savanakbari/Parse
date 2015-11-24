package com.example.parse;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parse.R;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CustomAdapter extends ParseQueryAdapter<ParseObject> {

    public CustomAdapter(Context context) {

        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Location");

                query.whereEqualTo("AlwaysTrue", true);
                return query;
            }
        });
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.list_view_location_mainactivty, null);
        }

        super.getItemView(object, v, parent);


        TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
        timestampView.setText(object.get("LocationName").toString());
        return v;
    }

}
