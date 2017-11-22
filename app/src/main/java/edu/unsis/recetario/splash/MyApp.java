package edu.unsis.recetario.splash;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by Meltsan on 20/11/17.
 * se ejecuta al abrir la aplicacion y hace un delay de 3 seg. mientras se muestra la ventana splash
 */
public class MyApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        SystemClock.sleep(3000);
    }

}
