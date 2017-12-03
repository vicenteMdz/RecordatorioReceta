package edu.unsis.recetario.accounts.dao;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import edu.unsis.recetario.accounts.model.Cuenta;
import edu.unsis.recetario.notifications.dao.NotificacionContract;
import edu.unsis.recetario.notifications.model.Notificacion;

/**
 * Created by Octavio on 02/12/2017.
 */

public class AccountsDAOImpl extends AccountsDAO {
    private long rowId;//identificador que regresa de la filas afectadas por un query
    private String conditionWhere = "";

    public AccountsDAOImpl(Context context) {
        super(context);
    }

    public void insertAccounts(Cuenta cuenta) throws Exception {
        try {
            openWrite();
            setRowId(database.insert(CuentaContract.CuentaEntry.TABLE_NAME, null,
                   cuenta.getContentValues()));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public void updateAccounts(Cuenta cuenta) throws Exception {
        try {
            openWrite();
            conditionWhere = CuentaContract.CuentaEntry.ID_CUENTA + " = ?";
            String[] selectionArgs = {""+cuenta.getIdCuenta()};
            setRowId(database.update(CuentaContract.CuentaEntry.TABLE_NAME,
                    cuenta.getContentValues(), conditionWhere, selectionArgs));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public void deleteAccounts(int idAccounts) throws Exception {
        try {
            openWrite();
            conditionWhere =CuentaContract.CuentaEntry.ID_CUENTA+ " = ?";
            String[] selectionArgs = {""+idAccounts};
            database.delete(CuentaContract.CuentaEntry.TABLE_NAME, conditionWhere, selectionArgs);
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }


    public Cuenta getAccountsById(int idAccounts) throws Exception{
        String qryGetAccounts = "SELECT " +
                CuentaContract.CuentaEntry.ID_CUENTA + ","+
                CuentaContract.CuentaEntry.EMAIL + ","+
                CuentaContract.CuentaEntry.ID_PACIENTE+ ","+
                CuentaContract.CuentaEntry.TIPO_CUENTA + ","+
                CuentaContract.CuentaEntry.ID_PACIENTE_PROPIETARIO + ","+
                CuentaContract.CuentaEntry.SW_ACTIVO + ","+
                CuentaContract.CuentaEntry.FECHA_ALTA+ ","+
                CuentaContract.CuentaEntry.FECHA_ALTA_PREMIUM+ " "+
                "FROM " + CuentaContract.CuentaEntry.TABLE_NAME + " " +
                "WHERE " +CuentaContract.CuentaEntry.ID_PACIENTE+ " = " +
                idAccounts;
        Log.d("query", qryGetAccounts);
        try {
            openRead();


            Cursor cursor = database.rawQuery(qryGetAccounts, null);
            if(cursor.moveToNext()){
                Cuenta cuenta=new Cuenta();
                cuenta.setIdCuenta(cursor.getString(0));
                cuenta.setEmail(cursor.getString(1));
                cuenta.setIdPaciente(cursor.getInt(2));
                cuenta.setTipoCuenta(cursor.getString(3));
                cuenta.setIdPacientePropietario(cursor.getInt(4));
                cuenta.setSwActivo(cursor.getString(5));
                cuenta.setFechaAlta(cursor.getString(6));
                cuenta.setFechaAltaPremium(cursor.getString(7));
                database.close();
                return cuenta;
            }else{
                database.close();
                return null;
            }
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }



    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }
}
