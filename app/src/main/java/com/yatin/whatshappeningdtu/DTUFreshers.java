package com.yatin.whatshappeningdtu;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DTUFreshers extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtufreshers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (isNetworkAvailable()) {


            setTitle("DTU Freshers 2k16");
            webView = (WebView) findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("https://www.facebook.com/groups/1082263245177758/");
            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    }else{
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Intent intent = new Intent(DTUFreshers.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


