package com.example.estacinmeteorolgica.Fragments.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.estacinmeteorolgica.Fragments.Registro.RegistroActivity;
import com.example.estacinmeteorolgica.MainActivity;
import com.example.estacinmeteorolgica.R;

public class LoginFragment extends Fragment {

     Button btn_login;
     TextView txtRegister;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

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

        btn_login = view.findViewById(R.id.btnLogin);
        txtRegister = view.findViewById(R.id.txtReg);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
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
}
