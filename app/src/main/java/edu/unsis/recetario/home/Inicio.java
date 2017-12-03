package edu.unsis.recetario.home;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.unsis.recetario.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Inicio.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Inicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inicio extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    private OnFragmentInteractionListener mListener;

    public Inicio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Inicio.
     */
    // TODO: Rename and change types and number of parameters
    public static Inicio newInstance(String param1, String param2) {
        Inicio fragment = new Inicio();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_inicio, container, false);


        TextView nombePersona;
        TextView fecha;
        String dt;

        Date cal = (Date) Calendar.getInstance().getTime();
        dt = cal.toLocaleString();

        nombePersona=(TextView)view.findViewById(R.id.txtnombrePersona);
        nombePersona.setText("Octavio");

        fecha=(TextView)view.findViewById(R.id.txtFechaActual);


        //Obteniendo la fecha actual
        Date dNow = new Date();
        SimpleDateFormat diaN = new SimpleDateFormat ("dd");
        String diaNum = diaN.format(dNow.getTime());

        SimpleDateFormat diaL = new SimpleDateFormat("EEEE");

        String dayOfTheWeek = diaL.format(dNow);
        SimpleDateFormat mes = new SimpleDateFormat("MMMM");
        String mesA = mes.format(dNow);

        fecha.setText(dayOfTheWeek+" "+diaNum+" de "+mesA);


        ArrayList<DatosInicio> list=new ArrayList<>();
        list.add(new DatosInicio("","paracetamol","8pm","2 tabletas"));

        list.add(new DatosInicio("","paracetamol","12 am","media tableta"));
        list.add(new DatosInicio("","paracetamol"," 4 am","1 tableta"));
        list.add(new DatosInicio("","paracetamol"," 10 pm","muchas"));
        list.add(new DatosInicio("","paracetamol"," 10 pm","muchas"));

        RecyclerView contendor=(RecyclerView) view.findViewById(R.id.contenedor);
        contendor.setHasFixedSize(true);
        LinearLayoutManager Layaut=new LinearLayoutManager(getContext());
        Layaut.setOrientation(LinearLayoutManager.VERTICAL);
        contendor.setAdapter(new AdaptadorInicio(list));
        contendor.setLayoutManager(Layaut);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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