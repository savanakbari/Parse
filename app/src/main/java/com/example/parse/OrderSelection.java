package com.example.parse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

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

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemName = (String) ((TextView) view.findViewById(R.id.itemName)).getText();
                    Bitmap bitmap= Bitmap.createBitmap((view.findViewById(R.id.image)).getDrawingCache());

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ImageBitmap", bitmap);
                    bundle.putString("itemName", itemName);
                    bundle.putString("Location", OrderSelection.locationId);
                    Intent i = new Intent(getBaseContext(), PlaceOrderAct.class);
                    i.putExtras(bundle);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cart:
                startActivity(new Intent(this, Cart.class));
                return true;
            case R.id.refresh:
                Intent intent1 = getIntent();
                finish();
                startActivity(intent1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
