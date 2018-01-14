package edu.unsis.recetario.treatements;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.unsis.recetario.R;

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
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == iconDelete.getId()){ //evento onlick del icono delete
            Toast.makeText(v.getContext(), "Tratamiento removido", Toast.LENGTH_LONG).show();
        }
        Log.d("viewHolder", "Posicion "+getAdapterPosition());
        //Toast.makeText(null, "posicoin "+getAdapterPosition(), Toast.LENGTH_SHORT).show();

    }
}
