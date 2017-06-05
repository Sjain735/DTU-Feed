package com.yatin.whatshappeningdtu;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    String name;
    String society;
    String date;
    String time;
    String venue;
    String description;
    String item;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle("Details");
        Toast.makeText(getApplicationContext(),"Loading...", Toast.LENGTH_SHORT).show();
        final TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        final TextView societyTextView = (TextView) findViewById(R.id.societyTextView);
        final TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        final TextView timeTextView = (TextView) findViewById(R.id.timeTextView);
        final TextView venueTextView = (TextView) findViewById(R.id.venueTextView);
        final TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        SharedPreferences prefs = getSharedPreferences("com.yatin.whatshappeningdtu", MODE_PRIVATE);
        value = getIntent().getStringExtra("key");
        // value = prefs.getString("key", null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        if (isNetworkAvailable()) {   //value = "Dance";
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
            query.whereEqualTo("Name", value);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (object == null) {

                    } else {

                        name = String.valueOf(object.get("Name"));
                        society = String.valueOf(object.get("Society"));
                        date = String.valueOf(object.get("date"));
                        time = String.valueOf(object.get("time"));
                        venue = String.valueOf(object.get("venue"));
                        description = String.valueOf(object.get("description"));
                        item = String.valueOf(object.get("Item"));
                        nameTextView.setText(name);
                        societyTextView.setText(society);
                        dateTextView.setText(date);
                        timeTextView.setText(time);
                        venueTextView.setText(venue);
                        descriptionTextView.setText(description);
                        value = "";
                    }
                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"No Network",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

         value = getIntent().getStringExtra("key");


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Intent intent = new Intent(DetailsActivity.this, CloudEvents.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
