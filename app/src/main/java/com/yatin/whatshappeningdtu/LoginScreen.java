package com.yatin.whatshappeningdtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginScreen extends AppCompatActivity {

    EditText username;
    EditText password;

    public void login(View view){

        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {

                if (parseUser != null) {

                    Toast.makeText(LoginScreen.this,"Login Success",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginScreen.this,EnterCloudEvent.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginScreen.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username = (EditText)findViewById(R.id.usernameEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
    }



    }
