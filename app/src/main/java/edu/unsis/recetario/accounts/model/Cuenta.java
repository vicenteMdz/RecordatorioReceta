package edu.unsis.recetario.accounts.model;

/**
 * Created by Meltsan on 01/12/17.
 */
public class Cuenta {

    private String idCuenta;
    private String email;//email que nos servirá al registrar y actualizar a premium en la app
    private int idPaciente;//id de los pacientes que están asociados a esta cuenta
    private char tipoCuenta;//T=cuenta en versión de prueba, P=cuenta versión premiúm
    /*
       * null para el propietario de la cuenta
       * para perfiles asociados se agregará el id del paciente quien creó la cuenta.
       *
    */
    private int idPacientePropietario;
    private char swActivo;
    private String fechaAlta;
    private String fechaAltaPremium;

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public char getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(char tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getIdPacientePropietario() {
        return idPacientePropietario;
    }

    public void setIdPacientePropietario(int idPacientePropietario) {
        this.idPacientePropietario = idPacientePropietario;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaAltaPremium() {
        return fechaAltaPremium;
    }

    public void setFechaAltaPremium(String fechaAltaPremium) {
        this.fechaAltaPremium = fechaAltaPremium;
    }

    public char getSwActivo() {
        return swActivo;
    }

    public void setSwActivo(char swActivo) {
        this.swActivo = swActivo;
    }
}
