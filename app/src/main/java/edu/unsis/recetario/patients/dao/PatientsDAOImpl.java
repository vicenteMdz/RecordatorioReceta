package edu.unsis.recetario.patients.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import edu.unsis.recetario.patients.model.Pacientes;

/**
 * Created by Octavio on 02/12/2017.
 */

public class PatientsDAOImpl extends PatientsDAO {

    private long rowId;//identificador que regresa de la filas afectadas por un query
    private String conditionWhere = "";


    public PatientsDAOImpl(Context context) {
        super(context);
    }

    public void insertPaciente(Pacientes pacientes) throws Exception {
        try {
            openWrite();
            setRowId(database.insert(PacientesContract.PacientesEntry.TABLE_NAME, null,
                    pacientes.getContentValues()));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            Log.d("ExceptionInsert",e.getLocalizedMessage() );
            throw new Exception();
        }
    }

    public void updatePaciente(Pacientes pacientes) throws Exception {
        try {
            openWrite();
            conditionWhere = PacientesContract.PacientesEntry.ID_PACIENTE + " = ?";
            String[] selectionArgs = {""+pacientes.getIdPaciente()};
            setRowId(database.update(PacientesContract.PacientesEntry.TABLE_NAME,
                    pacientes.getContentValues(), conditionWhere, selectionArgs));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public void deletePacient(int idPacient) throws Exception {
        try {
            openWrite();
            conditionWhere =PacientesContract.PacientesEntry.ID_PACIENTE+ " = ?";
            String[] selectionArgs = {""+idPacient};
            database.delete(PacientesContract.PacientesEntry.TABLE_NAME, conditionWhere, selectionArgs);
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public Pacientes getPacienteById(int idPaciente) throws Exception{
        String qryGetPaciente = "SELECT " +
                PacientesContract.PacientesEntry.ID_PACIENTE + ","+
                PacientesContract.PacientesEntry.NOMBRE_PACIENTE + ","+
                PacientesContract.PacientesEntry.PRIMER_APELLIDO + ","+
                PacientesContract.PacientesEntry.SEGUNDO_APELLIDO + ","+
                PacientesContract.PacientesEntry.EDAD + ","+
                PacientesContract.PacientesEntry.PESO + ","+
                PacientesContract.PacientesEntry.TIPOSANGRE + " "+


                "FROM " + PacientesContract.PacientesEntry.TABLE_NAME + " " +
                "WHERE " +PacientesContract.PacientesEntry.ID_PACIENTE+ " = " +
                idPaciente+ ";";
        Log.d("query", qryGetPaciente);
        try {
            openRead();


            Cursor cursor = database.rawQuery(qryGetPaciente, null);
            if(cursor.moveToNext()){
                Pacientes pacientes=new Pacientes();
                pacientes.setIdPaciente(cursor.getInt(0));
                pacientes.setNombre(cursor.getString(1));
                pacientes.setPrimerApellido(cursor.getString(2));
                pacientes.setSegundoApellido(cursor.getString(3));
                pacientes.setEdad(cursor.getString(4));
                pacientes.setPeso(cursor.getString(5));
                pacientes.setTipoSangre(cursor.getString(6));
                database.close();
                return pacientes;
            }else{
                database.close();
                return null;
            }
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public Pacientes getLastId() throws Exception{
        String qryGetPaciente = "SELECT MAX("+PacientesContract.PacientesEntry.ID_PACIENTE+")" +

                "FROM " + PacientesContract.PacientesEntry.TABLE_NAME + " "; //+
                //"WHERE " +PacientesContract.PacientesEntry.ID_PACIENTE+ " = " +
                //idPaciente+ ";";
        try {
            openRead();


            Cursor cursor = database.rawQuery(qryGetPaciente, null);
            if(cursor.moveToNext()){
                Pacientes pacientes=new Pacientes();
                pacientes.setIdPaciente(cursor.getInt(0));
                database.close();
                return pacientes;
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
