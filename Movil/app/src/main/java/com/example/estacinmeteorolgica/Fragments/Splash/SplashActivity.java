package com.example.estacinmeteorolgica.Fragments.Splash;

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
import com.example.estacinmeteorolgica.Fragments.Permisos.PermisosActivity;
import com.example.estacinmeteorolgica.Fragments.Permisos.PuentePermisos;
import com.example.estacinmeteorolgica.R;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    String[] listaPermisos = new String[]{
            Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        solicitarPermisos(listaPermisos);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, PuentePermisos.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }

    public void solicitarPermisos(String[] permisos){

        int x = 0;

        for (String permiso : permisos) {
            if (ContextCompat.checkSelfPermission(this, permiso)
                    != PackageManager.PERMISSION_GRANTED) {
                // no tiene el permiso
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        permiso)) {
                    Toast.makeText(this, "Necesita aceptar el permiso", Toast.LENGTH_SHORT).show();
                } else
                    ActivityCompat.requestPermissions(this,
                            new String[]{permiso}, x);
            } else {
                //Toast.makeText(this, "Ya se encuentra activo el permiso", Toast.LENGTH_SHORT).show();
            }

            x ++;
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode < listaPermisos.length){
            for (int x = 0; x < grantResults.length; x++){
                if(grantResults.length > 0 && grantResults[x] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "permiso concedido", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "permiso No concedido", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }




}