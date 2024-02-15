package com.example.libretto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.libretto.classi.Utente;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Media extends AppCompatActivity {
    TextView media_arit, media_pond, voto_laurea;
    float media_a = 0, media_p = 0, voto_l;
    int cfus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        media_arit = findViewById(R.id.media_arit);
        media_pond = findViewById(R.id.media_pond);
        voto_laurea = findViewById(R.id.voto_laurea);

        Utente utente = (Utente) getIntent().getSerializableExtra("utente");

        for(int i = 0; i < utente.getNumEsami(); i++)
        {
            media_a += utente.getEsame(i).getVoto();
        }
        media_a /= utente.getNumEsami();
        media_arit.setText(media_a + "");

        for(int i = 0; i < utente.getNumEsami(); i++)
        {
            media_p += utente.getEsame(i).getVoto() * utente.getEsame(i).getCFU();
            cfus += utente.getEsame(i).getCFU();
        }
        media_p /= cfus;
        media_pond.setText(media_p + "");

        voto_l = media_p * 110 / 30;
        voto_laurea.setText(voto_l + "");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_media);

        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.nav_media)
                return true;

            else if(item.getItemId() == R.id.nav_home)
            {
                Intent intent = new Intent(getApplicationContext(), Home.class);
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

            else if(item.getItemId() == R.id.nav_aggiungi)
            {
                Intent intent = new Intent(getApplicationContext(), Aggiungi.class);
                intent.putExtra("utente", utente);
                finish();
                startActivity(intent);
                return true;
            }

            return false;
        });
    }
}