package com.example.foodorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.foodorderapp.NearbyRestaurantsActivity.SELECTED_RESTAURANT;

/**
 * The homepage for when a restaurant/food worker logs in.
 */
public class RestaurantHomepageActivity extends AppCompatActivity {

    private TextView tempDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);

        tempDisplay = findViewById(R.id.temp_rest_homepage_text);

        Intent intent = getIntent();
        String restaurantName = intent.getStringExtra(SELECTED_RESTAURANT);
        tempDisplay.setText("To be implemented,\nhomepage for " + restaurantName);
    }
}
