package edu.unsis.recetario.registration;

import android.accounts.Account;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import java.text.SimpleDateFormat;
import java.util.Date;

import edu.unsis.recetario.R;
import edu.unsis.recetario.accounts.dao.AccountsDAOImpl;
import edu.unsis.recetario.accounts.model.Cuenta;
import edu.unsis.recetario.home.Home;
import edu.unsis.recetario.patients.dao.PatientsDAO;
import edu.unsis.recetario.patients.dao.PatientsDAOImpl;
import edu.unsis.recetario.patients.model.Pacientes;

public class Register extends AppCompatActivity {


    EditText nombrePaciente;
    EditText primerApellido;
    EditText segundoApellido;
    EditText edad;
    EditText peso;
    EditText tipoSangre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.headerActivityRegister);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        nombrePaciente=(EditText) findViewById(R.id.txtnombre);
        primerApellido=(EditText) findViewById(R.id.txtPrimeApell);
        segundoApellido=(EditText) findViewById(R.id.txtsegundoApll);
        edad=(EditText) findViewById(R.id.txtEdad);
        peso=(EditText) findViewById(R.id.txtPeso);
        tipoSangre=(EditText) findViewById(R.id.txtTipSangre);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.saveUser:
                saveUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveUser(){
        //Intent newAct = new Intent(getApplicationContext(), Home.class);
        //startActivity(newAct);
        validarCampos();
    }

    private void validarCampos(){
        /**comprobar si el objeto para el usuario esta vacio o no */


        //Bandera evidenciar algun error durante la validación de los datos
        boolean cancel = false;
        //Variable para contener el campo a ser enfocado
        View focusView = null;

         String nombre;
         String primerApell;
         String segundoApell;
         String edd;
         String pes;
         String tipoSan;

         nombre=nombrePaciente.getText().toString();
         primerApell=primerApellido.getText().toString();
         segundoApell=segundoApellido.getText().toString();
        edd=edad.getText().toString();
         pes=peso.getText().toString();
         tipoSan=tipoSangre.getText().toString();

        if (TextUtils.isEmpty(nombre)) {
            nombrePaciente.setError(getString(R.string.error_field_required));
            focusView = nombrePaciente;
            cancel = true;
        }if (TextUtils.isEmpty(primerApell)) {
            primerApellido.setError(getString(R.string.error_field_required));
            focusView = primerApellido;
            cancel = true;
        }if (TextUtils.isEmpty(segundoApell)){
            segundoApellido.setError(getString(R.string.error_field_required));
            focusView = segundoApellido;
            cancel = true;
        }if (TextUtils.isEmpty(edd)){
            edad.setError(getString(R.string.error_field_required));
            focusView = edad;
            cancel = true;
        }if (TextUtils.isEmpty(pes)){
            peso.setError(getString(R.string.error_field_required));
            focusView = peso;
            cancel = true;
        }if (TextUtils.isEmpty(tipoSan)){
            tipoSangre.setError(getString(R.string.error_field_required));
            focusView = tipoSangre;
            cancel = true;
        }

        if (cancel) {
            //Enfocar el Campo del Error
            focusView.requestFocus();
        } else {

            //Obteniendo la fecha actual
            Date dNow = new Date();
            SimpleDateFormat diaN = new SimpleDateFormat ("dd/MM/yyyy");
            String date = diaN.format(dNow.getTime());

            Pacientes patient=new Pacientes();
            Cuenta cuenta=new Cuenta();
            patient.setNombre(nombre);
            patient.setPrimerApellido(primerApell);
            patient.setSegundoApellido(segundoApell);
            patient.setEdad(edd);
            patient.setPeso(pes);
            patient.setTipoSangre(tipoSan);

            //Dar de alta la cuenta
            cuenta.setEmail(" ");
            cuenta.setIdPaciente(1);
            cuenta.setTipoCuenta("T");
            cuenta.setIdPacientePropietario(1);
            cuenta.setSwActivo(" ");
            cuenta.setFechaAlta(date);
            cuenta.setFechaAltaPremium("");

            PatientsDAOImpl patientDAO=new PatientsDAOImpl(this);
            AccountsDAOImpl accountscuentaDAO=new AccountsDAOImpl(this);
            try {
                patientDAO.insertPaciente(patient);
                accountscuentaDAO.insertAccounts(cuenta);
            } catch (Exception e) {
                Log.d(e.getLocalizedMessage(),"");
            }


           try {
                patientDAO.getPacienteById(1);
               accountscuentaDAO.getAccountsById(1);
                //Log.d("paciente",patientDAO.getPacienteById(1).toString());
               Log.d("Cuenta",accountscuentaDAO.getAccountsById(1).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent newAct = new Intent(getApplicationContext(), Home.class);
            startActivity(newAct);

        }
    }
}
