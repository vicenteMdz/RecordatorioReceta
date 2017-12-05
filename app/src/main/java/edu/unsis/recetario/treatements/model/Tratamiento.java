package edu.unsis.recetario.treatements.model;

import android.content.ContentValues;

import edu.unsis.recetario.notifications.dao.NotificacionContract;
import edu.unsis.recetario.treatements.dao.TratamientoContract;

/**
 * Created by Meltsan on 25/11/17.
 */
public class Tratamiento {

    private int idTratamiento;
    private int idPaciente;
    private String nombreTratamiento;
    private String descripcion;
    private String fechaAlta;
    private String swActivo;
    private String swFinalizado;
    //agregar datos del doctor quien emitió la receta


    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombreTratamiento() {
        return nombreTratamiento;
    }

    public void setNombreTratamiento(String nombreTratamiento) { this.nombreTratamiento = nombreTratamiento; }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSwActivo() {
        return swActivo;
    }

    public void setSwActivo(String swActivo) {
        this.swActivo = swActivo;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getSwFinalizado() {
        return swFinalizado;
    }

    public void setSwFinalizado(String swFinalizado) {
        this.swFinalizado = swFinalizado;
    }

    @Override
    public String toString(){
        return "idTratamiento: " + this.idTratamiento +
                "idPaciente: " + this.idPaciente +
                "nombre: " + this.nombreTratamiento +
                "descripcion: " + this.descripcion +
                "fechaAlta: " + this.fechaAlta +
                "swActivo: " + this.swActivo +
                "swFinalizado: " + this.swFinalizado ;
    }

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TratamientoContract.TratamientoEntry.ID_PACIENTE, this.idPaciente);
        contentValues.put(TratamientoContract.TratamientoEntry.NOMBRE_TRATAMIENTO,this.nombreTratamiento);
        contentValues.put(TratamientoContract.TratamientoEntry.DESCRIPCION,this.descripcion);
        contentValues.put(TratamientoContract.TratamientoEntry.FECHA_ALTA,this.fechaAlta);
        contentValues.put(TratamientoContract.TratamientoEntry.SW_ACTIVO,this.swActivo);
        contentValues.put(TratamientoContract.TratamientoEntry.SW_FINALIZADO,this.swFinalizado);
        return contentValues;
    }
}

