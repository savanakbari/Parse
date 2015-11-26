package com.example.parse;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.List;

public class OrderSelection extends AppCompatActivity {


    private MenuViewAdapter mAdapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_selection);

        //String id1 = getIntent().getStringExtra("id_");
        new  bgFetch().execute();
            }

    private class bgFetch extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... params) {

            mAdapter = new MenuViewAdapter(getBaseContext());
            listView = (ListView)findViewById(R.id.list);

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            listView.setAdapter(mAdapter);
            mAdapter.loadObjects();
        }
    }

}
