package com.example.estacinmeteorolgica.Fragments.MaestroDetalle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.estacinmeteorolgica.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescripcionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescripcionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DescripcionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescripcionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescripcionFragment newInstance(String param1, String param2) {
        DescripcionFragment fragment = new DescripcionFragment();
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

    private TextView tv_fecha, tv_latitud, tv_longitud, tv_temp, tv_hum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_descripcion, container, false);


        tv_fecha = view.findViewById(R.id.txtvw_fecha);
        tv_latitud = view.findViewById(R.id.txtvw_latitud);
        tv_longitud = view.findViewById(R.id.txtvw_longitud);
        tv_temp = view.findViewById(R.id.txtvw_longitud);
        tv_hum = view.findViewById(R.id.txtvw_longitud);

        Bundle objectData = getArguments();
        LectureData data = null;

        if(objectData != null)
        {
            data = (LectureData) objectData.getSerializable("object");
            tv_temp.setText(data.getTemperatura());
            tv_hum.setText(data.getHumedad());
            tv_fecha.setText(data.getTiempo());
            tv_latitud.setText(data.getLatitud());
            tv_longitud.setText(data.getLongitud());
        }

        return view;
    }

}
