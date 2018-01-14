package edu.unsis.recetario.treatements;

/**
 * Created by Octavio on 31/10/2017.
 */

public class DatosListaTratamientos {
    private String nombreTratamiento;

    public DatosListaTratamientos(String nombreTratamiento) {
        this.nombreTratamiento = nombreTratamiento;

    }

    public String getNombreTratamiento() {
        return nombreTratamiento;
    }

    public void setNombreTratamiento(String nombreTratamiento) {
        this.nombreTratamiento = nombreTratamiento;
    }
}
