package com.example.foodorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Displays store information like menu. This is gonna be complex, user has to see payment page but
 * store also needs to get the cart contents.
 */
public class RestaurantMenuActivity extends AppCompatActivity {

    // We're going to have to figure out how to actually do this, but we're going to have to pass out
    // the cart of the user to the restaurant.
    public static final String EXTRA_MENU_SELECTION = "com.example.foodorderapp.MENUSELECTION";

    // Pass the price to PaymentActivity for checkout.
    public static final String EXTRA_CART_PRICE = "com.example.foodorderapp.CARTPRICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_page);
    }
}
