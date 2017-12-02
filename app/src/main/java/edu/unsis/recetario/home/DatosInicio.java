package edu.unsis.recetario.home;

/**
 * Created by Octavio on 31/10/2017.
 */

public class DatosInicio {
    String nombrePersona;
    String nombreMedicamento;
    String fechaActual;
    String dosis;

    public DatosInicio(String nombrePersona, String nombreMedicamento, String fechaActual, String dosis) {
        this.nombrePersona = nombrePersona;
        this.nombreMedicamento = nombreMedicamento;
        this.fechaActual = fechaActual;
        this.dosis = dosis;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }
}
