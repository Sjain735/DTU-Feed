package com.yatin.whatshappeningdtu;

import android.app.Application;
import android.util.Log;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by cle99 on 09/02/2017.
 */

public class StartParse extends Application {

    String appID = "96f8b838fc9add906b991e720f1a50257773501a";
    String masterKey = "20833a60725288b489791a51a772694f2ffd09bd";
    String serverURL = "http://ec2-35-166-47-146.us-west-2.compute.amazonaws.com:80/parse/";

    @Override
    public void onCreate() {
        super.onCreate(); //

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(appID)
                .clientKey(masterKey)
                .server(serverURL)
                .build()
        );

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL,true);
    }
}