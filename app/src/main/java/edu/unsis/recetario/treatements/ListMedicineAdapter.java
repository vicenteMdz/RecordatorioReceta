package edu.unsis.recetario.treatements;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.unsis.recetario.R;
import edu.unsis.recetario.medicines.model.Medicamento;
import session.SessionObject;

/**
 * Created by Meltsan on 04/12/17.
 */
public class ListMedicineAdapter extends RecyclerView.Adapter<ListMedicineAdapter.ViewHolderMedicina>  {

    private ArrayList<Medicamento> listMedicamentos;

    public class ViewHolderMedicina extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtNameMedicine;
        public ImageView iconDelete;

        public ViewHolderMedicina(View itemView) {
            super(itemView);
            txtNameMedicine = (TextView) itemView.findViewById(R.id.txtNameMedicine);
            iconDelete = (ImageView) itemView.findViewById(R.id.deleteMedicine);
            iconDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == iconDelete.getId()){ //evento onlick del icono delete
                Toast.makeText(v.getContext(), "Medicamento removido del tratamiento exitosamente", Toast.LENGTH_LONG).show();
            } else { // evento onclick del textview
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
            SessionObject.deleteMedicine(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }
    }

    public ListMedicineAdapter(ArrayList<Medicamento> listMedicamentos) {
        this.listMedicamentos = listMedicamentos;
    }

    @Override
    public ViewHolderMedicina onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("viewHolder", "onCreateViewHolder method");
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine,parent,false);
        return new ViewHolderMedicina(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolderMedicina holder, int position) {
        Log.d("setText", "onBindViewHolder method");
        holder.txtNameMedicine.setText(listMedicamentos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listMedicamentos.size();
    }

}
