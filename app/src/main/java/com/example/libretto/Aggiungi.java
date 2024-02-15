package com.example.libretto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.libretto.classi.Esame;
import com.example.libretto.classi.Utente;
import com.example.libretto.eccezioni.InvalidParameterException;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Aggiungi extends AppCompatActivity {
    EditText nome_esame, voto_esame, cfu_esame;
    TextView errore_nomeesame, errore_votoesame, errore_cfuesame;
    Button inserisci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi);

        nome_esame = findViewById(R.id.nome_esame);
        voto_esame = findViewById(R.id.voto_esame);
        cfu_esame = findViewById(R.id.cfu_esame);
        inserisci = findViewById(R.id.inserisci);
        errore_nomeesame = findViewById(R.id.errore_nomeesame);
        errore_votoesame = findViewById(R.id.errore_votoesame);
        errore_cfuesame = findViewById(R.id.errore_cfuesame);

        Utente utente = (Utente) getIntent().getSerializableExtra("utente");

        inserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inizializza una lista per mantenere i messaggi di errore
                List<String> errorMessages = new ArrayList<>();

                try {
                    InvalidParameterException.controllaLunghezzaStringa(nome_esame.getText().toString(), "Nome Esame");
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                try {
                    InvalidParameterException.controllaLunghezzaStringa(voto_esame.getText().toString(), "Voto");
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                try {
                    InvalidParameterException.controllaLunghezzaStringa(cfu_esame.getText().toString(), "Crediti");
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                try {
                    InvalidParameterException.controllaVoto(Integer.parseInt(voto_esame.getText().toString()));
                } catch (NumberFormatException | InvalidParameterException e) {
                    errorMessages.add(e.getMessage());
                }

                try {
                    InvalidParameterException.controllaCrediti(Integer.parseInt(cfu_esame.getText().toString()));
                } catch (NumberFormatException | InvalidParameterException e) {
                    errorMessages.add(e.getMessage());
                }

                // Mostra tutti i messaggi di errore, se presenti
                if (!errorMessages.isEmpty()) {
                    // Nascondi tutti i messaggi di errore
                    errore_nomeesame.setVisibility(View.INVISIBLE);
                    errore_votoesame.setVisibility(View.INVISIBLE);
                    errore_cfuesame.setVisibility(View.INVISIBLE);

                    // Mostra gli errori corrispondenti
                    for (String errorMessage : errorMessages) {
                        if (errorMessage.contains("Nome")) {
                            errore_nomeesame.setVisibility(View.VISIBLE);
                            errore_nomeesame.setText(errorMessage);
                        } else if (errorMessage.contains("Voto")) {
                            errore_votoesame.setVisibility(View.VISIBLE);
                            errore_votoesame.setText(errorMessage);
                        } else if (errorMessage.contains("Crediti")) {
                            errore_cfuesame.setVisibility(View.VISIBLE);
                            errore_cfuesame.setText(errorMessage);
                        }
                    }
                } else {
                    // Se non ci sono errori, aggiungi l'esame e passa alla Home
                    utente.aggiungiEsame(new Esame(nome_esame.getText().toString(), Integer.parseInt(cfu_esame.getText().toString()), Integer.parseInt(voto_esame.getText().toString())));
                    Intent i = new Intent(Aggiungi.this, Home.class);
                    i.putExtra("utente", utente);
                    finish();
                    startActivity(i);
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_aggiungi);

        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.nav_aggiungi)
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
