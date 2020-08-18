package com.example.estacinmeteorolgica.Fragments.Registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.estacinmeteorolgica.R;

public class RegistroFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        /*btn_login = view.findViewById(R.id.btnLogin);
        edtemail = view.findViewById(R.id.edtUsuario);
        edtpassword = view.findViewById(R.id.edtPassword);

        queue = Volley.newRequestQueue(getContext());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });*/

        return view;
    }
}
