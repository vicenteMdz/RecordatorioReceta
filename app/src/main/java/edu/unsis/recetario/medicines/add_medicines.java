package edu.unsis.recetario.medicines;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import edu.unsis.recetario.R;
import edu.unsis.recetario.home.Home;
import edu.unsis.recetario.treatements.AddTreatement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
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

public class add_medicines extends AppCompatActivity implements OnClickListener {

    //UI References
    private EditText fromDateEtxt;
    private EditText txtTime,duracionToma;
    private TextView textViewDuracion;
    private Spinner spinnerDuracion;
    private Button AddMedicines;
    private   Switch sButton;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicines);

        Toolbar toolbar = (Toolbar) findViewById(R.id.headerActivityAddMedicines);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Añadir Medicamento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.window_close);

        //Get widgets reference from XML layout
        duracionToma = (EditText) findViewById(R.id.edtduracionToma);
        textViewDuracion = (TextView) findViewById(R.id.textVliew18);
        spinnerDuracion  = (Spinner) findViewById(R.id.spinneer3);
        sButton = (Switch) findViewById(R.id.switch1);
        sButton.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        txtTime = (EditText) findViewById(R.id.editText12);
        txtTime.setOnClickListener(this);
        findViewsById();
        setDateTimeField();

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
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.saveMedicine:
                //saveMedicine();
                return true;
            case android.R.id.home:
                Intent intent = new Intent(add_medicines.this, AddTreatement.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.editText9);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }
    public void onClic(View view) {

    }
    @Override
    public void onClick(View view) {
        //Get reference of TextView from XML layout



        if (view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if (view == txtTime) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

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
            duracionToma.setVisibility(View.INVISIBLE);
            duracionToma.setText("Continuo");
            textViewDuracion.setVisibility(View.INVISIBLE);
            spinnerDuracion.setVisibility(View.INVISIBLE);

        }else{
            //Do something when switch is off
            duracionToma.setVisibility(View.VISIBLE);
            duracionToma.setText(" ");

            textViewDuracion.setVisibility(View.VISIBLE);
            spinnerDuracion.setVisibility(View.VISIBLE);
        }


        }


}
