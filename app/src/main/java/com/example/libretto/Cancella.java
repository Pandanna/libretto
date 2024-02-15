package com.example.libretto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.libretto.classi.Esame;
import com.example.libretto.classi.Utente;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Cancella extends AppCompatActivity {
    Spinner spinner;
    Button invia;
    int pos;
    Intent i;
    TextView testo_sel_esame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancella);

        spinner = findViewById(R.id.spinner);
        invia = findViewById(R.id.invia);
        testo_sel_esame = findViewById(R.id.testo_sel_esame);

        Utente utente = (Utente) getIntent().getSerializableExtra("utente");

        if(utente.getNumEsami() == 0)
        {
            spinner.setVisibility(View.GONE);
            testo_sel_esame.setText("Nessun esame presente");
            invia.setVisibility(View.GONE);
        }

        ArrayList<String> esami = new ArrayList<String>();
        for (int i = 0; i < utente.getNumEsami(); i++) {
            esami.add(utente.getEsame(i).getNome());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, esami);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pos = 0;
            }
        });

        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utente.rimuoviEsame(pos);

                i = new Intent(Cancella.this, Home.class);
                i.putExtra("utente", utente);
                finish();
                startActivity(i);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_cancella);

        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.nav_cancella)
                return true;

            else if(item.getItemId() == R.id.nav_home)
            {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.putExtra("utente", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_aggiungi)
            {
                Intent intent = new Intent(getApplicationContext(), Aggiungi.class);
                intent.putExtra("utente", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_media)
            {
                Intent intent = new Intent(getApplicationContext(), Media.class);
                intent.putExtra("utente", utente);
                finish();
                startActivity(intent);
                return true;
            }

            return false;
        });
    }
}