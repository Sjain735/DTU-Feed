package com.yatin.whatshappeningdtu;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;


public class EnterCloudEvent extends AppCompatActivity {

    EditText nameEditText;
    EditText dateEditText;
    EditText timeEditText;
    EditText descriptionEditText;
    EditText societyEditText;
    EditText venueEditText;
    static String className = "Events";

    public void save(View view) {

        final String name = nameEditText.getText().toString();
        final String society = societyEditText.getText().toString();
        final String date = dateEditText.getText().toString();
        final String time = timeEditText.getText().toString();
        final String venue = venueEditText.getText().toString();
        final String description = descriptionEditText.getText().toString();

    if(name.equals("") || society.equals("") || date.equals("") || time.equals("") || venue.equals("")){

        Toast.makeText(this,"One of the Required Field is Empty",Toast.LENGTH_LONG).show();

    }else {
        String item;

        item ="NAME:"+ name + "\n" +"SOCIETY:"+ society + "\n" +"DATE:"+ date + "\n" +"TIME"+ time + "\n" +"VENUE:"+ venue;



        ParseObject events = new ParseObject(className);
        events.put("Name", name);
        events.put("Society", society);
        events.put("date", date);
        events.put("time", time);
        events.put("venue",venue);
        events.put("description", description);
        events.put("Item",item);
        events.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    Toast.makeText(EnterCloudEvent.this, "Saved", Toast.LENGTH_LONG).show();
                    nameEditText.setText("");
                    societyEditText.setText("");
                    dateEditText.setText("");
                    timeEditText.setText("");
                    venueEditText.setText("");
                    descriptionEditText.setText("");
                } else {
                    Toast.makeText(EnterCloudEvent.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });
        }
    }

        public void enter(View view){
        Intent i = new Intent(EnterCloudEvent.this,CloudEvents.class);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_cloud_event);
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        dateEditText = (EditText)findViewById(R.id.dateEditText);
        timeEditText = (EditText)findViewById(R.id.timeEditText);
        descriptionEditText = (EditText)findViewById(R.id.descriptionEditText);
        societyEditText = (EditText)findViewById(R.id.societyEditText);
        venueEditText = (EditText)findViewById(R.id.venueEditText);


       ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}
