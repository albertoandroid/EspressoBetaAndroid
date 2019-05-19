package com.androiddesdecero.expressoprueba;

import android.widget.Spinner;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;

import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class CustomMatcherActivityTest {
    @Rule
    public ActivityScenarioRule<CustomMatcherActivity> activityRule =
            new ActivityScenarioRule<>(CustomMatcherActivity.class);

    @Test
    public void checkWithWrongUserTest(){
        onView(withId((R.id.customMatcherActivityEtUserName)))
                .perform(typeText("11"));
        onView(ViewMatchers.withId(R.id.customMatcherActivityBtLogin))
                .perform(click());
        onView(withId((R.id.customMatcherActivityEtUserName)))
                .check(matches(
                        ErrorTextMatcher.withError(
                                Matchers.containsString("Username ha de ser más de 4 caracteres"))));
    }

    @Test
    public void checkWithWrongPasswordTest(){
        onView(withId((R.id.customMatcherActivityEtUserName)))
                .perform(typeText("Alberto"));
        onView(withId((R.id.customMatcherActivityEtPassword)))
                .perform(typeText("12"));
        onView(ViewMatchers.withId(R.id.customMatcherActivityBtLogin))
                .perform(click());
        onView(withId((R.id.customMatcherActivityEtPassword)))
                .check(matches(
                        ErrorTextMatcher.withError(
                                Matchers.containsString("ha de ser"))));
    }

    @Test
    public void validUserNameAndPasswordGoToSpinnerActivityTest(){
        onView(withId(R.id.customMatcherActivityEtUserName))
                .perform(typeText("Alberto"));
        onView(withId(R.id.customMatcherActivityEtPassword))
                .perform(typeText("1234"));
        onView(withId(R.id.customMatcherActivityBtLogin))
                .perform(click());
        intended(hasComponent(RecyclerViewActivity.class.getName()));
        intended(hasExtra("USER", "Alberto"));
    }
}