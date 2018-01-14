package edu.unsis.recetario.medicines;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import edu.unsis.recetario.R;
import edu.unsis.recetario.medicines.model.Medicamento;
import edu.unsis.recetario.home.Home;
import edu.unsis.recetario.treatements.AddTreatement;
import session.SessionObject;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import static edu.unsis.recetario.R.id.edtHora;

public class add_medicines extends AppCompatActivity implements OnClickListener{

    //UI References
    private EditText Nombre;
    private EditText Descripcion;
    private EditText NumDosis;
    private Spinner Dosis;
    private EditText numToma;
    private Spinner Toma;
    private EditText NumDuracion;
    private Spinner Duracion;
    private EditText Fecha;
    private EditText Hora;
    private EditText fromDateEtxt;
    private EditText txtTime,txtduracionToma;
    private TextView textViewDuracion;
    private Spinner spinnerDuracion;
    private Button AddMedicines;
    private   Switch sButton;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private int mHour, mMinute,seg,mils;
    //variable local que nos ayuda a verificar si se esta editando un medicamento o añadiendo uno nuevo
    private boolean edit;
    private Medicamento medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicines);

        Toolbar toolbar = (Toolbar) findViewById(R.id.headerActivityAddMedicines);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Añadir Medicamento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.window_close);

        edit = false;

        //obtenemos la instancia del medicamento, para la edición
        medicamento = getIntent().getParcelableExtra("medicamento");
        if(medicamento != null){//cargar los datos del medicamento
            Log.d("medicamento", medicamento.toString());
            loadData(medicamento);
            edit = true;
        }

        fromDateEtxt = (EditText) findViewById(R.id.edtFecha);
        fromDateEtxt.setOnClickListener(this);

       // FechaActual();
        // HoraActual();
        //Get widgets reference from XML layout
        txtduracionToma = (EditText) findViewById(R.id.edtNumDuracion);
        textViewDuracion = (TextView) findViewById(R.id.textVliew18);
        spinnerDuracion  = (Spinner) findViewById(R.id.spDuracion);
        sButton = (Switch) findViewById(R.id.swDuracion);
        sButton.setOnClickListener(this);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        txtTime = (EditText) findViewById(R.id.edtHora);
        txtTime.setOnClickListener(this);

        findViewsById();

        setDateTimeField();

    }

    public void loadData(Medicamento medicamento){
        Nombre=(EditText) findViewById(R.id.EdtnomMedicamento);
        Descripcion=(EditText) findViewById(R.id.edtDescripcion);
        NumDosis=(EditText) findViewById(R.id.edTNumDosis);
        Dosis=(Spinner) findViewById(R.id.spDosis);
        numToma=(EditText) findViewById(R.id.edtNumTomas);
        Toma=(Spinner) findViewById(R.id.spTomas);
        NumDuracion=(EditText) findViewById(R.id.edtNumDuracion);
        Duracion=(Spinner) findViewById(R.id.spDuracion);
        Fecha=(EditText) findViewById(R.id.edtFecha);
        Hora=(EditText) findViewById(edtHora);

        String [] arrayDosis = this.getBaseContext().getResources().getStringArray(R.array.dosis);
        String [] arrayTiempoToma = this.getBaseContext().getResources().getStringArray(R.array.tiempo);
        String [] arrayDuracionToma = this.getBaseContext().getResources().getStringArray(R.array.duracion);

        Log.d("indexDosis",""+Arrays.asList(arrayDosis).indexOf(medicamento.getTipoDosis()));
        Log.d("indexTipoPeriodoToma",""+Arrays.asList(arrayTiempoToma).indexOf(medicamento.getTipoPeriodoToma()));
        Log.d("indexDuracionToma",""+Arrays.asList(arrayDuracionToma).indexOf(medicamento.getTipoDuracion()));

        Nombre.setText(medicamento.getNombre());
        Descripcion.setText(medicamento.getDescripcion());
        NumDosis.setText(""+medicamento.getNumeroDosis());
        Dosis.setSelection(Arrays.asList(arrayDosis).indexOf(medicamento.getTipoDosis()));
        numToma.setText(""+medicamento.getPeriodoToma());
        Toma.setSelection(Arrays.asList(arrayTiempoToma).indexOf(medicamento.getTipoPeriodoToma()));
        NumDuracion.setText(""+medicamento.getDuracionToma());
        Duracion.setSelection(Arrays.asList(arrayDuracionToma).indexOf(medicamento.getTipoDuracion()));
        Fecha.setText(medicamento.getFechaInicio());
        Hora.setText(medicamento.getHoraInicio());
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_medicines, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Nombre=(EditText) findViewById(R.id.EdtnomMedicamento);
        Descripcion=(EditText) findViewById(R.id.edtDescripcion);
        NumDosis=(EditText) findViewById(R.id.edTNumDosis);
        Dosis=(Spinner) findViewById(R.id.spDosis);
        numToma=(EditText) findViewById(R.id.edtNumTomas);
        Toma=(Spinner) findViewById(R.id.spTomas);
        NumDuracion=(EditText) findViewById(R.id.edtNumDuracion);
        Duracion=(Spinner) findViewById(R.id.spDuracion);
        Fecha=(EditText) findViewById(R.id.edtFecha);
        Hora=(EditText) findViewById(edtHora);
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.saveMedicine:
                saveMedicine();
                return true;
            case android.R.id.home:
                int cont =0;
                if(edit){//se está editando un medicamento
                    if(!Nombre.getText().toString().equals(medicamento.getNombre())){
                        cont++;
                    }
                    if(!Descripcion.getText().toString().equals(medicamento.getDescripcion())){
                        cont++;
                    }
                    if( Float.parseFloat(NumDosis.getText().toString()) != medicamento.getNumeroDosis()){
                        cont++;
                    }
                    if(!Dosis.getSelectedItem().toString().equals(medicamento.getTipoDosis())){
                        cont++;
                    }
                    if(Integer.parseInt(numToma.getText().toString()) != medicamento.getPeriodoToma()){
                        cont++;
                    }
                    if(!Toma.getSelectedItem().toString().equals(medicamento.getTipoPeriodoToma())){
                        cont++;
                    }
                    if(Integer.parseInt(NumDuracion.getText().toString()) != medicamento.getDuracionToma()){
                        cont++;
                    }
                    if(!Duracion.getSelectedItem().toString().equals(medicamento.getTipoDuracion())){
                        cont++;
                    }
                    if(!Hora.getText().toString().equals(medicamento.getHoraInicio())){
                        cont++;
                    }
                    if(!Fecha.getText().toString().equals(medicamento.getFechaInicio())){
                        cont++;
                    }
                } else {//se añade un nuevo medicamento
                    //Variable para contener el campo a ser enfocado
                    View focusView = null;
                    if (!TextUtils.isEmpty(Nombre.getText().toString().trim())) {
                        cont=cont+1;
                    }
                    if (!TextUtils.isEmpty(Descripcion.getText().toString().trim())) {
                        cont=cont+1;
                    }
                }

                if (cont>0) {

                    //Crea el diálogo
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //Define el mensaje
                    builder.setMessage("¿Deseas salir sin guardar los datos?");
                    //Le agrega el botón "Sí"
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if(edit){
                                SessionObject.getListMedicamentos().add(medicamento);
                            }
                            Intent intent = new Intent(add_medicines.this, AddTreatement.class);
                            startActivity(intent);

                        }
                    });
                    //Le agrega el botón "No"
                    builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    //Muestra el dialogo
                    builder.show();

                } else {
                    if(edit){
                        medicamento.setNombre(Nombre.getText().toString());
                        medicamento.setDescripcion(Descripcion.getText().toString());
                        medicamento.setNumeroDosis(Float.parseFloat(NumDosis.getText().toString()));
                        medicamento.setTipoDosis(Dosis.getSelectedItem().toString());
                        medicamento.setPeriodoToma(Integer.parseInt(numToma.getText().toString()));
                        medicamento.setTipoPeriodoToma(Toma.getSelectedItem().toString());
                        medicamento.setDuracionToma(Integer.parseInt(NumDuracion.getText().toString()));
                        medicamento.setTipoDuracion(Duracion.getSelectedItem().toString());
                        medicamento.setHoraInicio(Hora.getText().toString());
                        medicamento.setFechaInicio(Fecha.getText().toString());
                        medicamento.setSwActivo("A");
                        medicamento.setSwFinalizado("N");
                        SessionObject.getListMedicamentos().add(medicamento);
                    }
                    Intent intent = new Intent(add_medicines.this, AddTreatement.class);
                    startActivity(intent);

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void findViewsById()  {

        fromDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        //fromDateEtxt.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    private  void FechaActual(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        fromDateEtxt.setText(fecha);

    }

    private void saveMedicine(){

        //Bandera evidenciar algun error durante la validación de los datos
        boolean cancel = false;
        //Variable para contener el campo a ser enfocado
        View focusView = null;

        if (TextUtils.isEmpty(Nombre.getText().toString())) {
            Nombre.setError(getString(R.string.error_field_required));
            focusView = Nombre;
            cancel = true;
        }
        if (TextUtils.isEmpty(Descripcion.getText().toString())) {
            Descripcion.setError(getString(R.string.error_field_required));
            focusView = Descripcion;
            cancel = true;
        }
        if (TextUtils.isEmpty(NumDosis.getText().toString())) {
            NumDosis.setError(getString(R.string.error_field_required));
            focusView = NumDosis;
            cancel = true;
        }
        if (TextUtils.isEmpty(Dosis.getSelectedItem().toString())) {
            // Dosis.setError(getString(R.string.error_field_required));
            focusView = Dosis;
            cancel = true;
        }
        if (TextUtils.isEmpty(numToma.getText().toString())) {
            numToma.setError(getString(R.string.error_field_required));
            focusView = numToma;
            cancel = true;
        }
        if (TextUtils.isEmpty(Toma.getSelectedItem().toString())) {
            //   Toma.setError(getString(R.string.error_field_required));
            focusView = Toma;
            cancel = true;
        }
        if (TextUtils.isEmpty(NumDuracion.getText().toString())) {
            NumDuracion.setError(getString(R.string.error_field_required));
            focusView = NumDuracion;
            cancel = true;
        }
        if (TextUtils.isEmpty(Duracion.getSelectedItem().toString())) {
            //Duracion.setError(getString(R.string.error_field_required));
            focusView = Duracion;
            cancel = true;
        }
        if (TextUtils.isEmpty(Fecha.getText().toString())) {
            Fecha.setError(getString(R.string.error_field_required));
            focusView = Fecha;
            cancel = true;
        }
        if (TextUtils.isEmpty(Hora.getText().toString())) {
            Hora.setError(getString(R.string.error_field_required));
            focusView = Hora;
            cancel = true;
        }
        if (cancel) {
            //Enfocar el Campo del Error

            focusView.requestFocus();

        } else {


            medicamento=new Medicamento();

            medicamento.setNombre(Nombre.getText().toString());
            medicamento.setDescripcion(Descripcion.getText().toString());
            medicamento.setNumeroDosis(Float.parseFloat(NumDosis.getText().toString()));
            medicamento.setTipoDosis(Dosis.getSelectedItem().toString());
            medicamento.setPeriodoToma(Integer.parseInt(numToma.getText().toString()));
            medicamento.setTipoPeriodoToma(Toma.getSelectedItem().toString());
            medicamento.setDuracionToma(Integer.parseInt(NumDuracion.getText().toString()));
            medicamento.setTipoDuracion(Duracion.getSelectedItem().toString());
            medicamento.setHoraInicio(Hora.getText().toString());
            medicamento.setFechaInicio(Fecha.getText().toString());
            medicamento.setSwActivo("A");
            medicamento.setSwFinalizado("N");
            SessionObject.getListMedicamentos().add(medicamento);
            Intent intent = new Intent(add_medicines.this, AddTreatement.class);
            startActivity(intent);
        }

    }

    @Override
    public void onClick  (View view) {
        //Get reference of TextView from XML layout

        if (view ==fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if (view == txtTime) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            // mMinute = c.get(Calendar.MINUTE);
            //seg=c.get(Calendar.SECOND);



            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,

                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if(sButton.isChecked()){
            //Do something when switch is on
            txtduracionToma.setVisibility(View.INVISIBLE);
            textViewDuracion.setVisibility(View.INVISIBLE);
            spinnerDuracion.setVisibility(View.INVISIBLE);

        }else{
            //Do something when switch is off
            txtduracionToma.setVisibility(View.VISIBLE);

            textViewDuracion.setVisibility(View.VISIBLE);
            spinnerDuracion.setVisibility(View.VISIBLE);
        }
    }


}
