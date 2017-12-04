package edu.unsis.recetario.accounts.model;

import android.content.ContentValues;

import edu.unsis.recetario.accounts.dao.CuentaContract;

/**
 * Created by Meltsan on 01/12/17.
 */
public class Cuenta {

    private String idCuenta;
    private String email;//email que nos servirá al registrar y actualizar a premium en la app
    private int idPaciente;//id de los pacientes que están asociados a esta cuenta
    private String tipoCuenta;//T=cuenta en versión de prueba, P=cuenta versión premiúm
    /*
       * null para el propietario de la cuenta
       * para perfiles asociados se agregará el id del paciente quien creó la cuenta.
       *
    */
    private int idPacientePropietario;
    private String swActivo;
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

    public String getSwActivo() {
        return swActivo;
    }

    public void setSwActivo(String swActivo) {
        this.swActivo = swActivo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CuentaContract.CuentaEntry.EMAIL,this.email);
        contentValues.put(CuentaContract.CuentaEntry.ID_PACIENTE,this.idPaciente);
        contentValues.put(CuentaContract.CuentaEntry.TIPO_CUENTA, this.getTipoCuenta());
        contentValues.put(CuentaContract.CuentaEntry.ID_PACIENTE,this.idPacientePropietario);
        contentValues.put(CuentaContract.CuentaEntry.SW_ACTIVO, this.getSwActivo());
        contentValues.put(CuentaContract.CuentaEntry.FECHA_ALTA,this.fechaAlta);
        contentValues.put(CuentaContract.CuentaEntry.FECHA_ALTA_PREMIUM,this.fechaAltaPremium);
        return contentValues;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "idCuenta='" + idCuenta + '\'' +
                ", email='" + email + '\'' +
                ", idPaciente=" + idPaciente +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", idPacientePropietario=" + idPacientePropietario +
                ", swActivo='" + swActivo + '\'' +
                ", fechaAlta='" + fechaAlta + '\'' +
                ", fechaAltaPremium='" + fechaAltaPremium + '\'' +
                '}';
    }
}
