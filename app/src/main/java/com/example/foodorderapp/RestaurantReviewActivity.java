package com.example.foodorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * For the user to provide restaurant/food reviews after they paid (we could possibly even wait
 * until the food is delivered.
 */
public class RestaurantReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_review);
    }
}
