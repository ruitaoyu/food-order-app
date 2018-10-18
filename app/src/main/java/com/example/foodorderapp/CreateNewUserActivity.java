package com.example.foodorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity to create a new user and select between if the account is a driver account or a customer
 * account.
 */
public class CreateNewUserActivity extends AppCompatActivity {

    // no extras out

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }
}
