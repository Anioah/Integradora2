package com.example.estacinmeteorolgica.Fragments.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.estacinmeteorolgica.R;

public class RegistroActivity extends AppCompatActivity {
    RegistroFragment rf = new RegistroFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportFragmentManager().beginTransaction().add(R.id.space_registro, rf).commit();
    }
}