package com.example.parse;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.List;

public class OrderSelection extends AppCompatActivity {

    ProgressDialog pDialog;
    private MenuViewAdapter mAdapter;
    private ListView listView;
    public static String locationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_selection);

        locationId = getIntent().getStringExtra("id_");
        new bgFetch().execute();

    }

    private class bgFetch extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(OrderSelection.this, "Location Menu", "Loading...", false);

        }

        @Override
        protected Void doInBackground(Void... params) {

            mAdapter = new MenuViewAdapter(getBaseContext());
            listView = (ListView) findViewById(R.id.list);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            listView.setAdapter(mAdapter);
            mAdapter.loadObjects();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    pDialog.dismiss();
                }
            }, 2000);


        }
    }


}
