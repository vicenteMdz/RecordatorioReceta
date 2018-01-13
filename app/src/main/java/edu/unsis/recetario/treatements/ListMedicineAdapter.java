package edu.unsis.recetario.treatements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import edu.unsis.recetario.medicines.add_medicines;
import edu.unsis.recetario.medicines.model.Medicamento;
import session.SessionObject;

/**
 * Created by Meltsan on 04/12/17.
 */
public class ListMedicineAdapter extends RecyclerView.Adapter<ListMedicineAdapter.ViewHolderMedicina>  {

    private ArrayList<Medicamento> listMedicamentos;

    public class ViewHolderMedicina extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView itemMedicineName;
        public ImageView iconDelete;

        public ViewHolderMedicina(View itemView) {
            super(itemView);
            itemMedicineName = (TextView) itemView.findViewById(R.id.itemMedicine);
            iconDelete = (ImageView) itemView.findViewById(R.id.deleteMedicine);
            iconDelete.setOnClickListener(this);
            itemMedicineName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == iconDelete.getId()){ //evento onlick del icono delete
                Toast.makeText(v.getContext(), "Medicamento borrado del tratamiento", Toast.LENGTH_LONG).show();
                SessionObject.deleteMedicine(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            } else if (v.getId() == itemMedicineName.getId()){ // evento onclick del textview
                Log.d("itemSelected::: ", listMedicamentos.get(getAdapterPosition()).toString());
                Intent intent = new Intent(v.getContext(), add_medicines.class);
                intent.putExtra("medicamento",listMedicamentos.get(getAdapterPosition()));
                v.getContext().startActivity(intent);
            }
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
        holder.itemMedicineName.setText(listMedicamentos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listMedicamentos.size();
    }

}
