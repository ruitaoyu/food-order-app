package com.example.foodorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Search all registered stores based on food or store name.
 */
public class EnterLocationActivity extends AppCompatActivity {

    // The store the person selected (which will then be passed to RestaurantMenuActivity).
    public static final String EXTRA_STORE_NAME = "com.example.foodorderapp.STORE_NAME";

    public static final String ADDRESS = "com.example.foodorderapp.ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_store_search);


        Spinner dropdown = findViewById(R.id.stateSpinner);

        String[] states = new String[]{"AL", "AK", "AS","AZ", "AR", "CA","CO", "CT", "DE", "DC"
                ,"FM", "FL", "GA","GU", "HI", "ID","IL", "IN", "IA", "KS"
                ,"KY", "LA", "ME","MH", "MD", "MA","MI", "MN", "MS", "MO"
                ,"MT", "NE", "NV","NH", "NJ", "NM","NY", "NC", "ND", "MP"
                ,"OH", "OK", "OR","PW", "PA", "PR","RI", "SC", "SD", "TN"
                ,"TX", "UT", "VT","VI", "VA", "WA","WV", "WI", "WY"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_dropdown_item, states);


        dropdown.setAdapter(adapter);
    }

    public void searchAddress(View view) {
        Intent intent = new Intent(this, NearbyRestaurantsActivity.class);
//        intent.putExtra(ADDRESS, ((EditText)findViewById(R.id.addressOneEditText)).getText());
        startActivity(intent);
    }
}
