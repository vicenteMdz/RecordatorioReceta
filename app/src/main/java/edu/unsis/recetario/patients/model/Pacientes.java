package edu.unsis.recetario.patients.model;

import android.content.ContentValues;

import edu.unsis.recetario.accounts.dao.CuentaContract;
import edu.unsis.recetario.patients.dao.PacientesContract;

/**
 * Created by Meltsan on 25/11/17.
 */
public class Pacientes {

    private int idPaciente;
    private String nombrePaciente;
    private String primerApellido;
    private String segundoApellido;
    private String edad;
    private String peso;
    private String tipoSangre;

    public int getIdPaciente() { return idPaciente; }

    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
    public String getNombre() {
        return nombrePaciente;
    }

    public void setNombre(String nombre) {
        this.nombrePaciente = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    @Override
    public String toString(){
        return
                "nombre: " + this.nombrePaciente +
                "primerApellido: " + this.primerApellido +
                "segundoApellido: " + this.segundoApellido +
                "edad: " + this.edad +
                "peso: " + this.peso +
                "tipoSangre: " + this.tipoSangre;
    }


    public int toString2() {
        return idPaciente;
    }

    public static final String ID_PACIENTE = "id_paciente";
    public static final String NOMBRE_PACIENTE = "nombre_paciente";
    public static final String PRIMER_APELLIDO = "primer_apellido";
    public static final String SEGUNDO_APELLIDO = "segundo_apellido";
    public static final String EDAD = "edad";
    public static final String PESO = "peso";
    public static final String TIPOSANGRE = "tipo_sangre";

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(PacientesContract.PacientesEntry.NOMBRE_PACIENTE,this.nombrePaciente);
        contentValues.put(PacientesContract.PacientesEntry.PRIMER_APELLIDO,this.primerApellido);
        contentValues.put(PacientesContract.PacientesEntry.SEGUNDO_APELLIDO,this.segundoApellido);
        contentValues.put(PacientesContract.PacientesEntry.EDAD,this.edad);
        contentValues.put(PacientesContract.PacientesEntry.PESO,this.peso);
        contentValues.put(PacientesContract.PacientesEntry.TIPOSANGRE,this.tipoSangre);
        return contentValues;
    }
}
