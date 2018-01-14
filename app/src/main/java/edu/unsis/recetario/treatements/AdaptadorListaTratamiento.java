package edu.unsis.recetario.treatements;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import edu.unsis.recetario.R;
import edu.unsis.recetario.home.ViewHolder;
import edu.unsis.recetario.treatements.ViewHolderListaTratamiento;

/**
 * Created by Octavio on 31/10/2017.
 */

public class AdaptadorListaTratamiento extends RecyclerView.Adapter<ViewHolderListaTratamiento> {

    List<DatosListaTratamientos> listaObajeto;

    public AdaptadorListaTratamiento(List<DatosListaTratamientos> listaObajeto) {
        this.listaObajeto = listaObajeto;
    }


    @Override
    public ViewHolderListaTratamiento onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_listtratamiento,parent,false);
        return new ViewHolderListaTratamiento(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolderListaTratamiento holder, int position) {
        holder.nombeTratamiento.setText(listaObajeto.get(position).getNombreTratamiento());
    }

    @Override
    public int getItemCount() {
        return listaObajeto.size();
    }


}
