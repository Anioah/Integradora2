package com.example.estacinmeteorolgica.Fragments.MaestroDetalle;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.estacinmeteorolgica.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    public String getdata_url = "http://100.26.102.200/api/showMysql";
    private RequestQueue queue = null;
    private JsonObjectRequest request;
    private RecyclerView geolocalization_historial;
    private ArrayList<LectureData> historial;

    private Activity activity;
    private Detalle detalle;

    SharedPreferences credentials;
    String userToken;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerFragment newInstance(String param1, String param2) {
        RecyclerFragment fragment = new RecyclerFragment();
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
        final View vista = inflater.inflate(R.layout.fragment_recycler, container, false);

        geolocalization_historial = (RecyclerView) vista.findViewById(R.id.geolocalization_historial);
        geolocalization_historial.setLayoutManager(new LinearLayoutManager(getContext()));
        geolocalization_historial.setHasFixedSize(true);

        queue = Volley.newRequestQueue(getContext());

        historial = new ArrayList<>();
        final recyclerViewAdapter adapt = new recyclerViewAdapter(historial);

        credentials = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        //System.out.println(credentials.getString("token", null));
        userToken = credentials.getString("token",null);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                getdata_url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());
                        System.out.println(response);

                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject jsonObject = response.getJSONObject(i);
                                LectureData datos = new LectureData();
                                //  if(jsonObject.getString("nombre").equals(nombre)) {
                                datos.setTemperatura(jsonObject.getString("temperatura"));
                                datos.setHumedad(jsonObject.getString("humedad"));
                                datos.setLatitud(jsonObject.getString("latitud"));
                                datos.setLongitud(jsonObject.getString("longitud"));
                                datos.setTiempo(jsonObject.getString("created_at"));

                                historial.add(datos);

                                geolocalization_historial.setAdapter(adapt);
                                queue.start();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred


                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() {
                String token = "Bearer " + userToken;
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                params.put("Authorization", token);
                params.put("Content-Type", "application/json");
                return params;
            }};

        // Add JsonArrayRequest to the RequestQueue
        queue.add(jsonArrayRequest);


        /*request = new JsonObjectRequest(Request.Method.GET, getdata_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    System.out.println(jsonArray);

                    for (int i = 0; i < jsonArray.length(); i ++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        LectureData datos = new LectureData();

                        //  if(jsonObject.getString("nombre").equals(nombre)) {
                        datos.setTemperatura(jsonObject.getString("temperatura"));
                        datos.setHumedad(jsonObject.getString("humedad"));
                        datos.setLatitud(jsonObject.getString("latitud"));
                        datos.setLongitud(jsonObject.getString("longitud"));
                        datos.setTiempo(jsonObject.getString("fecha"));

                        historial.add(datos);

                        geolocalization_historial.setAdapter(adapt);
                        queue.start();
                        // }
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }){
            @Override
        public Map<String, String> getHeaders() {
            String token = "Bearer " + userToken;
            Map<String, String> params = new HashMap<>();
            params.put("Accept", "application/json");
            params.put("Authorization", token);
            params.put("Content-Type", "application/json");
            return params;
        }};
        queue.add(request);
*/
        adapt.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historial.get(geolocalization_historial.getChildAdapterPosition(v)).getTemperatura();
                detalle.sendDetalle(historial.get(geolocalization_historial.getChildAdapterPosition(v)));
            }
        });
        return vista;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof Activity){

            this.activity = (Activity) context;
            this.detalle = (Detalle) this.activity;

        }
    }
}