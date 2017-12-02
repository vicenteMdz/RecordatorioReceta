package edu.unsis.recetario.patients.dao;

import android.provider.BaseColumns;

/**
 * Created by Meltsan on 01/12/17.
 */
public class PacientesContract {

    public static abstract class PacientesEntry implements BaseColumns {

        public static final String TABLE_NAME = "pacientes";

        public static final String ID_PACIENTE = "id_paciente";
        public static final String NOMBRE_PACIENTE = "nombre_paciente";
        public static final String PRIMER_APELLIDO = "primer_apellido";
        public static final String SEGUNDO_APELLIDO = "segundo_apellido";
        public static final String EDAD = "edad";
        public static final String PESO = "peso";
        public static final String TIPOSANGRE = "tipo_sangre";
    }
}
