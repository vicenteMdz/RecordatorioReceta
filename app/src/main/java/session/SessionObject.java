package session;

import java.util.ArrayList;

import edu.unsis.recetario.medicines.model.Medicamento;
import edu.unsis.recetario.patients.model.Pacientes;

/**
 * Created by Meltsan on 04/12/17.
 */
public class SessionObject {

    private static SessionObject sessionObject;
    private Pacientes currentPacient;
    private static ArrayList<Medicamento> medicamentos;

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


    public Pacientes getCurrentPacient() {
        return currentPacient;
    }

    public void setCurrentPacient(Pacientes currentPacient) {
        this.currentPacient = currentPacient;
    }
}
