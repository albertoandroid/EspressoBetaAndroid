package com.androiddesdecero.expressoprueba;

import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.openLink;
import static androidx.test.espresso.action.ViewActions.openLinkWithText;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.PositionAssertions.isAbove;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static androidx.test.espresso.assertion.PositionAssertions.isLeftOf;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

/*
Nuestra Clase de Test le debemos indicar con que Runner va a correr
en este caso con AndroidJUnit4, esto nos permite ejecutar clases de
test del estilo de jUnit4 en dispositivos android y en este caso
utilizando el framework de Espresso
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    /**
     * Use {@link ActivityScenarioRule} para crear y lanzar la Activity bajo
     * test y cerrarla una vez completado el Test.
     * This is a replacement for {@link ActivityTestRule}.
     */
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void myFistTest(){
        /*
        ViewMatcher son usados para localizar los elementos dentros
        de una vista.
        Espresso tiene gran cantidad de ViewMatcher, como podemos ver
        en la documentación oficial. Esto nos da una gran
        cantidad de opciones para localizar el elemento de la
        interfaz de usuario del que estamos interesados para
        realizar la prueba.
        Además se pueden combinar más de un ViewMatcher.
         */

        //Localizamos elemento por resource ID
        onView(withId(R.id.mainActivityTv));

        //Localizamos elemento por testo visible.
        onView(withText("Prueba Expresso"));

        //Localizamos por content descripticon
        onView(withContentDescription("description"));

        //Localizamos por hint
        onView(withHint("Esto es un hint"));

        //Como hemos comentado se pueden combinar. Es decir
        //Que si queremos estar seguros de que un elemento
        //esta visible
        //allOf nos permite comibar matcher.
        onView(allOf(withId(R.id.mainActivityTv), isDisplayed()));
        onView(allOf(withId(R.id.mainActivityTv), isCompletelyDisplayed()));

        onView(withText(startsWith("Hola")));

        onView(allOf(instanceOf(TextView.class), withId(R.id.mainActivityTv)));

        //Imagina que tienes un RelativeLayout y dentro de el un LinearLayout que
        //quieres testear
        onView(allOf(withId(R.id.mainActivityTv),withParent(withId(R.id.mainActivityRl))));
        onView(allOf(withId(R.id.mainActivityRl), withChild(withId(R.id.mainActivityTv))));


        /*
        View Action
        Despues de ver los ViewMatcher que nos permiten localizar cualquier elemento dentro de
        la Interfaz de Usuario vamos a ver ahora los View Action que nos permite realizar cualquier
        opción en el elemento seleccionado.
        Es decir, que al final es lo mismo que si hicieramos una prueba manual, ejemplo selecionamos
        el botón que queremos hacer click y luego hacemos click.
        Un test se basa en localizar elemento y hacer acción.
         */

        onView(withId(R.id.mainActivityEt))
                .perform(typeText("30"));

        onView(withId(R.id.mainActivityEt))
                .perform(replaceText("34"));

        onView(withId(R.id.mainActivityEt))
                .perform(clearText());

        onView(withId(R.id.mainActivityBt))
                .perform(click());

        onView(withId(R.id.mainActivityRl))
                .perform(swipeLeft());

        onView(withId(R.id.mainActivityEt))
                .perform(typeText("Prueba"),
                        ViewActions.closeSoftKeyboard());

        onView(withId(R.id.mainActivityTv))
                .perform(openLinkWithText("www.google.es"));


        /*
        View Assertions
        Despues de ver ViewMacher que nos permite seleccionar un elemento y View Actions que nos
        permite realizar una acción sobre ese elmeento por último nos encontramos con los View
        Assertions lo que nos permite es asertar que una condición se ha cumplido despues de realizar
        una acción en un elemento.

        No puedes correr un test en espreso a no ser que le indiques una aserción, es decir
        que tiene que ocurrir y así puede testear si ocurre o no ocurre.
        Las assertions nos aseguran que nuestra aplicación funcionará correctamente a no ser que
        no pase el test que encontes nos indica que no funciona correctamente.
         */

        onView(withId(R.id.mainActivityTv))
                .check(matches(isDisplayed()));

        onView(withId(R.id.mainActivityTv))
                .check(matches(withText("Prueba Espresso")));

        onView(withId(R.id.mainActivityTv))
                .check(matches(withText(R.string.app_name)));

        onView(withId(R.id.mainActivityTv))
                .check(isLeftOf(withId(R.id.mainActivityEt)));

        onView(withId(R.id.mainActivityTv))
                .check( isCompletelyLeftOf(withId(R.id.mainActivityEt)));

        onView(withId(R.id.mainActivityTv))
                .check(doesNotExist());
    }
}