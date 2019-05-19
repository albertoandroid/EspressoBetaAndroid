package com.androiddesdecero.expressoprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {

    /*
    Espresso nos ofrece la posibilidad de testear tambien los tipos
    de vista adapter. Lo que podría ser para ListView o Un AdapterView

    Para tester listas, como por emjemplo las creadas por un adapter
    la vista que queremos testear es posible que no se muestre en pantalla
    por ejemplo en un array, para ello en Espresso nos encontramos el onData
    que a diferencia del el onview este esta especialmente diseñado para
    este tipo de casos.
    Espresso analiza todas las filas de los adaptadores hasta encontrar
    el elmemento que le hemos indicado. 
     */
    private Spinner spinnerActivitySp;
    private TextView spinnerActivityTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        setUpView();

    }

    private void setUpView(){
        spinnerActivityTv = findViewById(R.id.spinnerActivityTv);
        spinnerActivitySp = findViewById(R.id.spinnerActivitySp);
        setUpDataSpinner();


        spinnerActivitySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    spinnerActivityTv.setText((CharSequence) parent.getSelectedItem());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

/*
        spinnerActivitySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerActivityTv.setText((CharSequence) parent.getSelectedItem());
                Log.d("TAG1", "3333 " + parent.getSelectedItem());
                //Log.d("TAG1", parent.getItemAtPosition(position).toString());
                //boolean a = ("Pablo".equals(parent.getItemAtPosition(position).toString()));
                //Log.d("TAG1", "aaa " + a);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/
    }

    private void setUpDataSpinner(){
        List<String> nombres = new ArrayList<String>();
        nombres.add("Alberto");
        nombres.add("Manuel");
        nombres.add("Laura");
        nombres.add("Monica");
        nombres.add("Pablo");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nombres);

        spinnerActivitySp.setAdapter(dataAdapter);
    }
}
