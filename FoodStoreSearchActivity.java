package com.example.foodorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Search all registered stores based on food or store name.
 */
public class FoodStoreSearchActivity extends AppCompatActivity {

    // The store the person selected (which will then be passed to RestaurantMenuActivity).
    public static final String EXTRA_STORE_NAME = "com.example.foodorderapp.STORE_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_store_search);
    }
}
