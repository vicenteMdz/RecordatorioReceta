package edu.unsis.recetario.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.unsis.recetario.R;

/**
 * Created by Octavio on 31/10/2017.
 */

public class AdaptadorInicio extends RecyclerView.Adapter<ViewHolder> {
    List<DatosInicio> listaObajeto;

    public AdaptadorInicio(List<DatosInicio> listaObajeto) {
        this.listaObajeto = listaObajeto;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_inicio,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombePersona.setText(listaObajeto.get(position).getNombrePersona());
        holder.nombreMedicamento.setText(listaObajeto.get(position).getNombreMedicamento());
        holder.dosis.setText(listaObajeto.get(position).getDosis());
        holder.horario.setText(listaObajeto.get(position).getFechaActual());
    }

    @Override
    public int getItemCount() {
        return listaObajeto.size();
    }


}
