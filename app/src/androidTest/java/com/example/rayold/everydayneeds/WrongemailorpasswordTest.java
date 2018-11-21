package com.example.rayold.everydayneeds;

import android.support.test.rule.ActivityTestRule;

import com.example.rayold.everydayneeds.activities.Login;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class WrongemailorpasswordTest {
    @Rule
    public ActivityTestRule<Login> mActivityTestRule = new
            ActivityTestRule<>(Login.class);
    @Test
    public void emailIsInvalid() {

        onView(withId(R.id.etEmailUser)).perform(typeText("wrongemail@hotmail.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("hhh222777...//???"), closeSoftKeyboard());


        onView(withId(R.id.bLogin)).perform(click());
        onView(withText("Invalid Email or password")).check(matches(isDisplayed()));
    }
}
