package com.androiddesdecero.expressoprueba;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class Main2ActivityTest {

    @Rule
    public ActivityScenarioRule<Main2Activity> activityRule =
            new ActivityScenarioRule<>(Main2Activity.class);


    /*
    Chequeamos que el texto que queremos aparece realmente en pantalla.
     */
    @Test
    public void isDisplayedOnTheScreenTest() {
        /*
        VieMathcer son usador para localizar los elementos de una vista.

         */
        // withText("Mi primer Espreso test") is a ViewMatcher
        // matches(isDisplayed()) is a ViewAssertion
        onView(withText("Mi primer Espreso test"))
                .check(matches(isDisplayed()));
    }

    /*
    Chequeamos que la vista con ID se muestra en pantalla.
     */
    @Test
    public void isDisplayedOnTheScreenWithIdTest() {
        // withId(R.id.my_view) is a ViewMatcher
        // matches(isDisplayed()) is a ViewAssertion
        onView(withId(R.id.activityMain2TvMiFirstTest))
                .check(matches(isDisplayed()));
    }

    @Test
    public void ensureResetWork(){
        // withId(R.id.my_view) is a ViewMatcher
        // click() is a ViewAction
        onView(withId(R.id.activityMain2BtReset))
                .perform(click());
        // withId(R.id.my_view) is a ViewMatcher
        // matches(isDisplayed()) is a ViewAssertion
        onView(withId(R.id.activityMain2TvMiFirstTest))
                .check(matches(withText("Reset Texto")));
    }

    @Test
    public void ensureChangeEditTextWork(){
        onView(withId(R.id.activityMain2EtMiFirstEditText))
                .perform(typeText("Prueba"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.activityMain2btChange)).perform(click());

        onView(withId(R.id.activityMain2TvMiFirstTest)).check(matches(withText("Prueba")));
    }

    /*
    Da error porque no se muestra en pantalla este TextView con ese texto.
    Imporatante para saber si se nos muestran o no cosas.
     */
    @Test
    public void isDisplayedOnTheScreenErrorTest() {
        onView(withText("No me muestra")).check(matches(isDisplayed()));
    }

}