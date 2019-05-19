package com.androiddesdecero.expressoprueba;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import androidx.test.espresso.matcher.BoundedMatcher;


public class ErrorTextMatcher {

    /*
    vamos a machear si editTex contiene el campo error.
     */
    @NonNull
    public static Matcher<View> withError(final Matcher<String> stringMatcher) {

        /*
        Espreso nos ofrece BoundedMathcer class para crear nuevos Matcher.
         */
        /*
        BoundedMatcher nos permite crear nuestro custom matcher que cumpla
        con los requistios minbimos de la clase proporcionada
         */
        return new BoundedMatcher<View, EditText>(EditText.class) {

            /*
            Este metodo es usado para describir que matcher vamos a hacer.
            Solo será usado para los Logs. Es decir para Debug.
             */
            @Override
            public void describeTo(Description description) {
                description.appendText("error text: ");
                stringMatcher.describeTo(description);
            }

            /*
            Este métod es usado para impleatar nuestro reglas customizadas
            del macher usando la clase encontrada en la pantalla. En este
            caso será un edit test.
             */
            @Override
            public boolean matchesSafely(EditText editTextw) {
                return stringMatcher.matches(editTextw.getError().toString());
            }
        };
    }
}
