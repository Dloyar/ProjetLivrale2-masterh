package com.example.rayold.everydayneeds;

import android.support.test.rule.ActivityTestRule;

import com.example.rayold.everydayneeds.activities.Register;

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

public class EmptyfieldRegisterTest {
    @Rule
    public ActivityTestRule<Register> mActivityTestRule = new
            ActivityTestRule<>(Register.class);
    @Test
    public void Invalid() {
        onView(withId(R.id.etName)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.etEmail)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText(""), closeSoftKeyboard());


        onView(withId(R.id.bRegister)).perform(click());
        onView(withText("Empty fields")).check(matches(isDisplayed()));
    }
}
