package edu.unsis.recetario.treatements;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.unsis.recetario.R;
import edu.unsis.recetario.home.AdaptadorInicio;
import edu.unsis.recetario.home.DatosInicio;
import edu.unsis.recetario.home.Home;
import edu.unsis.recetario.medicines.add_medicines;
import edu.unsis.recetario.medicines.dao.MedicineDAOImpl;
import edu.unsis.recetario.medicines.model.Medicamento;
import edu.unsis.recetario.notifications.alarms.receive.AlarmReceiver;
import edu.unsis.recetario.notifications.alarms.schedule.SchedulingAlarm;
import edu.unsis.recetario.treatements.dao.TratamientoDAOImpl;
import edu.unsis.recetario.treatements.model.Tratamiento;
import session.SessionObject;

public class AddTreatement extends AppCompatActivity {

    public View view;
    EditText nombreTratamiento;
    EditText descripcionTratamiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_treatement);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.headerActivityAddTreatement);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Añadir Tratamiento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.window_close);

        ArrayList<Medicamento> medicamentos = SessionObject.getListMedicamentos();
        Log.d("listCount", ""+medicamentos.size());
        for(Medicamento m : medicamentos){
            Log.d("medicamento",m.toString());
        }

        RecyclerView contendor=(RecyclerView) findViewById(R.id.RecicleViewShowMedicines);
        contendor.setHasFixedSize(true);
        LinearLayoutManager layout=new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        contendor.setLayoutManager(layout);
        ListMedicineAdapter adapter = new ListMedicineAdapter(medicamentos);
        contendor.setAdapter(adapter);
        Log.d("xxxx", "Se finalizó la creación del reciclerview");

        findViewById(R.id.addMedicine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTreatement.this, add_medicines.class);
                startActivity(intent);
            }
        });

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_treatement, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.saveTreatement:
                guardaTratamientos();
                return true;
            case android.R.id.home:
                SessionObject.setMedicamentos(null);
                Intent intent = new Intent(AddTreatement.this, Home.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void guardaTratamientos(){
        nombreTratamiento = (EditText) findViewById(R.id.txtNombreTratamiento);
        descripcionTratamiento = (EditText) findViewById(R.id.descripcionTratamiento);
        //validamos los campos

        //Bandera evidenciar algun error durante la validación de los datos
        boolean cancel = false;
        //Variable para contener el campo a ser enfocado
        View focusView = null;

        if (TextUtils.isEmpty(nombreTratamiento.getText().toString())) {
            nombreTratamiento.setError(getString(R.string.error_field_required));
            focusView = nombreTratamiento;
            cancel = true;
        }
        if (TextUtils.isEmpty(descripcionTratamiento.getText().toString())) {
            descripcionTratamiento.setError(getString(R.string.error_field_required));
            focusView = descripcionTratamiento;
            cancel = true;
        }
        if (cancel) {
            //Enfocar el Campo del Error

            focusView.requestFocus();

        } else {
            Log.d("paciente",SessionObject.getCurrentPacient().toString());
            if(SessionObject.getInstance().getCurrentPacient() != null){
                //declaramos interfaces java para persistir tratamientos y medicamentos en java
                TratamientoDAOImpl tratamientoDAO = new TratamientoDAOImpl(this);
                MedicineDAOImpl medicineDAO = new MedicineDAOImpl(this);
                //Obteniendo la fecha actual
                Date dNow = new Date();
                SimpleDateFormat diaN = new SimpleDateFormat ("dd/MM/yyyy");
                String date = diaN.format(dNow.getTime());
                Tratamiento tratamiento = new Tratamiento();
                tratamiento.setDescripcion(descripcionTratamiento.getText().toString());
                tratamiento.setFechaAlta(date);
                tratamiento.setIdPaciente(SessionObject.getInstance().getCurrentPacient().getIdPaciente());
                tratamiento.setNombreTratamiento(nombreTratamiento.getText().toString());
                tratamiento.setSwActivo("A");
                tratamiento.setSwFinalizado("N");
                try{
                    //persistir el medicamento en la base de datos
                    tratamientoDAO.insertTratamiento(tratamiento);
                    tratamiento.setIdTratamiento(tratamientoDAO.getIdTratamientoInsertado());
                    //reccorrer la lista de medicamentos e insertar en la tabla de medicamentos
                    ArrayList<Medicamento> medicamentos = SessionObject.getListMedicamentos();
                    Log.d("listCount", ""+medicamentos.size());
                    for(Medicamento m : medicamentos){
                        m.setIdTratamiento(tratamiento.getIdTratamiento());
                        //persistimos cada medicamento en la base de datos
                        medicineDAO.insertMedicines(m);

                        //programamos la alarma para cada medicamento

                        /*Intent alarmIntent = new Intent(AddTreatement.this, AlarmReceiver.class);
                        //agregar un id al intent para despues con ese id eliminar la notificacion
                        alarmIntent.putExtra("alarmaId",tratamiento.getNombreTratamiento().substring(0,2) +
                            "_" + m.getNombre().substring(0,2) + "_" + m.getHoraInicio());
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(AddTreatement.this, 0, alarmIntent, 0);
                        SchedulingAlarm schedulingAlarm = new SchedulingAlarm(getBaseContext());
                        try{
                            schedulingAlarm.createAlarm(m, pendingIntent);
                        }catch(Exception e){
                            Log.d("errorSchedulingTask", e.getCause().getMessage());
                            e.printStackTrace();
                        }*/
                    }
                    SessionObject.setMedicamentos(null);
                    Intent intent = new Intent(AddTreatement.this, Home.class);
                    startActivity(intent);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(this, "No hay usuario en session", Toast.LENGTH_SHORT);
            }
        }
    }



}
