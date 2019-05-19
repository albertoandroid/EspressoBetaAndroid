package com.androiddesdecero.expressoprueba;

import com.androiddesdecero.expressoprueba.utils.PersonaAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewActivityTest {

    @Rule
    public ActivityScenarioRule<RecyclerViewActivity> activityRule =
            new ActivityScenarioRule<>(RecyclerViewActivity.class);

    @Test
    public void recyclerViewSimpleTest(){
        // Match the text in an item below the fold and check that it's displayed.
        String itemElementText = "Pablo";
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerViewSimpleTest1(){
        // Match the text in an item below the fold and check that it's displayed.
        onView(withId(R.id.recyclerActivityRv))
                .perform(RecyclerViewActions.scrollToPosition(19));
        onView(withText("Pablo")).check(matches(isDisplayed()));


    }

    @Test
    public void scrollToElement10AndCheckItsTextTest(){
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.recyclerActivityRv))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(18,
                                click()));

        //En la posición 3 si existe en pantalla Alberto
        //pero no si estamos en la 18

        // Match the text in an item below the fold and check that it's displayed.
        String itemElementText = "Alberto";
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }
}