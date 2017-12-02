package edu.unsis.recetario.notifications.model;

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
    private char sw_tomado;
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

    public char getSw_tomado() {
        return sw_tomado;
    }

    public void setSw_tomado(char sw_tomado) {
        this.sw_tomado = sw_tomado;
    }

    public String getDescripcionToma() {
        return descripcionToma;
    }

    public void setDescripcionToma(String descripcionToma) { this.descripcionToma = descripcionToma;}
}
