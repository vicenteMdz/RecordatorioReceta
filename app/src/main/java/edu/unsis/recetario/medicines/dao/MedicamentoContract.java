package edu.unsis.recetario.medicines.dao;

import android.provider.BaseColumns;

/**
 * Created by Meltsan on 01/12/17.
 */
public class MedicamentoContract {

    public static abstract class MedicamentoEntry implements BaseColumns {

        public static final String TABLE_NAME = "medicamentos";

        public static final String  IDMEDICAMENTO = "id_medicamento";
        public static final String  IDTRATAMIENTO = "id_tratamiento";
        public static final String  NOMBRE = "nombre_medicamento";
        public static final String  DESCRIPCION = "descripcion_medicamento";
        public static final String  NUMERODOSIS = "numero_dosis";
        public static final String  TIPODOSIS = "tipo_dosis";
        public static final String  TIPOPERIODOTOMA = "tipo_periodo_toma";
        public static final String  PERIODOTOMA = "periodo_toma";
        public static final String  DURACIONTOMA = "duracion_toma";
        public static final String  TIPODURACION = "tipo_duracion";
        public static final String  FECHAINICIO = "fecha_inicio";
        public static final String  HORAINICIO = "hora_inicio";
        public static final String  SWACTIVO = "sw_activo";
        public static final String  SWFINALIZADO = "sw_finalizado";

    }
}
