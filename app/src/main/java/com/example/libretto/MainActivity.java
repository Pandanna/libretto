package com.example.libretto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.libretto.classi.Utente;
import com.example.libretto.eccezioni.InvalidParameterException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nomeutente, password;
    Button procedi, bottone_registrazione;
    TextView errore_nomeutente, errore_password, errore_generale;
    Intent home, registra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeutente = findViewById(R.id.nomeutente);
        password = findViewById(R.id.password);
        procedi = findViewById(R.id.procedi);
        bottone_registrazione = findViewById(R.id.bottone_registrazione);
        errore_nomeutente = findViewById(R.id.errore_nomeutente);
        errore_password = findViewById(R.id.errore_password);
        errore_generale = findViewById(R.id.errore_generale);

        Utente chiara = new Utente("Panda", "Anna Chiara", "Mameli", new Date(2002, 7, 27), "panda");

        bottone_registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registra = new Intent(MainActivity.this, Registrazione.class);
                finish();
                startActivity(registra);
            }
        });

        procedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errore_nomeutente.setVisibility(View.INVISIBLE);
                errore_password.setVisibility(View.INVISIBLE);
                errore_generale.setVisibility(View.INVISIBLE);

                List<String> errorMessages = new ArrayList<String>();

                try {
                    InvalidParameterException.controllaLunghezzaStringa(nomeutente.getText().toString(), "Nome utente");
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                try {
                    InvalidParameterException.controllaLunghezzaStringa(password.getText().toString(), "Password");
                } catch (InvalidParameterException e) {
                    errorMessages.add(e.getMess());
                }

                if(!nomeutente.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
                {
                    if(!nomeutente.getText().toString().equals(chiara.getNomeutente()) && !password.getText().toString().equals(chiara.getPassword()))
                    {
                        errorMessages.add("Generale");
                    }
                }

                // Mostra tutti i messaggi di errore, se presenti
                for (String errorMessage : errorMessages) {
                    if (errorMessage.contains("Nome utente")) {
                        errore_nomeutente.setVisibility(View.VISIBLE);
                        errore_nomeutente.setText(errorMessage);
                    } else if (errorMessage.contains("Password")) {
                        errore_password.setVisibility(View.VISIBLE);
                        errore_password.setText(errorMessage);
                    } else if(errorMessage.contains("Generale")) {
                        errore_generale.setVisibility(View.VISIBLE);
                        errore_generale.setText("Nome utente o password sbagliati");
                    }
                }

                // Se non ci sono errori, procedi con la creazione dell'utente
                if (errorMessages.isEmpty()) {
                    Intent i = new Intent(MainActivity.this, Home.class);
                    i.putExtra("utente", chiara);
                    finish();
                    startActivity(i);
                }
            }
        });
    }
}
