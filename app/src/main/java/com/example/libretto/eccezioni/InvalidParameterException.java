package com.example.libretto.eccezioni;

import androidx.annotation.NonNull;
import java.util.Date;

public class InvalidParameterException extends Exception {

    private final String messaggio;
    private final String tipo;

    public InvalidParameterException(String messaggio, String tipo) {
        super(messaggio);
        this.messaggio = messaggio;
        this.tipo = tipo;
    }

    public String getMess() {
        return messaggio;
    }

    public String getTipo() {
        return tipo;
    }

    public static void controllaLunghezzaStringa(@NonNull String stringa, String campo) throws InvalidParameterException {
        if (stringa.isEmpty()) {
            throw new InvalidParameterException("Il campo " + campo + " non può essere vuoto", campo);
        }
    }

    public static void controllaData(@NonNull Date data) throws InvalidParameterException {
        int anno = data.getYear() + 1900;
        int mese = data.getMonth() + 1;
        int giorno = data.getDate();

        boolean erroreAnno = anno < 1950 || anno > 2019;
        boolean erroreMese = mese < 1 || mese > 12;
        boolean erroreGiorno = giorno < 1 || giorno > 31;

        if (erroreAnno || erroreMese || erroreGiorno) {
            throw new InvalidParameterException("Data non valida ", "Data");
        }
    }

    public static void controllaVoto(int voto) throws InvalidParameterException {

        if (voto < 18 || voto > 31 ) {
            throw new InvalidParameterException("Il Voto deve essere compreso tra 18 e 31", "Voto");
        }
    }

    public static void controllaCrediti(int crediti) throws InvalidParameterException {
        if (crediti < 1 || crediti > 15) {
            throw new InvalidParameterException("I Crediti devono essere compresi tra 1 e 15", "Crediti");
        }
    }
}


