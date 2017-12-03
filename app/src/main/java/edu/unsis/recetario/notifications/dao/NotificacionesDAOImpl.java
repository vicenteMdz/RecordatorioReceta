package edu.unsis.recetario.notifications.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.unsis.recetario.notifications.model.Notificacion;

/**
 * Created by Meltsan on 02/12/17.
 */
public class NotificacionesDAOImpl extends NotificacionesDAO {

    private long rowId;//identificador que regresa de la filas afectadas por un query
    private String conditionWhere = "";

    public NotificacionesDAOImpl(Context context){
        super(context);
    }

    public void insertNotification(Notificacion notificacion) throws Exception {
        try {
            openWrite();
            setRowId(database.insert(NotificacionContract.NotificacionEntry.TABLE_NAME, null,
                    notificacion.getContentValues()));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public void updateNotification(Notificacion notificacion) throws Exception {
        try {
            openWrite();
            conditionWhere = NotificacionContract.NotificacionEntry.ID_NOTIFICACION + " = ?";
            String[] selectionArgs = {""+notificacion.getIdNotificacion()};
            setRowId(database.update(NotificacionContract.NotificacionEntry.TABLE_NAME,
                    notificacion.getContentValues(), conditionWhere, selectionArgs));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public void deleteNotification(int idNotificacion) throws Exception {
        try {
            openWrite();
            conditionWhere = NotificacionContract.NotificacionEntry.ID_NOTIFICACION + " = ?";
            String[] selectionArgs = {""+idNotificacion};
            database.delete(NotificacionContract.NotificacionEntry.TABLE_NAME, conditionWhere, selectionArgs);
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public Notificacion getNotificacionById(int idNotification) throws Exception{
        String qryGetNotification = "SELECT " + NotificacionContract.NotificacionEntry.ID_NOTIFICACION + ", " +
                NotificacionContract.NotificacionEntry.ID_MEDICAMENTO +", " +
                NotificacionContract.NotificacionEntry.FECHA +", " +
                NotificacionContract.NotificacionEntry.HORA +", " +
                NotificacionContract.NotificacionEntry.DESCRIPCION_TOMA +", " +
                NotificacionContract.NotificacionEntry.ID_MEDICAMENTO +", " +
                NotificacionContract.NotificacionEntry.ID_MEDICAMENTO +" " +
                "FROM " + NotificacionContract.NotificacionEntry.TABLE_NAME + " " +
                "WHERE " + NotificacionContract.NotificacionEntry.ID_NOTIFICACION + " = " +
                idNotification;
        Log.d("query", qryGetNotification);
        try {
            openRead();
            Cursor cursor = database.rawQuery(qryGetNotification, null);
            if(cursor.moveToNext()){
                Notificacion notificacion = new Notificacion();
                notificacion.setDescripcionToma(cursor.getString(5));
                notificacion.setFecha(cursor.getString(3));
                notificacion.setHora(cursor.getString(4));
                notificacion.setIdMedicamento(cursor.getInt(2));
                notificacion.setIdNotificacion(cursor.getInt(1));
                notificacion.setSw_tomado(cursor.getString(6));
                database.close();
                return notificacion;
            }else{
                database.close();
                return null;
            }
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public List<Notificacion> getAllNotificacion()  throws Exception{
        ArrayList<Notificacion> listaNotificiones = new ArrayList<Notificacion>();
        String qryGetNotification = "SELECT " + NotificacionContract.NotificacionEntry.ID_NOTIFICACION + ", " +
                NotificacionContract.NotificacionEntry.ID_MEDICAMENTO +", " +
                NotificacionContract.NotificacionEntry.FECHA +", " +
                NotificacionContract.NotificacionEntry.HORA +", " +
                NotificacionContract.NotificacionEntry.DESCRIPCION_TOMA +", " +
                NotificacionContract.NotificacionEntry.ID_MEDICAMENTO +", " +
                NotificacionContract.NotificacionEntry.ID_MEDICAMENTO +" " +
                "FROM " + NotificacionContract.NotificacionEntry.TABLE_NAME + " ";
        Log.d("query", qryGetNotification);
        try {
            openRead();
            Cursor cursor = database.rawQuery(qryGetNotification, null);
            while(cursor.moveToNext()){
                Notificacion notificacion = new Notificacion();
                notificacion.setDescripcionToma(cursor.getString(5));
                notificacion.setFecha(cursor.getString(3));
                notificacion.setHora(cursor.getString(4));
                notificacion.setIdMedicamento(cursor.getInt(2));
                notificacion.setIdNotificacion(cursor.getInt(1));
                notificacion.setSw_tomado(cursor.getString(6));
                listaNotificiones.add(notificacion);
            }
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
        return listaNotificiones;
    }

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }
}
