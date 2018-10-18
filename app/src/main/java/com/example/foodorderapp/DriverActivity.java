package com.example.foodorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * The landing page for when a driver logs in where he/she will get orders.
 */
public class DriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
    }
}
