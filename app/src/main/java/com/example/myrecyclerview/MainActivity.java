package com.example.myrecyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Equipo> equipos;
    private String[] descripcion;
    private AlertDialog.Builder dialogBuilder;

    private void inicializarDatos() {
        equipos = new ArrayList<>();
        equipos.add(new Equipo("Ajax", "Holanda", R.drawable.ajax,"4 Titulos"));
        equipos.add(new Equipo("Barcelona", "España", R.drawable.barca, "5 Titulos"));
        equipos.add(new Equipo("Bayern Munich", "Alemania", R.drawable.bayern,"6 Titulos"));
        equipos.add(new Equipo("Inter Milan", "Italia", R.drawable.inter, "3 Titulos"));
        equipos.add(new Equipo("Juventus", "Italia", R.drawable.juventus,"2 Titulos"));
        equipos.add(new Equipo("Liverpool", "Inglaterra", R.drawable.liverpool,"6 Titulos"));
        equipos.add(new Equipo("Manchester United", "Inglaterra", R.drawable.manu,"3 Titulos"));
        equipos.add(new Equipo("Real Madrid", "España", R.drawable.realmadrid,"13 Titulos"));
        descripcion = getResources().getStringArray(R.array.descripcion);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        inicializarDatos();

        RVAdapter rvAdapter = new RVAdapter(equipos,getApplicationContext());
        recyclerView.setAdapter(rvAdapter);
    }
}