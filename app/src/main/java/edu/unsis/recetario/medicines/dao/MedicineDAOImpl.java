package edu.unsis.recetario.medicines.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.unsis.recetario.medicines.model.Medicamento;
import edu.unsis.recetario.notifications.dao.NotificacionContract;
import edu.unsis.recetario.notifications.model.Notificacion;
import edu.unsis.recetario.treatements.dao.TratamientoContract;

/**
 * Created by Octavio on 03/12/2017.
 */

public class MedicineDAOImpl extends MedicineDAO {

    private long rowId;//identificador que regresa de la filas afectadas por un query
    private String conditionWhere = "";

    public MedicineDAOImpl(Context context) {
        super(context);
    }
    public void insertMedicines(Medicamento medicamento) throws Exception {
        try {
            openWrite();
            setRowId(database.insert(MedicamentoContract.MedicamentoEntry.TABLE_NAME, null,
                    medicamento.getContentValues()));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }


    public void updateMedicine(Medicamento medicamento) throws Exception {
        try {
            openWrite();
            conditionWhere = MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO + " = ?";
            String[] selectionArgs = {""+medicamento.getIdMedicamento()};
            setRowId(database.update(MedicamentoContract.MedicamentoEntry.TABLE_NAME,
                    medicamento.getContentValues(), conditionWhere, selectionArgs));
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public void deleteMedicine(int idMedicine) throws Exception {
        try {
            openWrite();
            conditionWhere =MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO + " = ?";
            String[] selectionArgs = {""+idMedicine};
            database.delete(MedicamentoContract.MedicamentoEntry.TABLE_NAME, conditionWhere, selectionArgs);
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public void deleteMedicineByTratamiento(int idTratamiento) throws Exception {
        try {
            openWrite();
            conditionWhere =MedicamentoContract.MedicamentoEntry.IDTRATAMIENTO + " = ?";
            String[] selectionArgs = {""+idTratamiento};
            database.delete(MedicamentoContract.MedicamentoEntry.TABLE_NAME, conditionWhere, selectionArgs);
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }


    public Medicamento getMedicineById(int idMedicine) throws Exception{
        String qryGetNotification = "SELECT " + MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO+ ", " +
                MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO +", " +
                MedicamentoContract.MedicamentoEntry.IDTRATAMIENTO +", " +
                MedicamentoContract.MedicamentoEntry.NOMBRE +", " +
                MedicamentoContract.MedicamentoEntry.DESCRIPCION +", " +
                MedicamentoContract.MedicamentoEntry.NUMERODOSIS +", " +
                MedicamentoContract.MedicamentoEntry.TIPODOSIS +", " +
                MedicamentoContract.MedicamentoEntry.PERIODOTOMA +", " +
                MedicamentoContract.MedicamentoEntry.TIPOPERIODOTOMA +", " +
                MedicamentoContract.MedicamentoEntry.DURACIONTOMA +", " +
                MedicamentoContract.MedicamentoEntry.TIPODURACION +", " +
                MedicamentoContract.MedicamentoEntry.FECHAINICIO+", " +
                MedicamentoContract.MedicamentoEntry.HORAINICIO +", " +
                MedicamentoContract.MedicamentoEntry.SWACTIVO +", " +
                MedicamentoContract.MedicamentoEntry.SWFINALIZADO +" " +

                "FROM " + NotificacionContract.NotificacionEntry.TABLE_NAME + " " +
                "WHERE " + NotificacionContract.NotificacionEntry.ID_NOTIFICACION + " = " +
                idMedicine;
        Log.d("query", qryGetNotification);
        try {
            openRead();
            Cursor cursor = database.rawQuery(qryGetNotification, null);
            if(cursor.moveToNext()){
                Medicamento medicine = new Medicamento();
                medicine.setIdTratamiento(cursor.getInt(1));
                medicine.setNombre(cursor.getString(2));
                medicine.setDescripcion(cursor.getString(3));
                medicine.setNumeroDosis(cursor.getInt(4));
                medicine.setTipoDosis(cursor.getString(5));
                medicine.setPeriodoToma(cursor.getInt(6));
                medicine.setDuracionToma(cursor.getInt(7));
                medicine.setTipoDuracion(cursor.getString(8));
                medicine.setTipoPeriodoToma(cursor.getString(9));
                medicine.setFechaInicio(cursor.getString(10));
                medicine.setHoraInicio(cursor.getString(11));
                medicine.setSwActivo(cursor.getString(12));
                medicine.setSwFinalizado(cursor.getString(13));
                database.close();
                return medicine;
            }else{
                database.close();
                return null;
            }
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
    }

    public List<Medicamento> getAllMedicine()  throws Exception{
        ArrayList<Medicamento> listaMedicine = new ArrayList<Medicamento>();
        String qryGetMedicine = "SELECT " + MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO + ", " +
                MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO +", " +
                MedicamentoContract.MedicamentoEntry.IDTRATAMIENTO +", " +
                MedicamentoContract.MedicamentoEntry.NOMBRE +", " +
                MedicamentoContract.MedicamentoEntry.DESCRIPCION +", " +
                MedicamentoContract.MedicamentoEntry.NUMERODOSIS +", " +
                MedicamentoContract.MedicamentoEntry.TIPODOSIS +", " +
                MedicamentoContract.MedicamentoEntry.PERIODOTOMA +", " +
                MedicamentoContract.MedicamentoEntry.TIPOPERIODOTOMA +", " +
                MedicamentoContract.MedicamentoEntry.DURACIONTOMA +", " +
                MedicamentoContract.MedicamentoEntry.TIPODURACION +", " +
                MedicamentoContract.MedicamentoEntry.FECHAINICIO+", " +
                MedicamentoContract.MedicamentoEntry.HORAINICIO +", " +
                MedicamentoContract.MedicamentoEntry.SWACTIVO +", " +
                MedicamentoContract.MedicamentoEntry.SWFINALIZADO +" " +
                "FROM " + MedicamentoContract.MedicamentoEntry.TABLE_NAME + " ";
        Log.d("query", qryGetMedicine);
        try {
            openRead();
            Cursor cursor = database.rawQuery(qryGetMedicine, null);
            while(cursor.moveToNext()){
                Medicamento medicine = new Medicamento();
                medicine.setIdTratamiento(cursor.getInt(1));
                medicine.setNombre(cursor.getString(2));
                medicine.setDescripcion(cursor.getString(3));
                medicine.setNumeroDosis(cursor.getInt(4));
                medicine.setTipoDosis(cursor.getString(5));
                medicine.setPeriodoToma(cursor.getInt(6));
                medicine.setDuracionToma(cursor.getInt(7));
                medicine.setTipoDuracion(cursor.getString(8));
                medicine.setTipoPeriodoToma(cursor.getString(9));
                medicine.setFechaInicio(cursor.getString(10));
                medicine.setHoraInicio(cursor.getString(11));
                medicine.setSwActivo(cursor.getString(12));
                medicine.setSwFinalizado(cursor.getString(13));
                listaMedicine.add(medicine);
            }
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
        return listaMedicine;
    }

    public List<Medicamento> getMedicinesByTratamiento(int idTratamiento)  throws Exception{
        ArrayList<Medicamento> listaMedicine = new ArrayList<Medicamento>();
        String qryGetMedicine = "SELECT " + MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO + ", " +
                MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO +", " +
                MedicamentoContract.MedicamentoEntry.IDTRATAMIENTO +", " +
                MedicamentoContract.MedicamentoEntry.NOMBRE +", " +
                MedicamentoContract.MedicamentoEntry.DESCRIPCION +", " +
                MedicamentoContract.MedicamentoEntry.NUMERODOSIS +", " +
                MedicamentoContract.MedicamentoEntry.TIPODOSIS +", " +
                MedicamentoContract.MedicamentoEntry.PERIODOTOMA +", " +
                MedicamentoContract.MedicamentoEntry.TIPOPERIODOTOMA +", " +
                MedicamentoContract.MedicamentoEntry.DURACIONTOMA +", " +
                MedicamentoContract.MedicamentoEntry.TIPODURACION +", " +
                MedicamentoContract.MedicamentoEntry.FECHAINICIO+", " +
                MedicamentoContract.MedicamentoEntry.HORAINICIO +", " +
                MedicamentoContract.MedicamentoEntry.SWACTIVO +", " +
                MedicamentoContract.MedicamentoEntry.SWFINALIZADO +" " +
                "FROM " + MedicamentoContract.MedicamentoEntry.TABLE_NAME +
                " WHERE " + MedicamentoContract.MedicamentoEntry.IDTRATAMIENTO + " = " + idTratamiento;
        Log.d("query", qryGetMedicine);
        try {
            openRead();
            Cursor cursor = database.rawQuery(qryGetMedicine, null);
            while(cursor.moveToNext()){
                Medicamento medicine = new Medicamento();
                medicine.setIdTratamiento(cursor.getInt(1));
                medicine.setNombre(cursor.getString(2));
                medicine.setDescripcion(cursor.getString(3));
                medicine.setNumeroDosis(cursor.getInt(4));
                medicine.setTipoDosis(cursor.getString(5));
                medicine.setPeriodoToma(cursor.getInt(6));
                medicine.setDuracionToma(cursor.getInt(7));
                medicine.setTipoDuracion(cursor.getString(8));
                medicine.setTipoPeriodoToma(cursor.getString(9));
                medicine.setFechaInicio(cursor.getString(10));
                medicine.setHoraInicio(cursor.getString(11));
                medicine.setSwActivo(cursor.getString(12));
                medicine.setSwFinalizado(cursor.getString(13));
                listaMedicine.add(medicine);
            }
            database.close();
        }catch (Exception e){
            Log.d("ExceptionInsert", e.getCause().getMessage());
            throw new Exception();
        }
        return listaMedicine;
    }

    public int getIdMedicamentoInsertado() throws Exception{
        String qryGetMedicamentoId = "SELECT MAX("+ MedicamentoContract.MedicamentoEntry.IDMEDICAMENTO+")" +

                "FROM " + MedicamentoContract.MedicamentoEntry.TABLE_NAME + " ";
        try {
            openRead();
            Cursor cursor = database.rawQuery(qryGetMedicamentoId, null);
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

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }
}
