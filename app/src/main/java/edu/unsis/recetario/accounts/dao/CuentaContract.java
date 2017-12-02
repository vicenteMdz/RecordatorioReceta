package edu.unsis.recetario.accounts.dao;

import android.provider.BaseColumns;

/**
 * Created by Meltsan on 01/12/17.
 */
public class CuentaContract {

    public static abstract class CuentaEntry implements BaseColumns {

        public static final String TABLE_NAME = "cuentas";

        public static final String ID_CUENTA = "id_cuenta";
        public static final String EMAIL = "email";
        public static final String ID_PACIENTE = "id_paciente";
        public static final String TIPO_CUENTA = "tipo_cuenta";
        public static final String ID_PACIENTE_PROPIETARIO = "id_paciente_propietario";
        public static final String SW_ACTIVO = "sw_activo";
        public static final String FECHA_ALTA = "fecha_alta";
        public static final String FECHA_ALTA_PREMIUM = "fecha_alta_premium";
    }
}
