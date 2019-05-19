package com.androiddesdecero.expressoprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
Si el elemento de UI no es traceable tendresmo que hacer nuestro Espresso
view mathcer. Podemos crear nuestro propio custom mathcer para localizar el
elemento.
Ejemplo Toast, popup, o cuando mostramos un error en un edit test.

En nuestro caso de prueba nos vamos a crear un custom matcher que nos
permita determinar cuando un Edit Text muestra un mensaje de error utilizando
su metodo setError.
Esto por defecto no puede ser testeado, pero nosotros realmente queremos testearlo.



Tennemos tres formas para crear un Customer Matcher
1.- usando texto plano
.check(matches(ErrorTextMatchers.withErrorText"Error Nombre"))

2.- Usando id
.check(ViewAssertions.matches(ErrorTextMatchers.withErrorText"R.string.error))

3.- Usando String Matcher
.check(ViewAssertions.matches(ErrorTextMatchers.withErrorText
Matchers.containsString"Error"))

https://github.com/smuldr/espresso-errortext-matcher/tree/master/app/src/androidTest/java/smuldr/espresso/errormatcher


 */

public class CustomMatcherActivity extends AppCompatActivity {

    private EditText customMatcherActivityEtUserName;
    private EditText customMatcherActivityEtPassword;
    private Button customMatcherActivityBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_matcher);
        setUpView();
    }

    private void setUpView(){
        customMatcherActivityEtUserName = findViewById(R.id.customMatcherActivityEtUserName);
        customMatcherActivityEtPassword = findViewById(R.id.customMatcherActivityEtPassword);
        customMatcherActivityBtLogin = findViewById(R.id.customMatcherActivityBtLogin);

        customMatcherActivityBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = customMatcherActivityEtUserName.getText().toString();
                String password = customMatcherActivityEtPassword.getText().toString();

                if(userName!= null && userName.length()< 4){
                    customMatcherActivityEtUserName.setError(getString(R.string.login_activity_username_error));
                    return;
                }
                if(password!= null && password.length()< 4){
                    customMatcherActivityEtPassword.setError(getString(R.string.login_activity_password_error));
                    return;
                }
                doLoginBackEnd(userName, password);
            }
        });
    }

    private void doLoginBackEnd(String userName, String password){
        if(userName.equals("Alberto")&& password.equals("1234")){
            Intent intent = new Intent(getApplicationContext(), RecyclerViewActivity.class);
            //intent.putExtra("USER", userName);
            startActivity(intent);
        }
    }
}
