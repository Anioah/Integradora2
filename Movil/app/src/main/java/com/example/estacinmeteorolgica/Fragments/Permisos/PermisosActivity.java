package com.example.estacinmeteorolgica.Fragments.Permisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.estacinmeteorolgica.Fragments.Adaptadores.recyclerAdapter;
import com.example.estacinmeteorolgica.Fragments.Login.LoginActivity;
import com.example.estacinmeteorolgica.Fragments.Models.objectPermisos;
import com.example.estacinmeteorolgica.Fragments.Splash.SplashActivity;
import com.example.estacinmeteorolgica.R;

import java.util.ArrayList;

public class PermisosActivity extends AppCompatActivity {

    ArrayList<objectPermisos> permissionsdata;
    RecyclerView permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);


        ArrayList<String> permisos = new ArrayList<String>();

        Bundle bundle = getIntent().getExtras();
        permisos = bundle.getStringArrayList("permisos");

        System.out.println(permisos);

        permissionsdata = new ArrayList<>();
        permissions = (RecyclerView) findViewById(R.id.recyclerPermisos);

        permissions.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter adapt = new recyclerAdapter(permisos);
        permissions.setAdapter(adapt);
    }


}