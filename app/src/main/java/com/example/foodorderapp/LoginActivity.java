package com.example.foodorderapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // Purely a navigational intent String. This is a quick and dirty way to find out which activity
    // called the the next activity.
    public static final String FROM_HOMEPAGE = "com.example.foodorderapp.FROMHOMEPAGE";

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView mNotificationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mNotificationView = findViewById(R.id.display_text);

        // Show Account Created Message
        Intent intent = getIntent();
        String message = intent.getStringExtra(CreateNewUserActivity.EXTRA_ACCOUNT_CREATED);

        if (message != null) {
            mNotificationView.setVisibility(View.VISIBLE);
            mNotificationView.setText(message);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public void registerNewUser(View view) {
        Intent intent = new Intent(this, CreateNewUserActivity.class);
        intent.putExtra(FROM_HOMEPAGE, FROM_HOMEPAGE);
        startActivity(intent);
    }

    public void signIn(View view) {
        SharedPreferences sharedPref = getSharedPreferences("mySettings", Context.MODE_PRIVATE);
        String password = sharedPref.getString(mEmailView.getText().toString(), null);

        if (!isEmailValid(mEmailView.getText().toString())) {
            // Email is not valid, display error
            mEmailView.setError(getString(R.string.error_invalid_email));
            mEmailView.requestFocus();
        } else if (password != null && password.equals(mPasswordView.getText().toString())) {
            // We have an account with that matching password
            Intent intent = new Intent(this, FoodStoreSearchActivity.class);
            intent.putExtra(FROM_HOMEPAGE, FROM_HOMEPAGE);
            startActivity(intent);
        } else {
            mNotificationView.setVisibility(View.VISIBLE);
            mNotificationView.setText(R.string.invalid_credentials);
            mNotificationView.setBackgroundColor(Color.RED);
        }
    }
}

