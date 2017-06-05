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


import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    String valueName ;
    String valueSociety;
    String valueDate;
    String valueTime;
    String valueVenue;
    String value;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
   // private ArrayList<String> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_events);
        setTitle("EVENTS");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ArrayList<String> cloudEvents = new ArrayList<>();
        //final CloudArrayAdapter cloudArrayAdapter = new CloudArrayAdapter(getApplicationContext(),R.layout.typeview1,cloudEvents);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, cloudEvents) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
               // text.setText(text2.toString());
                 text.setTextColor(Color.BLACK);
                 text.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
                 text.setTextSize(30);
                //parent.setBackgroundResource(R.drawable.rectangle3);
                //text.setBackgroundResource(R.drawable.border);
                text.setPadding(10,10,10,10);
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

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show();

        }
        final ListView listView = (ListView) findViewById(R.id.cloudListView);
        listView.setAdapter(arrayAdapter);
//////////////////////////////////////////////////////////////////////////////////
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);
///////////////////////////////////////////////////////////////////////////////
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CloudEvents.this,DetailsActivity.class);
                String itemAtPosition = parent.getItemAtPosition(position).toString();
                intent.putExtra("key",itemAtPosition);
                startActivity(intent);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CloudEvents.this,MySchedule.class);
                startActivity(intent);

            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle("Main Menu");
        //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show();
        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                //Closing drawer on item click
                drawerLayout.closeDrawers();

                switch (item.getItemId()){

                    case R.id.navigationItem1:
                        Intent intent = new Intent(CloudEvents.this,DceSpeaksUp.class);
                        startActivity(intent);
                        break;
                    case R.id.navigationItem2:
                        Intent intent1 = new Intent(CloudEvents.this,DTUSA.class);
                        startActivity(intent1);
                        break;
                    case R.id.navigationItem3:
                        Intent intent2 = new Intent(CloudEvents.this, DTUFreshers.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigationItem4:
                        Intent intent6 = new Intent(CloudEvents.this,DTUCC.class);
                        startActivity(intent6);
                        break;
                    case R.id.navigationItem5:
                        Intent intent3 = new Intent(CloudEvents.this,DTUTimes.class);
                        startActivity(intent3);
                        break;
                    case R.id.navigationItem6:
                        Intent intent7 = new Intent(CloudEvents.this,Memes.class);
                        startActivity(intent7);
                        break;
                    case R.id.aboutDeveloper:
                        Intent intent4 = new Intent(CloudEvents.this,AboutDeveloper.class);
                        startActivity(intent4);
                        break;
                    case R.id.aboutApp:
                        Intent intent5 = new Intent(CloudEvents.this,AboutApp.class);
                        startActivity(intent5);
                        break;

                }

                return true;
            }
        });
        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
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

                                Toast.makeText(CloudEvents.this, "Saving...", Toast.LENGTH_SHORT).show();

                                String itemAtPosition = parent.getItemAtPosition(position).toString();
                                ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
                                query.whereEqualTo("Name", itemAtPosition);
                                query.getFirstInBackground(new GetCallback<ParseObject>() {
                                    public void done(ParseObject object, ParseException e) {
                                        if (object == null) {
                                            Toast.makeText(getApplicationContext(),"Save fail",Toast.LENGTH_SHORT).show();
                                        } else {
                                            valueName = String.valueOf(object.get("Name"));
                                            valueDate = String.valueOf(object.get("date"));
                                           valueSociety = String.valueOf(object.get("Society"));
                                           valueTime = String.valueOf(object.get("time"));
                                           valueVenue = String.valueOf(object.get("venue"));
                                           // value = String.valueOf(object.get("Item"));
                                            value ="NAME: "+ valueName + "\n" +"SOCIETY: "+ valueSociety + "\n" +"DATE: "
                                                    + valueDate + "\n" +"TIME: "+ valueTime + "\n" +"VENUE: "+ valueVenue;
                                            Intent intent = new Intent(CloudEvents.this, MySchedule.class);
                                            DbHandler db = new DbHandler(getApplicationContext());
                                            db.store_item(value);
                                            db.close();
                                            startActivity(intent);
                                        }
                                    }
                                });


                            }
                        })
                        .setNegativeButton("No", null)
                        //Developer Feature Only !
                    /* .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String itemAtPosition = parent.getItemAtPosition(position).toString();
                                ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
                                query.whereEqualTo("Name", itemAtPosition);
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