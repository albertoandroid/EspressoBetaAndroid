package com.androiddesdecero.expressoprueba.utils;

import android.os.Handler;
import android.util.Log;

public class WebServiceMock {
    public void login (String user, String password, final CallBack callBack){
        Log.d("TAG1", "AlbertoLogin");

        new Handler().postDelayed(new Runnable() {
            public void run() {
                boolean check = true;
                if(check){
                    callBack.onSuccess("Usuario Correcto");
                }else{
                    callBack.onFailure("Usuario Incorrecto");
                }
            };
        }, 5000); //Cada 5 segundos, 5000 milisegundos.


    }
}
