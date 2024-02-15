package com.example.libretto.classi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Utente implements Serializable
{
    private String nomeutente;
    private String nome;
    private String cognome;
    private Date data_nascita;
    private String password;
    private ArrayList<Esame> esami = new ArrayList<>();

    public Utente()
    {
        this.nomeutente = "nomeutente";
        this.nome = "nome";
        this.cognome = "cognome";
        this.data_nascita = new Date();
        this.password = "password";
    }
    public Utente(String nomeutente, String nome, String cognome, Date data_nascita, String password)
    {
        this.nomeutente = nomeutente;
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data_nascita;
        this.password = password;
    }

    public String getNomeutente() {return this.nomeutente;}
    public String getNome() {return this.nome;}
    public String getCognome() {return this.cognome;}
    public Date getData_nascita() {return this.data_nascita;}
    public String getPassword() {return this.password;}
    public Esame getEsame(int i) {return this.esami.get(i);}
    public int getNumEsami() {return this.esami.size();}
    public ArrayList<Esame> getEsami() {return this.esami;}

    public void setNomeutente(String nomeutente) {this.nomeutente = nomeutente;}
    public void setNome(String nome) {this.nome = nome;}
    public void setCognome(String cognome) {this.cognome = cognome;}
    public void setData_nascita(Date data_nascita) {this.data_nascita = data_nascita;}
    public void setPassword(String password) {this.password = password;}

    public void aggiungiEsame(Esame esame) {this.esami.add(esame);}
    public void rimuoviEsame(int i) {this.esami.remove(i);}
}
