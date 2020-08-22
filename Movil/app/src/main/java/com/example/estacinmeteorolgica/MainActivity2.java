package com.example.estacinmeteorolgica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.estacinmeteorolgica.Fragments.MaestroDetalle.DescripcionFragment;
import com.example.estacinmeteorolgica.Fragments.MaestroDetalle.Detalle;
import com.example.estacinmeteorolgica.Fragments.MaestroDetalle.LectureData;
import com.example.estacinmeteorolgica.Fragments.MaestroDetalle.RecyclerFragment;

public class MainActivity2 extends AppCompatActivity implements Detalle {

    RecyclerFragment recyclerFragment;
    DescripcionFragment descriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerFragment recyclerFragment= new RecyclerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.recyclerxDDD, recyclerFragment,null);
        fragmentTransaction.commit();
    }

    @Override
    public void sendDetalle(LectureData data) {
        descriptionFragment = new DescripcionFragment();
        Bundle bundleSerializable = new Bundle();
        bundleSerializable.putSerializable("object", data);
        descriptionFragment.setArguments(bundleSerializable);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.RecyclerLayout,descriptionFragment).addToBackStack(null)
                .commit();
    }
}