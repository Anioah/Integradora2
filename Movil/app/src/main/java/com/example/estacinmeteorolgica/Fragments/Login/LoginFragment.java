package com.example.estacinmeteorolgica.Fragments.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.estacinmeteorolgica.Fragments.Registro.RegistroActivity;
import com.example.estacinmeteorolgica.MainActivity;
import com.example.estacinmeteorolgica.R;
import com.example.estacinmeteorolgica.Url;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {

     Button btn_login;
     EditText edtxt_user, edtxt_password;
     TextView txtRegister;
     RequestQueue queue;
     Url url;
     String finalUrl = url.getUrl() + "/login";
     SharedPreferences credentials;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btn_login = view.findViewById(R.id.btnLogin);
        edtxt_user = view.findViewById(R.id.edtUsuario);
        edtxt_password = view.findViewById(R.id.edtPassword);
        txtRegister = view.findViewById(R.id.txtReg);

        queue = Volley.newRequestQueue(getContext());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // System.out.println(finalUrl);
                login();
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreg = new Intent(getContext(), RegistroActivity.class);
                startActivity(intentreg);
            }
        });
        return view;
    }

    private void login(){

        boolean filledInputs = false;

        if(!edtxt_user.getText().toString().equals("") && !edtxt_password.getText().toString().equals(""))
        {
            filledInputs = true;
        }else{
            Toast.makeText(getContext(),"Llena los campos correctamente", Toast.LENGTH_SHORT).show();
        }

        if(filledInputs){

            final String user = edtxt_user.getText().toString();
            final String password = edtxt_password.getText().toString();


            JSONObject data = new JSONObject();
            try {
                data.put("email", user);
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
                            try {
                                credentials = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = credentials.edit();

                                editor.putString("token", response.getString("token"));
                                editor.apply();

                                pass();

                            }
                            catch (JSONException ex) {
                                ex.printStackTrace();
                            }
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

        credentials = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        System.out.println(credentials.getString("token", null));
        if(credentials.getString("token",null) != null) {
            //Toast.makeText(getContext(), credentials.getString("token",null), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }


}
