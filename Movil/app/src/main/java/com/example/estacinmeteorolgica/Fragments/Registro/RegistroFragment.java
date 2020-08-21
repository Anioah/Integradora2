package com.example.estacinmeteorolgica.Fragments.Registro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.estacinmeteorolgica.Fragments.Login.LoginActivity;
import com.example.estacinmeteorolgica.Fragments.Login.LoginFragment;
import com.example.estacinmeteorolgica.MainActivity;
import com.example.estacinmeteorolgica.R;
import com.example.estacinmeteorolgica.Url;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroFragment extends Fragment {


    private EditText edtxt_nombre, edtxt_email, edtxt_password;
    private Button btn_registro;
    Url url;
    String finalUrl = url.getUrl() + "/register";
    RequestQueue queue;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        btn_registro = view.findViewById(R.id.btnRegistro);
        edtxt_nombre = view.findViewById(R.id.edtNomUsuario);
        edtxt_email = view.findViewById(R.id.edtcorreo);
        edtxt_password = view.findViewById(R.id.edt_Password);

        queue = Volley.newRequestQueue(getContext());

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        return view;
    }

    private void registrar(){

        boolean filledInputs = false;

        if(!edtxt_nombre.getText().toString().equals("") && !edtxt_email.getText().toString().equals("") && !edtxt_password.getText().toString().equals(""))
        {
            filledInputs = true;
        }else{
            Toast.makeText(getContext(),"Llena los campos correctamente", Toast.LENGTH_SHORT).show();
        }

        if(filledInputs){

            final String nombre = edtxt_nombre.getText().toString();
            final String email = edtxt_email.getText().toString();
            final String password = edtxt_password.getText().toString();


            JSONObject data = new JSONObject();
            try {
                data.put("username", nombre);
                data.put("email", email);
                data.put("password", password);
            }catch (JSONException e){
                e.printStackTrace();
            }

            System.out.println(data);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    finalUrl,
                    data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getContext(),"Usuario Creado correctamente", Toast.LENGTH_SHORT).show();
                            pass();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(error);
                            Toast.makeText(getContext(),"Verifica los datos e intenta de nuevo",Toast.LENGTH_SHORT).show();
                        }
                    });

            queue.add(jsonObjectRequest);

        } // if (filledInputs) END
    }

    private void pass(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}


