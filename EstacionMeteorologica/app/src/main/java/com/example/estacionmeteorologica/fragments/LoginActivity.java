package com.example.estacionmeteorologica.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.estacionmeteorologica.R;

public class LoginActivity extends AppCompatActivity {

    LoginFragment lf = new LoginFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().add(R.id.space_login, lf).commit();
    }
}