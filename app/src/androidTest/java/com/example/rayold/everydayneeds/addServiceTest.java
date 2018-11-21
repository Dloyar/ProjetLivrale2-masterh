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

public class addServiceTest {
    @Rule
    public ActivityTestRule<admin> mActivityTestRule = new
            ActivityTestRule<>(admin.class);
    @Test
    public void addservice() {


        onView(withId(R.id.editService)).perform(typeText("Demenagement"), closeSoftKeyboard());
        onView(withId(R.id.editHourlyPrice)).perform(typeText("100"), closeSoftKeyboard());


        onView(withId(R.id.buttonAdd)).perform(click());
        onView(withText("Unsuccesfull add")).check(matches(isDisplayed()));
    }
}
