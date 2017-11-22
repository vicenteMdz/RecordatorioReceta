package edu.unsis.recetario.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.unsis.recetario.MainActivity;
import edu.unsis.recetario.registration.Register;

/**
 * Created by Meltsan on 20/11/17.
 * Clase del activity splash
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //registramos el siguiente activity a mostrar
        //validamos si ya fue usado por primera vez en la base de datos para mostrar el siguiente activity
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
}
