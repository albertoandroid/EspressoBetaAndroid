package com.androiddesdecero.expressoprueba;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SpinnerActivityTest {
    @Rule
    public ActivityTestRule<SpinnerActivity> activityRule =
            new ActivityTestRule<>(SpinnerActivity.class);


    @Test
    public void spinnerTest(){

        onView(withId(R.id.spinnerActivitySp))
                .perform(click());

        onData(anything())
                .atPosition(1)
                .perform(click());

        onView(withId(R.id.spinnerActivitySp))
                .check(matches(withSpinnerText(containsString("Manuel"))));


/*
        onData(
               allOf(is(instanceOf(String.class)),
                       is("Laura")))
                .perform(click());

        onView(withId(R.id.spinnerActivityTv))
                .check(matches(withText("LauraN")));



*/}

        @Test
        public void spinnerTest2(){
            onView(withId(R.id.spinnerActivitySp))
                    .perform(click());
            onData(
                    allOf(is(instanceOf(String.class)),
                            is("Laura")))
                    .perform(click());

            onView(withId(R.id.spinnerActivityTv))
                    .check(matches(withText("LauraN")));
        }



}