package com.yatin.whatshappeningdtu;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MySchedule extends AppCompatActivity {

   RelativeLayout listLayout;
    String[] items;
    ScheduleAdapter scheduleAdapter;
     RecyclerView listRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);
        //////////////////////////////////////////////////////////////////////////////////////////////////
        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setTitle("Schedule");
        //Toast.makeText(this,"Long Press on any event to delete",Toast.LENGTH_SHORT).show();
        //listLayout =  (RelativeLayout)findViewById(R.id.listLayout);
        /////////////////////////////////////////////////////////////////////////////////////////////
        DbHandler db = new DbHandler(getApplicationContext());
        items = db.get_item();
        db.close();
        listRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        scheduleAdapter = new ScheduleAdapter(getApplicationContext());
        if (listRecyclerView != null)
            listRecyclerView.setAdapter(scheduleAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());

        listRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getApplicationContext()));
        listRecyclerView.stopScroll();
        // scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        // myArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
//////////////////////////////////////////////////////////////////////////////////
        //  View emptyView = findViewById(R.id.empty_view);
        //   scheduleListView.setEmptyView(emptyView);
/////////////////////////////////////////////////////////////////////////////////

    listRecyclerView.addOnItemTouchListener(new RItemClickListener(getApplicationContext(), listRecyclerView, new RItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

        }

        @Override
        public void onLongItemClick(final View view, final int position) {
            new AlertDialog.Builder(MySchedule.this)
                    .setIcon(R.drawable.trash)
                    .setTitle("Are you sure?")
                    .setMessage("Do you want to delete this note?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String itemAtPosition = items[position];
                            DbHandler db = new DbHandler(getApplicationContext());
                            db.delete_item(itemAtPosition);
                            db.close();
                        scheduleAdapter.notifyItemRemoved(position);
                        scheduleAdapter.notifyItemRangeChanged(position,items.length);
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();


        }
    }));


    }
    @Override
    protected void onResume(){
        listRecyclerView.stopScroll();
        super.onResume();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.myschedule_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.delete:
                DbHandler db = new DbHandler(getApplicationContext());
               for(int i =0;i<items.length;i++){
                   String itemAtPosition = items[i];
                   db.delete_item(itemAtPosition);
                   scheduleAdapter.notifyItemRemoved(i);
                   scheduleAdapter.notifyItemRangeChanged(i,items.length);
               }
                db.close();

                scheduleAdapter.notifyDataSetChanged();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                Toast.makeText(this,"Whole list Deleted",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Intent intent3 = new Intent(MySchedule.this,AboutMySchedule.class);
                startActivity(intent3);
                break;
            case android.R.id.home:
                Intent intent1 = new Intent(MySchedule.this, CloudEvents.class);
                startActivity(intent1);
                break;
        }


        return super.onOptionsItemSelected(item);
    }


}



