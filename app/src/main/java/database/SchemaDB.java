package database;

import android.util.Log;

import edu.unsis.recetario.accounts.dao.CuentaContract;
import edu.unsis.recetario.medicines.dao.MedicamentoContract;
import edu.unsis.recetario.notifications.dao.NotificacionContract;
import edu.unsis.recetario.patients.dao.PacientesContract;
import edu.unsis.recetario.treatements.dao.TratamientoContract;

/**
 * Created by Meltsan on 01/12/17.
 */
public class SchemaDB {

    public static String getQryCreateTablePacientes(){
        String qryCreateTablePacientes = "";

        //sentencia sql para crear la tabla pacientes
        qryCreateTablePacientes = "CREATE TABLE " + PacientesContract.PacientesEntry.TABLE_NAME + " (" +
                   //PacientesContract.PacientesEntry._ID + " INTEGER NOT NULL PRIMARY KEY, "+
                  PacientesContract.PacientesEntry.ID_PACIENTE + " INTEGER NOT NULL PRIMARY KEY, "+
                    PacientesContract.PacientesEntry.NOMBRE_PACIENTE + " TEXT NOT NULL, "+
                    PacientesContract.PacientesEntry.PRIMER_APELLIDO + "  TEXT NOT NULL, "+
                    PacientesContract.PacientesEntry.SEGUNDO_APELLIDO + "  TEXT NOT NULL, "+
                    PacientesContract.PacientesEntry.EDAD + "  INTEGER, "+
                    PacientesContract.PacientesEntry.PESO + " INTEGER, "+
                    PacientesContract.PacientesEntry.TIPOSANGRE + " TEXT, "+
                    "UNIQUE (" + PacientesContract.PacientesEntry.ID_PACIENTE + "));";
        return qryCreateTablePacientes;
    }




    public static String getQryCreateTableTratamientos(){
        String qryCreateTablePacientes = "";

        //sentencia sql para crear la tabla tratamientos
        qryCreateTablePacientes = "CREATE TABLE " + TratamientoContract.TratamientoEntry.TABLE_NAME + " (" +
                //TratamientoContract.TratamientoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TratamientoContract.TratamientoEntry.ID_TRATAMIENTO + " INTEGER NOT NULL PRIMARY KEY, "+
                TratamientoContract.TratamientoEntry.ID_PACIENTE + " INTEGER NOT NULL, "+
                TratamientoContract.TratamientoEntry.NOMBRE_TRATAMIENTO + "  TEXT NOT NULL, "+
                TratamientoContract.TratamientoEntry.DESCRIPCION + "  TEXT, "+
                TratamientoContract.TratamientoEntry.FECHA_ALTA + "  TEXT NOT NULL, "+
                TratamientoContract.TratamientoEntry.SW_ACTIVO + " CHAR NOT NULL, "+
                TratamientoContract.TratamientoEntry.SW_FINALIZADO + " CHAR NOT NULL, "+
                "UNIQUE (" + TratamientoContract.TratamientoEntry.ID_TRATAMIENTO + ")" +
                "FOREIGN KEY (" + TratamientoContract.TratamientoEntry.ID_PACIENTE + ") " +
                "REFERENCES " + PacientesContract.PacientesEntry.TABLE_NAME + "(" + PacientesContract.PacientesEntry.ID_PACIENTE + "))";

        return qryCreateTablePacientes;
    }

