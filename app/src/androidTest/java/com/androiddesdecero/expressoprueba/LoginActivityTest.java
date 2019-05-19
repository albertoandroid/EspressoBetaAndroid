package com.androiddesdecero.expressoprueba;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/*
Ventajas Espresso Test Recorder
        1.-  Nos permite crear Casos de Test de UI con nuestras interacciones reales.
        2.- Podemos capturar los assertiions y las interaciones sin acceder
        a la estructura de la app. Lo que nos ofrece más velocidad en la ejecución
        de los test y optimizar los casos de test.
        3.- Es mucho más rapido que escribir los test a mano.
        4.  Soporta multiples assertions, lo que permite hacer casos de test más fiables.

    Es decir que espresso test recorder nos permite realizar pruebas manuales
    de interfaz de usuario y que estas sean grabadas.
*/
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {


    //@Rule
    //public ActivityScenarioRule<LoginActivity> activityRule =
    //        new ActivityScenarioRule<>(LoginActivity.class);

    @Rule
    public IntentsTestRule<LoginActivity> activityRule =
            new IntentsTestRule<>(LoginActivity.class);


    /*
    Chequeamos que este error no se muestra al principio.
     */
    @Test
    public void errorMessageIsNotShowInitiallyTest(){
        onView(withId(R.id.loginActivityTvError))
                .check(matches(not(isDisplayed())));
    }

    /*
    Chequemos hint
     */
    @Test
    public void hintIsDisplayedInEditTextUserNameTest(){
        onView(withId(R.id.loginActivityEtUserName))
                .check(matches(withHint(R.string.login_activity_username_hint)));
    }

    @Test
    public void hintIsDisplayedInEditTextPasswordTest(){
        onView(withId(R.id.loginActivityEtPassword))
                .check(matches(withHint(R.string.login_activity_password_hint)));
    }

    /*
    Chequeamos si usuario no tiene 4 digitos esta mal y por tando
    debe mostrar error.
     */
    @Test
    public void userNameLenghtRule_ErrorTest(){
        onView(withId(R.id.loginActivityEtUserName))
                .perform(typeText("alb"));
        onView(withId(R.id.loginActivityBtLogin))
                .perform(click());
        onView(withId(R.id.loginActivityTvError))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.login_activity_username_error)));
    }
    /*
    Hacemos los mismo con la contraseña,
    pero nos va a un error.
     */

    @Test
    public void passwordLenghtRule_ErrorTest(){
        onView(withId(R.id.loginActivityEtPassword))
                .perform(typeText("123"));
        onView(withId(R.id.loginActivityBtLogin))
                .perform(click());
        onView(withId(R.id.loginActivityTvError))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.login_activity_password_error)));
    }

    /*
    El ejemplo anterior nos da el error de que el texto no es el correcto.
    En este caso en pantalla aparecera Login ha de ser más de 4 caracteres
    y nosotros lo estamos comparando con Username ha de ser más de 4 caracteres
    la solución es que hemos de poner un userName correcto
     */

    @Test
    public void passwordLenghtRule_ErrorTestOK(){
        onView(withId(R.id.loginActivityEtUserName))
                .perform(typeText("alberto"));
        onView(withId(R.id.loginActivityEtPassword))
                .perform(typeText("123"));
        onView(withId(R.id.loginActivityBtLogin))
                .perform(click());
        onView(withId(R.id.loginActivityTvError))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.login_activity_password_error)));
    }

    @Test
    public void validUserNameandPasswordNotShowErrorMessage(){
        onView(withId(R.id.loginActivityEtUserName))
                .perform(typeText("alberto"));
        onView(withId(R.id.loginActivityEtPassword))
                .perform(typeText("1234"));
        onView(withId(R.id.loginActivityBtLogin))
                .perform(click());
        onView(withId(R.id.loginActivityTvError))
                .check(matches(not(isDisplayed())));
    }

    /*
    Test para cambiar activity con login correcto
     */

    @Test
    public void validUserNameAndPasswordGoToMainActivityTest(){
        onView(withId(R.id.loginActivityEtUserName))
                .perform(typeText("Alberto"));
        onView(withId(R.id.loginActivityEtPassword))
                .perform(typeText("1234"));
        onView(withId(R.id.loginActivityBtLogin))
                .perform(click());
        intended(hasComponent(RecyclerViewActivity.class.getName()));
        intended(hasExtra("USER", "Alberto"));

        intended(allOf(
                hasComponent(RecyclerViewActivity.class.getName()),
                hasExtra("USER", "Alberto"))
        );
    }

}