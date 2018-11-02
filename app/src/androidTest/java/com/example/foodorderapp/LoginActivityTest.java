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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public IntentsTestRule<LoginActivity> newLoginActivity = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void registerNewUser() {
        onView(withId(R.id.register_button)).perform(click());

        intended(hasExtra(LoginActivity.FROM_HOMEPAGE, LoginActivity.FROM_HOMEPAGE));
    }

    @Test
    public void signIn_success() {
        // Note I know this account was created before and is in the SharedPreferences on the device.
        // If this account has not been created this test will fail.
        onView(withId(R.id.email)).perform(typeText("mike@mike.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("dubby"), closeSoftKeyboard());
        onView(withId(R.id.email_sign_in_button)).perform(click());

        intended(hasExtra(LoginActivity.FROM_HOMEPAGE, LoginActivity.FROM_HOMEPAGE));
    }

    @Test
    public void signIn_failure_accountDoesNotExist() {
        // An account that has not been created before on the device
        onView(withId(R.id.email)).perform(typeText("trtrtrrrr@"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("trtrtrrrtr"), closeSoftKeyboard());
        onView(withId(R.id.email_sign_in_button)).perform(click());

        // Invalid credentials popup shows itself
        onView(withId(R.id.display_text)).check(matches(isDisplayed()));
    }

    @Test
    public void signIn_failure_invalidEmail() {
        onView(withId(R.id.email)).perform(typeText("trtrtrrrr"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("trtrtrrrtr"), closeSoftKeyboard());
        onView(withId(R.id.email_sign_in_button)).perform(click());

        // Invalid credentials popup shows itself
        onView(withId(R.id.email)).check(matches(hasErrorText("This email address is invalid")));
    }
}