    public static String getQryCreateTableMedicamentos(){
        String qryCreateTableMedicamentos = "";

        //sentencia sql para crear la tabla medicamentos
        qryCreateTableMedicamentos = "CREATE TABLE " + MedicamentoContract.MedicamentoEntry.TABLE_NAME + " (" +
                MedicamentoContract.MedicamentoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO + " INTEGER NOT NULL AUTOINCREMENT, "+
                MedicamentoContract.MedicamentoEntry.IDTRATAMIENTO + " INTEGER NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.NOMBRE + "  TEXT NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.DESCRIPCION + "  TEXT, "+
                MedicamentoContract.MedicamentoEntry.NUMERODOSIS + "  REAL NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.TIPODOSIS + " TEXT NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.PERIODOTOMA + " NUMERIC NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.DURACIONTOMA + " NUMERIC NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.TIPODURACION + " CHAR NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.TIPOPERIODOTOMA + " CHAR NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.FECHAINICIO + "  TEXT NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.HORAINICIO + "  TEXT NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.SWACTIVO + "  TEXT NOT NULL, "+
                MedicamentoContract.MedicamentoEntry.SWFINALIZADO + " CHAR NOT NULL, "+
                "UNIQUE (" + MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO + ")" +
                "FOREIGN KEY (" + MedicamentoContract.MedicamentoEntry.IDTRATAMIENTO + ") " +
                "REFERENCES " + TratamientoContract.TratamientoEntry.TABLE_NAME + "(" + TratamientoContract.TratamientoEntry.ID_TRATAMIENTO + "))";

        return qryCreateTableMedicamentos;
    }

    public static String getQryCreateTableNotificaciones(){
        String qryCreateTableNotificaciones = "";

        //sentencia sql para crear la tabla notificaciones
        qryCreateTableNotificaciones = "CREATE TABLE " + NotificacionContract.NotificacionEntry.TABLE_NAME + " (" +
                NotificacionContract.NotificacionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NotificacionContract.NotificacionEntry.ID_NOTIFICACION + " INTEGER NOT NULL AUTOINCREMENT, "+
                NotificacionContract.NotificacionEntry.ID_MEDICAMENTO + " INTEGER NOT NULL, "+
                NotificacionContract.NotificacionEntry.FECHA + "  TEXT NOT NULL, "+
                NotificacionContract.NotificacionEntry.HORA + "  TEXT NOT NULL, "+
                NotificacionContract.NotificacionEntry.DESCRIPCION_TOMA + "  TEXT , "+
                NotificacionContract.NotificacionEntry.SW_TOMADO + "  CHAR NOT NULL, "+
                NotificacionContract.NotificacionEntry.INTENT_ID + "  TEXT, "+
                "UNIQUE (" + NotificacionContract.NotificacionEntry.ID_NOTIFICACION + ")" +
                "FOREIGN KEY (" + NotificacionContract.NotificacionEntry.ID_MEDICAMENTO + ") " +
                "REFERENCES " + MedicamentoContract.MedicamentoEntry.TABLE_NAME + "(" + MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO + "))";

        return qryCreateTableNotificaciones;
    }

    public static String getQryCreateTableAccounts(){
        String qryCreateTableAccounts = "";

        //sentencia sql para crear la tabla cuentas
        qryCreateTableAccounts = "CREATE TABLE " + CuentaContract.CuentaEntry.TABLE_NAME + " (" +
               // CuentaContract.CuentaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CuentaContract.CuentaEntry.ID_CUENTA + " INTEGER NOT NULL  PRIMARY KEY, "+
                CuentaContract.CuentaEntry.EMAIL + " TEXT , "+
                CuentaContract.CuentaEntry.ID_PACIENTE + "  INTEGER NOT NULL, "+
                CuentaContract.CuentaEntry.TIPO_CUENTA + "  TEXT NOT NULL, "+
                CuentaContract.CuentaEntry.ID_PACIENTE_PROPIETARIO + "  INTEGER , "+
                CuentaContract.CuentaEntry.SW_ACTIVO + "  TEXT , "+
                CuentaContract.CuentaEntry.FECHA_ALTA + "  TEXT NOT NULL, "+
                CuentaContract.CuentaEntry.FECHA_ALTA_PREMIUM + "  TEXT , "+
                "UNIQUE (" + CuentaContract.CuentaEntry.ID_CUENTA + ")" +
                "FOREIGN KEY (" + CuentaContract.CuentaEntry.ID_PACIENTE + ") " +
                "REFERENCES " + PacientesContract.PacientesEntry.TABLE_NAME + "(" + PacientesContract.PacientesEntry.ID_PACIENTE + "));";

        return qryCreateTableAccounts;
    }

}
