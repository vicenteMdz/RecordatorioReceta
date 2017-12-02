package edu.unsis.recetario.treatements.model;

/**
 * Created by Meltsan on 25/11/17.
 */
public class Tratamiento {

    private int idTratamiento;
    private int idPaciente;
    private String nombreTratamiento;
    private String descripcion;
    private String fechaAlta;
    private char swActivo;
    private char swFinalizado;
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

    public char getSwActivo() {
        return swActivo;
    }

    public void setSwActivo(char swActivo) {
        this.swActivo = swActivo;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public char getSwFinalizado() {
        return swFinalizado;
    }

    public void setSwFinalizado(char swFinalizado) {
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
}
