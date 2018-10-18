package com.example.foodorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * The activity for the login page, this is the page the user sees when they open the app.
 *
 * This will need to decide between customer or driver and go to different activities.
 */
public class LoginActivity extends AppCompatActivity {

    // I don't know how this user stuff works yet, but there will need to be an extra for if it was
    // a user or not (I imagine once a user is authenticated they have a unique identifier that will
    // need to be passed on).
    public static final String EXTRA_USERID = "com.example.foodorderapp.USERID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
