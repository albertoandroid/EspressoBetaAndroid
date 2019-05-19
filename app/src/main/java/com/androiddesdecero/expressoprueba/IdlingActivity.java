package com.androiddesdecero.expressoprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androiddesdecero.expressoprueba.utils.CallBack;
import com.androiddesdecero.expressoprueba.utils.EspressoIdlingResource;
import com.androiddesdecero.expressoprueba.utils.WebServiceMock;

public class IdlingActivity extends AppCompatActivity {

    /*
    Un idling resource es un recurso que nos va a permitir
    hacer una operación asincrona cuyos resultados van a
    afetar a nuestra interfaz de usuario y por tanto a nuestros test
    Es decir nos permite testear de manera fiable las operaciones
    asincronas.
    Tipicos casos de uso:
    Una operación larga
    Petición a base de datos.
    Manejar servicios

    Basicamente un idlint Resource lo que nos a permitr es sincronizar
    nuestr test en expreso con nuestras tareas en background

    Para ello tenemos la clase CountingIdlingResource que básicamente
    incrementa y decrementa y así le permite tener un contador
    de tareas asincronas activas.
    Cuando el contador es cero el recurso esta inactivo, y sino es cero
    quiere decir que hay una tarea al menos ejecutanto que cuando finalice
    cambiara la interfaz de usuario y por lo tanto recurso no activo.

     */

    private TextView idlingActivityTv;
    private Button idlingActivityBt;
    private Button idlingActivityBtGoToSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idling);

        setUpView();

    }

    private void setUpView(){
        idlingActivityTv = findViewById(R.id.idlingActivityTv);
        idlingActivityBt = findViewById(R.id.idlingActivityBt);
        idlingActivityBtGoToSpinner = findViewById(R.id.idlingActivityBtGoToSpinner);
        idlingActivityBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos();
            }
        });
        idlingActivityBtGoToSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getApplicationContext(), SpinnerActivity.class));
            }
        });

    }

    private void cargarDatos(){
//Notificamos a espresso que comienza tarea en segundo plano
        Log.d("TAG1", "Alberto");
        EspressoIdlingResource.increment(); // App is busy until further notice
        TareaAsyncTask tareaAsyncTask = new TareaAsyncTask();
        tareaAsyncTask.execute();


        WebServiceMock webServiceMock = new WebServiceMock();
        webServiceMock.login("Alberto", "12345", new CallBack() {
            @Override
            public void onSuccess(String response) {
                Log.d("TAG1", "Albertobbb");

                //if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement(); // Set app as idle.
                //}
                idlingActivityTv.setText("Alberto");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String response) {
                Log.d("TAG1", "Albertoerr");

                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class TareaAsyncTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute(){
            //tvProgreso.setText("0");
        }

        @Override
        protected String doInBackground(Void... voids) {
            for(int i=1; i<=5; i++){
                esperarUnSegundo();
            }
            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values){
        }

        @Override
        protected void onPostExecute(String resultado){
            //Una vez ha finalizado la tarea,
            //marcamos que la app esta lista para el test
            //de espreso decrementando.
            // if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
            //   EspressoIdlingResource.decrement(); // Set app as idle.
            // }
            //Procemos el dato
            //idlingActivityTv.setText(resultado);
        }
    }

    private void esperarUnSegundo(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }

    }
}

