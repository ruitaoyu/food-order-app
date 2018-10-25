package com.example.foodorderapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Activity to create a new user and select between if the account is a driver account or a customer
 * account.
 */
public class CreateNewUserActivity extends AppCompatActivity {

    public static final String EXTRA_ACCOUNT_CREATED = "com.example.foodorderapp.ACCOUNTCREATED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void createAccount(View view) {
        // Get the text values
        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);

        String newEmail = email.getText().toString();
        String newPassword = password.getText().toString();

        // Create new Key value pair with the username and password
        SharedPreferences sharedPref = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(newEmail, newPassword);
        editor.commit();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(EXTRA_ACCOUNT_CREATED, "Account created!");
        startActivity(intent);
    }
}
