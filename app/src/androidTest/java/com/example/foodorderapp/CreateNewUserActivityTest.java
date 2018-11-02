package com.example.foodorderapp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class CreateNewUserActivityTest {

    @Rule
    public IntentsTestRule<CreateNewUserActivity> newUserActivity = new IntentsTestRule<>(CreateNewUserActivity.class);

    @Test
    public void createAccount() {
        // Submit account
        onView(withId(R.id.email)).perform(typeText("mike@mike.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("dubby"), closeSoftKeyboard());
        onView(withId(R.id.register_button)).perform(click());

        // Check to see if intent was sent out (success)
        intended(hasExtra(CreateNewUserActivity.EXTRA_ACCOUNT_CREATED, CreateNewUserActivity.EXTRA_ACCOUNT_CREATED));
    }

}