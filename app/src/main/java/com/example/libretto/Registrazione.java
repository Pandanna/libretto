package com.example.libretto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.libretto.classi.Utente;
import com.example.libretto.eccezioni.InvalidParameterException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Registrazione extends AppCompatActivity {
    EditText nomeutente, nome, cognome, password;
    TextView errore_nomeutente, errore_nome, errore_cognome, errore_datanascita, errore_password;
    TextView data_nascita; // Cambiato EditText a TextView
    Button conferma;
    Calendar calendar;
    ImageButton indietro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        // Inizializzazione dei campi e dei TextView
        nomeutente = findViewById(R.id.nomeutente);
        nome = findViewById(R.id.nome);
        cognome = findViewById(R.id.cognome);
        data_nascita = findViewById(R.id.data_nascita); // Cambiato EditText a TextView
        password = findViewById(R.id.password);
        errore_nomeutente = findViewById(R.id.errore_nomeutente);
        errore_nome = findViewById(R.id.errore_nome);
        errore_cognome = findViewById(R.id.errore_cognome);
        errore_datanascita = findViewById(R.id.errore_datanascita);
        errore_password = findViewById(R.id.errore_password);
        conferma = findViewById(R.id.conferma);
        indietro = findViewById(R.id.indietro);

        // Inizializzazione del calendario
        calendar = Calendar.getInstance();

        // Impostazione del click listener per la data di nascita
        data_nascita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ottieni l'anno, mese e giorno correnti dal calendario
                int anno = calendar.get(Calendar.YEAR);
                int mese = calendar.get(Calendar.MONTH);
                int giorno = calendar.get(Calendar.DAY_OF_MONTH);

                // Crea un DatePickerDialog e mostra la finestra di dialogo
                DatePickerDialog datePickerDialog = new DatePickerDialog(Registrazione.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Imposta la data selezionata nel campo di testo
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                data_nascita.setText(sdf.format(calendar.getTime()));
                            }
                        }, anno, mese, giorno);
                datePickerDialog.show();
            }
        });

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registrazione.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });

        // Impostazione del click listener per il pulsante di conferma
        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Nascondi i messaggi di errore precedenti
                errore_nomeutente.setVisibility(View.INVISIBLE);
                errore_nome.setVisibility(View.INVISIBLE);
                errore_cognome.setVisibility(View.INVISIBLE);
                errore_datanascita.setVisibility(View.INVISIBLE);
                errore_password.setVisibility(View.INVISIBLE);

                // Lista per memorizzare tutti i messaggi di errore
                List<String> errorMessages = new ArrayList<>();

                // Validazione di ogni campo e raccolta degli errori
                try {
                    InvalidParameterException.controllaLunghezzaStringa(nomeutente.getText().toString(), "Nome utente");
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                try {
                    InvalidParameterException.controllaLunghezzaStringa(nome.getText().toString(), "Nome");
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                try {
                    InvalidParameterException.controllaLunghezzaStringa(cognome.getText().toString(), "Cognome");
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                // Controllo sulla data di nascita
                try {
                    InvalidParameterException.controllaData(calendar.getTime());
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                try {
                    InvalidParameterException.controllaLunghezzaStringa(password.getText().toString(), "Password");
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                // Mostra tutti i messaggi di errore, se presenti
                for (String errorMessage : errorMessages) {
                    if (errorMessage.contains("Nome utente")) {
                        errore_nomeutente.setVisibility(View.VISIBLE);
                        errore_nomeutente.setText(errorMessage);
                    } else if (errorMessage.contains("Nome")) {
                        errore_nome.setVisibility(View.VISIBLE);
                        errore_nome.setText(errorMessage);
                    } else if (errorMessage.contains("Cognome")) {
                        errore_cognome.setVisibility(View.VISIBLE);
                        errore_cognome.setText(errorMessage);
                    } else if (errorMessage.contains("Data")) {
                        errore_datanascita.setVisibility(View.VISIBLE);
                        errore_datanascita.setText(errorMessage);
                    } else if (errorMessage.contains("Password")) {
                        errore_password.setVisibility(View.VISIBLE);
                        errore_password.setText(errorMessage);
                    }
                }

                // Se non ci sono errori, procedi con la creazione dell'utente
                if (errorMessages.isEmpty()) {
                    Utente utente = new Utente(nomeutente.getText().toString(), nome.getText().toString(), cognome.getText().toString(), calendar.getTime(), password.getText().toString());
                    Intent i = new Intent(Registrazione.this, Home.class);
                    i.putExtra("utente", utente);
                    finish();
                    startActivity(i);
                }
            }
        });
    }
}
