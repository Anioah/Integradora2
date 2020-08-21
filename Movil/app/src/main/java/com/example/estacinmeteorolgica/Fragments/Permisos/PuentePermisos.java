package com.example.estacinmeteorolgica.Fragments.Permisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.estacinmeteorolgica.Fragments.Login.LoginActivity;
import com.example.estacinmeteorolgica.Fragments.Splash.SplashActivity;
import com.example.estacinmeteorolgica.R;

import java.util.ArrayList;

public class PuentePermisos extends AppCompatActivity {

    String[] listaPermisos = new String[]{
            Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puente_permisos);

        verificarPermisos(listaPermisos);
    }


    public void verificarPermisos (String[] permisos) {


        ArrayList<String> permisosNegados = new ArrayList<String>();

        for (String permiso : permisos) {

            if (ContextCompat.checkSelfPermission(getBaseContext(), permiso) != PackageManager.PERMISSION_GRANTED) {
                permisosNegados.add(permiso);
            }
        }


        if (permisosNegados.size() >= 1) {
            Intent ipp = new Intent(PuentePermisos.this, PermisosActivity.class);
            ipp.putExtra("permisos", permisosNegados);

            for (int x = 0; x < permisosNegados.size(); x++) {
                //  Toast.makeText(this, permisosNegados.get(x), Toast.LENGTH_SHORT).show();
            }

            startActivity(ipp);

        }

        if(permisosNegados.size() == 0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(PuentePermisos.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            },5000);
        }
    }

}