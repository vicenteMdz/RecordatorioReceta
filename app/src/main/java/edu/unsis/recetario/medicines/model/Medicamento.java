package edu.unsis.recetario.medicines.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import edu.unsis.recetario.medicines.dao.MedicamentoContract;
import edu.unsis.recetario.notifications.dao.NotificacionContract;

/**
 * Created by Meltsan on 25/11/17.
 */
public class Medicamento implements Parcelable{

    private int idMedicamento;
    private int idTratamiento;
    private String nombre;
    private String descripcion;//descripción del medicamento, o instrucciones
    private float numeroDosis; //especifica cuantas dosis tomar (1,2,3)
    private String tipoDosis; //Se especifica si es pildora, pastilla, parche, ml, cucharaditas...
    private int periodoToma; // si el medicamento se tomará cada 2, 4, 6 hrs, 0 para indicar la toma cuando sea necesario.
    private String tipoPeriodoToma; //M=minuto, H=hora, D=días, S=Semanas, etc...//
    private int duracionToma; //por cuanto tiempo se tomará el medicamento, 2,3,4,5, continuo.
    private String tipoDuracion; //si la duración es en Días, semanas, meses, años
    private String fechaInicio; //establece hora de inicio, a partir de esta calcular las tomas
    private String horaInicio; //establece el dia de inicio de la toma del medicamento
    private String swActivo;//medicamento activo
    private String swFinalizado; //si la toma del medicamento fue finalizada
    //agregar atributos para emitir aviso cuando el medicamento está por terminarse.

    public Medicamento() {
    }

    /**
     * Constructor para crear el objeto a partir de un parcelable
     * @param in
     */
    public Medicamento(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Medicamento> CREATOR = new Creator<Medicamento>() {
        @Override
        public Medicamento createFromParcel(Parcel in) {
            return new Medicamento(in);
        }

        @Override
        public Medicamento[] newArray(int size) {
            return new Medicamento[size];
        }
    };

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

    public float getNumeroDosis() {
        return numeroDosis;
    }

    public void setNumeroDosis(float numeroDosis) {
        this.numeroDosis = numeroDosis;
    }

    public String getTipoDosis() {
        return tipoDosis;
    }

    public void setTipoDosis(String tipoDosis) {
        this.tipoDosis = tipoDosis;
    }

    public int getPeriodoToma() {
        return periodoToma;
    }

    public void setPeriodoToma(int periodoToma) {
        this.periodoToma = periodoToma;
    }

    public int getDuracionToma() {
        return duracionToma;
    }

    public void setDuracionToma(int duracionToma) {
        this.duracionToma = duracionToma;
    }

    public String getTipoDuracion() {
        return tipoDuracion;
    }

    public void setTipoDuracion(String tipoDuracion) {
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

    public String getTipoPeriodoToma() {
        return tipoPeriodoToma;
    }

    public void setTipoPeriodoToma(String tipoPeriodoToma) {
        this.tipoPeriodoToma = tipoPeriodoToma;
    }

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedicamentoContract.MedicamentoEntry.IDTRATAMIENTO,this.idTratamiento);
        contentValues.put(MedicamentoContract.MedicamentoEntry.NOMBRE,this.nombre);
        contentValues.put(MedicamentoContract.MedicamentoEntry.DESCRIPCION,this.descripcion);
        contentValues.put(MedicamentoContract.MedicamentoEntry.NUMERODOSIS,this.numeroDosis);
        contentValues.put(MedicamentoContract.MedicamentoEntry.TIPODOSIS,this.tipoDosis);
        contentValues.put(MedicamentoContract.MedicamentoEntry.PERIODOTOMA,this.periodoToma);
        contentValues.put(MedicamentoContract.MedicamentoEntry.TIPOPERIODOTOMA,this.tipoPeriodoToma);
        contentValues.put(MedicamentoContract.MedicamentoEntry.DURACIONTOMA,this.duracionToma);
        contentValues.put(MedicamentoContract.MedicamentoEntry.TIPODURACION,this.tipoDuracion);
        contentValues.put(MedicamentoContract.MedicamentoEntry.FECHAINICIO,this.fechaInicio);
        contentValues.put(MedicamentoContract.MedicamentoEntry.HORAINICIO,this.horaInicio);
        contentValues.put(MedicamentoContract.MedicamentoEntry.SWACTIVO,this.swActivo);
        contentValues.put(MedicamentoContract.MedicamentoEntry.SWFINALIZADO,this.swFinalizado);
        return contentValues;
    }

    @Override
    public String toString(){
        return "{ idMedicamento: " + idMedicamento +
                "\n idTratamiento: " + idTratamiento +
                "\n nombre: " + nombre +
                "\n descripcion: " + descripcion +
                "\n numeroDosis: " + numeroDosis +
                "\n tipoDosis: " + tipoDosis +
                "\n periodoToma: " + periodoToma +
                "\n tipoPeriodoToma: " + tipoPeriodoToma +
                "\n duracionToma: " + duracionToma +
                "\n tipoDuracion: " + tipoDuracion +
                "\n fechaInicio: " + fechaInicio +
                "\n horaInicio: " + horaInicio +
                "\n swActivo: " + swActivo +
                "\n swFinalizado: " + swFinalizado + "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Escribir a un parcel, OJO el orden es importante, es como escribir en un archivo binario
     * @param dest Parcel donde se va a escribir
     * @param flags ver documentacion de Parcelable.writeToParcel
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idMedicamento);
        dest.writeInt(this.idTratamiento);
        dest.writeString(this.nombre);
        dest.writeString(this.descripcion);
        dest.writeFloat(this.numeroDosis);
        dest.writeString(this.tipoDosis);
        dest.writeInt(this.periodoToma);
        dest.writeString(this.tipoPeriodoToma);
        dest.writeInt(this.duracionToma);
        dest.writeString(this.tipoDuracion);
        dest.writeString(this.fechaInicio);
        dest.writeString(this.horaInicio);
        dest.writeString(this.swActivo);
        dest.writeString(this.swFinalizado);
    }

    /**
     * Clase para recuperar los datos de un parcel, IMPORTANTE leerlos en el mismo orden que se escribieron!
     * @param in Parcel con los datos a leer
     */
    private void readFromParcel(Parcel in) {
        this.idMedicamento = in.readInt();
        this.idTratamiento = in.readInt();
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.numeroDosis = in.readFloat();
        this.tipoDosis = in.readString();
        this.periodoToma = in.readInt();
        this.tipoPeriodoToma = in.readString();
        this.duracionToma = in.readInt();
        this.tipoDuracion = in.readString();
        this.fechaInicio = in.readString();
        this.horaInicio = in.readString();
        this.swActivo = in.readString();
        this.swFinalizado = in.readString();
    }
}
