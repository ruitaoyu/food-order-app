package com.example.foodorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * The homepage for when a restaurant/food worker logs in.
 */
public class RestaurantHomepageActivity extends AppCompatActivity {

    // no extras out

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
    }
}
