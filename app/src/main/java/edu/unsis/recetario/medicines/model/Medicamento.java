package edu.unsis.recetario.medicines.model;

/**
 * Created by Meltsan on 25/11/17.
 */
public class Medicamento {

    private int idMedicamento;
    private int idTratamiento;
    private String nombre;
    private String descripcion;//descripción del medicamento, o instrucciones
    private String numeroDosis; //especifica cuantas dosis tomar (1,2,3)
    private String tipoDosis; //Se especifica si es pildora, pastilla, parche, ml, cucharaditas...
    private String periodoToma; // si el medicamento se tomará cada 2, 4, 6 hrs, 0 para indicar la toma cuando sea necesario.
    private String duracionToma; //por cuanto tiempo se tomará el medicamento, 2,3,4,5, continuo.
    private char tipoDuracion; //si la duración es en Días, semanas, meses, años
    private String fechaInicio; //establece hora de inicio, a partir de esta calcular las tomas
    private String horaInicio; //establece el dia de inicio de la toma del medicamento
    private String swActivo;//medicamento activo
    private String swFinalizado; //si la toma del medicamento fue finalizada
    //agregar atributos para emitir aviso cuando el medicamento está por terminarse.


    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroDosis() {
        return numeroDosis;
    }

    public void setNumeroDosis(String numeroDosis) {
        this.numeroDosis = numeroDosis;
    }

    public String getTipoDosis() {
        return tipoDosis;
    }

    public void setTipoDosis(String tipoDosis) {
        this.tipoDosis = tipoDosis;
    }

    public String getPeriodoToma() {
        return periodoToma;
    }

    public void setPeriodoToma(String periodoToma) {
        this.periodoToma = periodoToma;
    }

    public String getDuracionToma() {
        return duracionToma;
    }

    public void setDuracionToma(String duracionToma) {
        this.duracionToma = duracionToma;
    }

    public char getTipoDuracion() {
        return tipoDuracion;
    }

    public void setTipoDuracion(char tipoDuracion) {
        this.tipoDuracion = tipoDuracion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getSwActivo() {
        return swActivo;
    }

    public void setSwActivo(String swActivo) {
        this.swActivo = swActivo;
    }

    public String getSwFinalizado() { return swFinalizado; }

    public void setSwFinalizado(String swFinalizado) {
        this.swFinalizado = swFinalizado;
    }
}
