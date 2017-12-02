package edu.unsis.recetario.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import edu.unsis.recetario.R;

/**
 * Created by Octavio on 31/10/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nombePersona;
    TextView nombreMedicamento;
    TextView horario;
    TextView dosis;
    public ViewHolder(View itemView) {
        super(itemView);
        nombePersona=(TextView)itemView.findViewById(R.id.txtNombrePer);
        nombreMedicamento=(TextView)itemView.findViewById(R.id.txtNombreMed);
        horario=(TextView)itemView.findViewById(R.id.txtHorario);
        dosis=(TextView)itemView.findViewById(R.id.txtDosis);


    }

    @Override
    public void onClick(View v) {

    }
}
