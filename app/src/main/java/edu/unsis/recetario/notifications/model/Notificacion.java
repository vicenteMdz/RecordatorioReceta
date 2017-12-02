package edu.unsis.recetario.notifications.model;

import android.content.ContentValues;
import android.database.Cursor;

import edu.unsis.recetario.notifications.dao.NotificacionContract;

/**
 * Created by Meltsan on 01/12/17.
 */
public class Notificacion {

    private int idNotificacion;
    private int idMedicamento;
    private String fecha;
    private String hora;
    /*T si el medicamento fué tomado, O si el medicamento no se tomó,
    y P cuando el medicamento no se ha tomado porque no ha llegado la hora*/
    private String sw_tomado;
    private String descripcionToma;


    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public int getIdMedicamento() { return idMedicamento; }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSw_tomado() {
        return sw_tomado;
    }

    public void setSw_tomado(String sw_tomado) {
        this.sw_tomado = sw_tomado;
    }

    public String getDescripcionToma() {
        return descripcionToma;
    }

    public void setDescripcionToma(String descripcionToma) { this.descripcionToma = descripcionToma;}

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotificacionContract.NotificacionEntry.ID_MEDICAMENTO,this.idMedicamento);
        contentValues.put(NotificacionContract.NotificacionEntry.FECHA, this.fecha);
        contentValues.put(NotificacionContract.NotificacionEntry.HORA, this.hora);
        contentValues.put(NotificacionContract.NotificacionEntry.DESCRIPCION_TOMA, this.descripcionToma);
        contentValues.put(NotificacionContract.NotificacionEntry.SW_TOMADO, this.sw_tomado);
        return contentValues;
    }

    public Notificacion rowMapper(Cursor result){
        Notificacion notificacion = new Notificacion();
        return notificacion;
    }
}
