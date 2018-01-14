package edu.unsis.recetario.treatements;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.unsis.recetario.R;
import edu.unsis.recetario.home.DatosInicio;
import edu.unsis.recetario.home.Home;
import edu.unsis.recetario.medicines.add_medicines;
import edu.unsis.recetario.treatements.dao.TratamientoDAOImpl;
import edu.unsis.recetario.treatements.model.Tratamiento;
import session.SessionObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaTratamientos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaTratamientos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaTratamientos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    private OnFragmentInteractionListener mListener;
    public ListaTratamientos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaTratamientos.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaTratamientos newInstance(String param1, String param2) {
        ListaTratamientos fragment = new ListaTratamientos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        /*view.findViewById(R.id.btnAgregarTratamiento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(null, AddTreatement.class);
                startActivity(intent);
            }
        });*/
        /*Button agrgarTratamiento = (Button) view.findViewById(R.id.button);
        agrgarTratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(this, "Algunos campos estan vacíos ", Toast.LENGTH_SHORT).show();

            }
        });*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_lista_tratamientos, container, false);
        TratamientoDAOImpl tratamientoDAO=new TratamientoDAOImpl(null);
        Tratamiento tratamiento=new Tratamiento();
        /**Declarando una lista en donde se guardaran los medicamentos del tratamiento*/
        List<Tratamiento> list= SessionObject.getListTratamientos();
        try {
            /**Obteniendo el total de medicamentos */
            list = tratamientoDAO.getAllTratamiento();
            SessionObject.setTratamientos(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView contendor=(RecyclerView) view.findViewById(R.id.contenedorTratamiento);
        contendor.setHasFixedSize(true);
        LinearLayoutManager Layaut=new LinearLayoutManager(getContext());
        Layaut.setOrientation(LinearLayoutManager.VERTICAL);
        contendor.setAdapter(new AdaptadorListaTratamiento(list));
        contendor.setLayoutManager(Layaut);
        /** creacion de boton Para llamar addTratamiento */

        view.findViewById(R.id.btnAgregarTratamiento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddTreatement.class);
                startActivity(intent);
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
