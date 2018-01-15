package edu.unsis.recetario.treatements;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.unsis.recetario.R;
import edu.unsis.recetario.medicines.dao.MedicineDAOImpl;
import edu.unsis.recetario.treatements.dao.TratamientoDAOImpl;
import edu.unsis.recetario.treatements.model.Tratamiento;
import session.SessionObject;

/**
 * Created by Octavio on 31/10/2017.
 */

public class AdaptadorListaTratamiento extends RecyclerView.Adapter<AdaptadorListaTratamiento.ViewHolderListaTratamiento> {

    List<Tratamiento> listaTratamiento;

    public class ViewHolderListaTratamiento extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombeTratamiento;
        public ImageView iconDelete;
        private ArrayList<Tratamiento> listTratamiento;

        public ViewHolderListaTratamiento(View itemView) {
            super(itemView);
            nombeTratamiento = (TextView) itemView.findViewById(R.id.txtNombreTratamiento);
            iconDelete = (ImageView) itemView.findViewById(R.id.deleteMedicine);
            iconDelete.setOnClickListener(this);
            nombeTratamiento.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TratamientoDAOImpl tratamientoDAO = new TratamientoDAOImpl(v.getContext());
            MedicineDAOImpl medicineDAO = new MedicineDAOImpl(v.getContext());
            Tratamiento tratamiento = new Tratamiento();

            int idTratamiento = -1;
            idTratamiento = SessionObject.getTratamientos().get(getAdapterPosition()).getIdTratamiento();
            if (v.getId() == iconDelete.getId()) { //evento onlick del icono delete
                try {
                    //Eliminar medicamentos relacionados con el tratamiento
                    medicineDAO.deleteMedicineByTratamiento(idTratamiento);
                    //borramos el tratamiento
                    tratamientoDAO.deleteTratamiento(idTratamiento);
                    SessionObject.deleteTratamiento(getAdapterPosition());// borramos el tratamiento de la lista
                    Toast.makeText(v.getContext(), "Tratamiento removido", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                notifyItemRemoved(getAdapterPosition());//reload pantalla
            } else if (v.getId() == nombeTratamiento.getId()) {
                Log.d("viewHolder", "Posicion " + idTratamiento);

                Log.d("itemSelected TratamientoOOOOOOOOOOS::: ", "id" + idTratamiento);

                Intent intent = new Intent(v.getContext(), AddTreatement.class);//creamos el nuevo intent
                intent.putExtra("TratamientoEditar", idTratamiento);//guardamos el tratamiento para mostrarlo en la siguiente ventana
                v.getContext().startActivity(intent);//lanzamos el activity
            }
            Log.d("viewHolder", "Posicion " + getAdapterPosition());
            //Toast.makeText(null, "posicoin "+getAdapterPosition(), Toast.LENGTH_SHORT).show();

        }


    }

    public AdaptadorListaTratamiento(List<Tratamiento> listaTratamiento) {
        this.listaTratamiento = listaTratamiento;
    }


    @Override
    public ViewHolderListaTratamiento onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_listtratamiento,parent,false);
        return new ViewHolderListaTratamiento(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolderListaTratamiento holder, int position) {
        holder.nombeTratamiento.setText(listaTratamiento.get(position).getNombreTratamiento());
    }

    @Override
    public int getItemCount() {
        return listaTratamiento.size();
    }

    public void reloadListTratamiento(int index){
        notifyItemRemoved(index);
    }
}
