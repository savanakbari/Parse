package com.example.parse;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.*;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ParseQueryAdapter<ParseObject> mainAdapter;
    private CustomAdapter urgentTodosAdapter;
    private ListView listView;
    public static String locationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainAdapter = new ParseQueryAdapter<ParseObject>(this, "Location");
        mainAdapter.setTextKey("LocationName");

        urgentTodosAdapter = new CustomAdapter(this);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(urgentTodosAdapter);
        urgentTodosAdapter.loadObjects();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String str = (String)((TextView) view).getText().toString();
                int length = str.length();
                int indexNumber = str.lastIndexOf('-');
                locationId = str.substring(indexNumber + 1, length);
                Intent i = new Intent(getBaseContext(),OrderSelection.class);
                i.putExtra("id_",locationId);
                startActivity(i);
                Toast.makeText(getBaseContext(),locationId, Toast.LENGTH_SHORT).show();

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_logout:
                ParseUser.logOut();
                this.finish();
                Toast.makeText(getApplicationContext(), "Disconnected...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,StartActivity.class);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;
            case R.id.menu_refresh:

                Intent intent1 = getIntent();
                finish();
                startActivity(intent1);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed(){

        ParseUser.logOut();
        this.finish();
        startActivity(new Intent(this, StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onStart() {
        super.onStart();

    }

}
