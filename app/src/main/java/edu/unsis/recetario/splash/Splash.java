package edu.unsis.recetario.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import edu.unsis.recetario.MainActivity;
import edu.unsis.recetario.accounts.dao.AccountsDAOImpl;
import edu.unsis.recetario.accounts.model.Cuenta;
import edu.unsis.recetario.home.Home;
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
        AccountsDAOImpl accountscuentaDAO=new AccountsDAOImpl(this);
        try{
            ArrayList<Cuenta> cuentas = accountscuentaDAO.getAllAccounts();
            Log.d("num  cuentas",""+cuentas.size());
            for(Cuenta c:cuentas){
                Log.d("Cuenta",c.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        int checkUserExist = accountscuentaDAO.validateUserInserted();
        if(checkUserExist > 0){ //si ya hay un usario ir directo al home
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
        finish();
    }
}
