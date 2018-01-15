package edu.unsis.recetario.treatements.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.unsis.recetario.medicines.dao.MedicamentoContract;
import edu.unsis.recetario.medicines.model.Medicamento;
import edu.unsis.recetario.notifications.dao.NotificacionContract;
import edu.unsis.recetario.notifications.model.Notificacion;
import edu.unsis.recetario.patients.dao.PacientesContract;
import edu.unsis.recetario.treatements.model.Tratamiento;

/**
 * Created by Octavio on 03/12/2017.
 */

public class TratamientoDAOImpl extends  TratamientoDAO {

    private long rowId;//identificador que regresa de la filas afectadas por un query
    private String conditionWhere = "";
    public TratamientoDAOImpl(Context context) {
        super(context);
    }

    public void insertTratamiento(Tratamiento tratamiento) throws Exception {
        try {
            openWrite();
            setRowId(database.insert(TratamientoContract.TratamientoEntry.TABLE_NAME, null,
                    tratamiento.getContentValues()));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public int getIdTratamientoInsertado() throws Exception{
        String qryGetTratamiento = "SELECT MAX("+ TratamientoContract.TratamientoEntry.ID_TRATAMIENTO+")" +

                "FROM " + TratamientoContract.TratamientoEntry.TABLE_NAME + " ";
        try {
            openRead();
            Cursor cursor = database.rawQuery(qryGetTratamiento, null);
            if(cursor.moveToNext()){
                int r = cursor.getInt(0);
                database.close();
                return r;
            }else{
                database.close();
                return -1;
            }
        }catch (Exception e){
            Log.d("ExceptionGetLasId", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public void updateTratamiento(Tratamiento tratamiento) throws Exception {
        try {
            openWrite();
            conditionWhere = TratamientoContract.TratamientoEntry.ID_TRATAMIENTO+ " = ?";
            String[] selectionArgs = {""+tratamiento.getIdTratamiento()};
            setRowId(database.update(MedicamentoContract.MedicamentoEntry.TABLE_NAME,
                    tratamiento.getContentValues(), conditionWhere, selectionArgs));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public void deleteTratamiento(int idTratamiento) throws Exception {
        try {
            openWrite();
            conditionWhere = TratamientoContract.TratamientoEntry.ID_TRATAMIENTO + " = ?";
            String[] selectionArgs = {""+idTratamiento};
            database.delete(TratamientoContract.TratamientoEntry.TABLE_NAME, conditionWhere, selectionArgs);
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public Tratamiento getTratamientoById(int idTratamiento) throws Exception{
        String qryGetTratamiento = "SELECT "
                + TratamientoContract.TratamientoEntry.ID_TRATAMIENTO + ", " +
                TratamientoContract.TratamientoEntry.ID_PACIENTE+ ", " +
                TratamientoContract.TratamientoEntry.NOMBRE_TRATAMIENTO + ", " +
                TratamientoContract.TratamientoEntry.DESCRIPCION+ ", " +
                TratamientoContract.TratamientoEntry.FECHA_ALTA + ", " +
                TratamientoContract.TratamientoEntry.SW_ACTIVO+ ", " +
                TratamientoContract.TratamientoEntry.SW_FINALIZADO+ " " +
                "FROM " + TratamientoContract.TratamientoEntry.TABLE_NAME + " " +
                "WHERE " + TratamientoContract.TratamientoEntry.ID_TRATAMIENTO + " = " +
                idTratamiento;
        Log.d("query", qryGetTratamiento);
        try {
            openRead();
            Cursor cursor = database.rawQuery(qryGetTratamiento, null);
            if(cursor.moveToNext()){
                Log.d("query", "Entro rn el if");
                Tratamiento tratamiento = new Tratamiento();
                tratamiento.setIdTratamiento(cursor.getInt(0));
                tratamiento.setIdPaciente(cursor.getInt(1));
                tratamiento.setNombreTratamiento(cursor.getString(2));
                tratamiento.setDescripcion(cursor.getString(3));
                tratamiento.setFechaAlta(cursor.getString(4));
                tratamiento.setSwActivo(cursor.getString(5));
                tratamiento.setSwFinalizado(cursor.getString(6));
                database.close();
                return tratamiento;
            }else{
                database.close();
                return null;
            }
        }catch (Exception e){
           // Log.d("ExceptioID", e.getCause().getMessage());
            Log.d("Exception select por ID", e.toString());
            throw new Exception();
        }
    }

    public List<Tratamiento> getAllTratamiento()  throws Exception{
        ArrayList<Tratamiento> listaTratamiento = new ArrayList<Tratamiento>();
        String qryGetTratamiento = "SELECT "
                + TratamientoContract.TratamientoEntry.ID_TRATAMIENTO + ", " +
                TratamientoContract.TratamientoEntry.ID_PACIENTE+ ", " +
                TratamientoContract.TratamientoEntry.NOMBRE_TRATAMIENTO + ", " +
                TratamientoContract.TratamientoEntry.DESCRIPCION+ ", " +
                TratamientoContract.TratamientoEntry.FECHA_ALTA + ", " +
                TratamientoContract.TratamientoEntry.SW_ACTIVO+ ", " +
                TratamientoContract.TratamientoEntry.SW_FINALIZADO+ " " +
                "FROM " + TratamientoContract.TratamientoEntry.TABLE_NAME + " ";
        Log.d("query", qryGetTratamiento);
        try {
            openRead();
            Cursor cursor = database.rawQuery(qryGetTratamiento, null);
            while(cursor.moveToNext()){
                Tratamiento tratamiento = new Tratamiento();
                tratamiento.setIdTratamiento(cursor.getInt(0));
                tratamiento.setIdPaciente(cursor.getInt(1));
                tratamiento.setNombreTratamiento(cursor.getString(2));
                tratamiento.setDescripcion(cursor.getString(3));
                tratamiento.setFechaAlta(cursor.getString(4));
                tratamiento.setSwActivo(cursor.getString(5));
                tratamiento.setSwFinalizado(cursor.getString(6));
                listaTratamiento.add(tratamiento);
            }
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
        return listaTratamiento;
    }
    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }
}
