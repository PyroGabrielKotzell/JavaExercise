package com.company;

public class Dato {
    private final String cognome, nome;
    private final float altezza;
    Dato(String cognome, String nome, float altezza){
        this.cognome = cognome;
        this.nome = nome;
        this.altezza = altezza;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public float getAltezza() {
        return altezza;
    }

    public String toString(){
        return getCognome() + ";" + getNome() + ";" + getAltezza();
    }
}
