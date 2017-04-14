package com.yatin.whatshappeningdtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

public class AboutMySchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_my_schedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("About Schedule");
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
                Intent intent = new Intent(AboutMySchedule.this, MySchedule.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
