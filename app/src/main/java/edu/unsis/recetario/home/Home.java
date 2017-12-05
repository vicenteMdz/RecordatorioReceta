package edu.unsis.recetario.home;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import edu.unsis.recetario.R;
import edu.unsis.recetario.accounts.dao.AccountsDAOImpl;
import edu.unsis.recetario.medicines.model.Medicamento;
import edu.unsis.recetario.notifications.alarms.receive.AlarmReceiver;
import edu.unsis.recetario.notifications.alarms.schedule.SchedulingAlarm;
import edu.unsis.recetario.notifications.model.Notificacion;
import edu.unsis.recetario.patients.dao.PatientsDAOImpl;
import edu.unsis.recetario.patients.model.Pacientes;
import edu.unsis.recetario.treatements.AddTreatement;
import session.SessionObject;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AccountsDAOImpl accountscuentaDAO=new AccountsDAOImpl(this);
    PatientsDAOImpl pacientesDAO = new PatientsDAOImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Medicamentos para hoy");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Cargamos el fragmento Inicial, Menú item Hoy.
        setInitialFragmet();
        //cargamos los datos del usuario,
        try{
            //primero obtenemos el id del paciente de la tabla cuentas
            int idPaciente = accountscuentaDAO.getAccountAdmin();
            SessionObject sessionObject = SessionObject.getInstance();
            Pacientes paciente = pacientesDAO.getPacienteById(idPaciente);
            sessionObject.setCurrentPacient(paciente);
            Log.d("user:: ", paciente.toString());
            TextView userName = (TextView) findViewById(R.id.userNamexxx);
            String nom= paciente.getNombre() + " " + paciente.getPrimerApellido() +
                    " " + paciente.getSegundoApellido();
            userName.setText(nom);
        }catch(Exception e){
            Toast.makeText(this, "Error al cargar datos del usario", Toast.LENGTH_SHORT);
        }


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alarmIntent = new Intent(Home.this, AlarmReceiver.class);
                //agregar un id al intent para despues con ese id eliminar la notificacion
                PendingIntent pendingIntent = PendingIntent.getBroadcast(Home.this, 0, alarmIntent, 0);

                SchedulingAlarm schedulingAlarm = new SchedulingAlarm(getBaseContext());
                Medicamento m = new Medicamento();
                m.setFechaInicio("2017-12-01 01:46:00");
                m.setTipoPeriodoToma("M");
                m.setPeriodoToma(1);
                try{
                    schedulingAlarm.createAlarm(m, pendingIntent);
                }catch(Exception e){
                    Log.d("error", e.getCause().getMessage());
                }
            }
        });*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.treatement:
                Log.d("createIntent","launch intent");
                Intent intent = new Intent(Home.this, AddTreatement.class);
                startActivity(intent);
                Log.d("createIntent","launch intent");
                break;
            case R.id.today:
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.appBody, new Inicio());
                tx.commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setInitialFragmet(){
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.appBody, new Inicio());
        tx.commit();
    }
}
