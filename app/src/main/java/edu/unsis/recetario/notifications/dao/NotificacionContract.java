package edu.unsis.recetario.notifications.dao;

import android.provider.BaseColumns;

/**
 * Created by Meltsan on 01/12/17.
 */
public class NotificacionContract {

    public static abstract class NotificacionEntry implements BaseColumns {

        public static final String TABLE_NAME = "notificaciones";

        public static final String ID_NOTIFICACION = "id_notificacion";
        public static final String ID_MEDICAMENTO = "id_medicamento";
        public static final String FECHA = "fecha";
        public static final String HORA = "hora";
        public static final String SW_TOMADO = "sw_tomado";
        public static final String DESCRIPCION_TOMA = "descripcion_toma";
    }
}
