package com.example.rayold.everydayneeds;

import android.support.test.rule.ActivityTestRule;

import com.example.rayold.everydayneeds.activities.Register;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

public class ActivityTest2 {
    @Rule
    public ActivityTestRule<Register> mActivityTestRule = new
            ActivityTestRule<>(Register.class);
    @Test
    public void Register() {
        onView(withId(R.id.etName)).perform(typeText("user"), closeSoftKeyboard());
        onView(withId(R.id.etEmail)).perform(typeText("mgarzon@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText("1990Papii"), closeSoftKeyboard());

        onData(allOf(is(instanceOf(String.class)), is("Fournisseur"))).perform(click());
        onView(withId(R.id.role)).check(matches(withSpinnerText(containsString("Fournisseur"))));


        onView(withId(R.id.bRegister)).perform(click());
        onView(withText("Empty fields")).check(matches(isDisplayed()));
    }

}
