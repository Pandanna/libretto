package com.example.libretto.classi;

import java.io.Serializable;

public class Esame implements Serializable {
    private String nome;
    private int CFU;
    private int voto;

    public Esame() {
        this.nome = "nome";
        this.CFU = 6;
        this.voto = 25;
    }

    public Esame(String nome, int CFU, int voto) {
        this.nome = nome;

        if(CFU < 1 || CFU > 15) {
            this.CFU = 6;
        }
        else {
            this.CFU = CFU;
        }

        if(voto < 18 || voto > 31) {
            this.voto = 25;
        }
        else {
            this.voto = voto;
        }
    }

    public String getNome() {return this.nome;}
    public int getCFU() {return this.CFU;}
    public int getVoto() {return this.voto;}
}
