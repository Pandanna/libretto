package com.example.libretto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.libretto.classi.AdapterEsami;
import com.example.libretto.classi.Esame;
import com.example.libretto.classi.Utente;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    ListView lista_esami;
    TextView nome_cognome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Utente utente = (Utente) getIntent().getSerializableExtra("utente");

        lista_esami = findViewById(R.id.lista_esami);
        nome_cognome = findViewById(R.id.nome_cognome);

        nome_cognome.setText("Ciao " + utente.getNome() + " " + utente.getCognome());

        AdapterEsami adapter = new AdapterEsami(getApplicationContext(), utente.getEsami());
        lista_esami.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.nav_home)
                return true;

            else if(item.getItemId() == R.id.nav_aggiungi)
            {
                Intent intent = new Intent(getApplicationContext(), Aggiungi.class);
                intent.putExtra("utente", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_cancella)
            {
                Intent intent = new Intent(getApplicationContext(), Cancella.class);
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