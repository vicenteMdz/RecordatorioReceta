package edu.unsis.recetario.treatements.dao;

import android.provider.BaseColumns;

/**
 * Created by Meltsan on 01/12/17.
 */
public class TratamientoContract {

    public static abstract class TratamientoEntry implements BaseColumns {

        public static final String TABLE_NAME = "tratamientos";

        public static final String ID_TRATAMIENTO = "id_tratamiento";
        public static final String ID_PACIENTE = "id_paciente";
        public static final String NOMBRE_TRATAMIENTO = "nombre_tratamiento";
        public static final String DESCRIPCION = "descripcion_tratamiento";
        public static final String FECHA_ALTA = "fecha_alta";
        public static final String SW_ACTIVO = "sw_activo";
        public static final String SW_FINALIZADO = "sw_finalizado";
    }
}
