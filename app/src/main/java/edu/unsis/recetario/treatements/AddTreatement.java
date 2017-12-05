package edu.unsis.recetario.treatements;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import edu.unsis.recetario.R;
import edu.unsis.recetario.home.AdaptadorInicio;
import edu.unsis.recetario.home.Home;
import edu.unsis.recetario.medicines.add_medicines;
import edu.unsis.recetario.medicines.model.Medicamento;
import session.SessionObject;

public class AddTreatement extends AppCompatActivity {

    public View view;
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
                //saveUser();
                return true;
            case android.R.id.home:
                Intent intent = new Intent(AddTreatement.this, Home.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
