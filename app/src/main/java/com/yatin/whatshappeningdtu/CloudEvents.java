package com.yatin.whatshappeningdtu;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class CloudEvents extends AppCompatActivity {

     String name;
     String society;
     String date;
     String time;
     String venue;
     String description;
    String item;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
   // private ArrayList<String> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_events);
        setTitle("Cloud Events");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ArrayList<String> cloudEvents = new ArrayList<>();
        //final CloudArrayAdapter cloudArrayAdapter = new CloudArrayAdapter(getApplicationContext(),R.layout.typeview1,cloudEvents);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_checked, cloudEvents) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.typeview1,parent,false);
               // TextView text = (TextView) view.findViewById(android.R.id.text1);
               // TextView text2 = (TextView)view.findViewById(R.id.textView) ;
               // text.setText(text2.toString());
             // text.setTextColor(Color.WHITE);
              //  text.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
                //text.setTextSize(20);
                //parent.setBackgroundResource(R.drawable.rectangle3);
                //text.setBackgroundResource(R.drawable.border);
                //text.setPadding(10,10,5,10);
                return view;
            }
        };
        if(isNetworkAvailable()) {
            //////////////////////////////////////////////////////////////////////////////////////////////////////
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Events");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {

                    if (e == null && list != null) {
                        for (ParseObject object : list) {

                            name = String.valueOf(object.get("Name"));
                            society = String.valueOf(object.get("Society"));
                            date = String.valueOf(object.get("date"));
                            time = String.valueOf(object.get("time"));
                            venue = String.valueOf(object.get("venue"));
                            description = String.valueOf(object.get("description"));
                            item = String.valueOf(object.get("Item"));
                            cloudEvents.add(name);
                            object.saveInBackground();
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }

                }
            });
            //ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Blogs");
            //  parseQuery.whereEqualTo("url","true");
        //Toast.makeText(this,"Long Press To Save Onto Your Schedule",Toast.LENGTH_SHORT).show();
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
        final ListView listView = (ListView) findViewById(R.id.cloudListView);
        listView.setAdapter(arrayAdapter);
//////////////////////////////////////////////////////////////////////////////////
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
/////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, final long id) {

                new AlertDialog.Builder(CloudEvents.this)
                        .setIcon(R.drawable.save_icon)
                        .setTitle("Save Event")
                        .setMessage("Do you want to save this event into your schedule?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(CloudEvents.this, "Saved", Toast.LENGTH_SHORT).show();

                                String itemAtPosition = parent.getItemAtPosition(position).toString();
                                Intent intent = new Intent(CloudEvents.this, MySchedule.class);
                                DbHandler db = new DbHandler(getApplicationContext());
                                db.store_item(itemAtPosition);
                                db.close();
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", null)
                        //Developer Feature Only !
                    /*  .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String itemAtPosition = parent.getItemAtPosition(position).toString();
                                ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
                                query.whereEqualTo("Item", itemAtPosition);
                                query.findInBackground(new FindCallback<ParseObject>() {
                                    @Override
                                    public void done(List<ParseObject> objects, ParseException e) {
                                        ParseObject.deleteAllInBackground(objects, new DeleteCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                });
                            }
                        })*/
                        .show();

                return true;
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.insert_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.insert:
                Intent intent = new Intent(CloudEvents.this,LoginScreen.class);
                startActivity(intent);
                break;
            case R.id.refresh:
                Intent refresh_intent = getIntent();
                finish();
                startActivity(refresh_intent);
                break;
            case R.id.aboutCloud:
                Intent intent5 = new Intent(CloudEvents.this,AboutCloudEvents.class);
                startActivity(intent5);
                break;
            case android.R.id.home:
                Intent intent6 = new Intent(CloudEvents.this, MainActivity.class);
                startActivity(intent6);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}