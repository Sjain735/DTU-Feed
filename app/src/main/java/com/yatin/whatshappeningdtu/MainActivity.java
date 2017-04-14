package com.yatin.whatshappeningdtu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    ImageView shareImage;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void schedule(View view) {
        Intent i = new Intent(MainActivity.this, MySchedule.class);
        startActivity(i);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void cloudEvents(View view) {
        Intent i = new Intent(MainActivity.this, CloudEvents.class);
        startActivity(i);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void share(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    public void test(){
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initializing Toolbar and setting it as the actionbar
         shareImage = (ImageView)findViewById(R.id.shareImage);
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Main Menu");

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
                        Intent intent = new Intent(MainActivity.this,DceSpeaksUp.class);
                        startActivity(intent);
                        break;
                    case R.id.navigationItem2:
                        Intent intent1 = new Intent(MainActivity.this,DTUSA.class);
                        startActivity(intent1);
                        break;
                    case R.id.navigationItem3:
                        Intent intent2 = new Intent(MainActivity.this, DTUFreshers.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigationItem4:
                        Intent intent6 = new Intent(MainActivity.this,DTUCC.class);
                        startActivity(intent6);
                        break;
                    case R.id.navigationItem5:
                        Intent intent3 = new Intent(MainActivity.this,DTUTimes.class);
                        startActivity(intent3);
                        break;
                    case R.id.aboutDeveloper:
                        Intent intent4 = new Intent(MainActivity.this,AboutDeveloper.class);
                        startActivity(intent4);
                        break;
                    case R.id.aboutApp:
                        Intent intent5 = new Intent(MainActivity.this,AboutApp.class);
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

    }


}



