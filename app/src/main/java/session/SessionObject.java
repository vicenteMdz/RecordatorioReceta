package session;

import java.util.ArrayList;

import edu.unsis.recetario.medicines.model.Medicamento;
import edu.unsis.recetario.patients.model.Pacientes;
import edu.unsis.recetario.treatements.model.Tratamiento;

/**
 * Created by Meltsan on 04/12/17.
 */
public class SessionObject {

    private static SessionObject sessionObject;
    private static Pacientes currentPacient;
    private static ArrayList<Medicamento> medicamentos;
    private static Tratamiento tratamiento;

    public static SessionObject getInstance(){
        if (sessionObject == null){
            sessionObject = new SessionObject();
        }
        return sessionObject;
    }

    public static ArrayList<Medicamento> getListMedicamentos(){
        if(medicamentos == null){
            medicamentos = new ArrayList<Medicamento>();
        }
        return medicamentos;
    }

    public static ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public static void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        sessionObject.medicamentos = medicamentos;
    }

    public static Tratamiento getTratamiento() {
        if(tratamiento == null){
            tratamiento = new Tratamiento();
        }
        return tratamiento;
    }

    public static void setTratamiento(Tratamiento tratamiento) {
        SessionObject.tratamiento = tratamiento;
    }

    public static Pacientes getCurrentPacient() {
        if(currentPacient == null){
            currentPacient = new Pacientes();
        }
        return currentPacient;
    }

    public static void setCurrentPacient(Pacientes currentPacient) {
        sessionObject.currentPacient = currentPacient;
    }

    public static void deleteMedicine(int index){
        try{
            getMedicamentos().remove(index);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
