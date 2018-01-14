package edu.unsis.recetario.treatements;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.unsis.recetario.R;
import edu.unsis.recetario.home.Inicio;
import edu.unsis.recetario.medicines.add_medicines;
import edu.unsis.recetario.treatements.dao.TratamientoDAOImpl;
import session.SessionObject;

/**
 * Created by Octavio on 31/10/2017.
 */

public class ViewHolderListaTratamiento extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nombeTratamiento;
    public ImageView iconDelete;
    public ViewHolderListaTratamiento(View itemView) {
        super(itemView);
        nombeTratamiento=(TextView)itemView.findViewById(R.id.txtNombreTratamiento);
        iconDelete = (ImageView) itemView.findViewById(R.id.deleteMedicine);
        iconDelete.setOnClickListener(this);
        nombeTratamiento.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TratamientoDAOImpl tratamientoDAO = new TratamientoDAOImpl(v.getContext());
        if (v.getId() == iconDelete.getId()){ //evento onlick del icono delete
            Toast.makeText(v.getContext(), "Tratamiento removido", Toast.LENGTH_LONG).show();
            int idTratamiento = SessionObject.getTratamientos().get(getAdapterPosition()).getIdTratamiento();
            try {
                //con este id eliminar de la base de datos el tratamiento
                tratamientoDAO.deleteTratamiento(idTratamiento);
            }catch (Exception e){
                Log.d("deletetratamiento", e.getCause().getMessage());
            }
            //reload activity
        }else if(v.getId() == nombeTratamiento.getId()){

        }
        Log.d("viewHolder", "Posicion "+getAdapterPosition());
        //Toast.makeText(null, "posicoin "+getAdapterPosition(), Toast.LENGTH_SHORT).show();

    }
}
