package com.company;

import java.util.Arrays;

public class Statistica {
    private Dato[] data;
    private int count = 0;

    Statistica(int crea) {
        data = new Dato[crea];
        Arrays.fill(data, null);
    }

    public void addDato(String cognome, String nome, float altezza) {
        try {
            data[count] = new Dato(cognome, nome, altezza);
            count++;
        }catch (IndexOutOfBoundsException e){
            final Dato[] data2 = new Dato[data.length+1];
            System.arraycopy(data, 0, data2, 0, data.length);
            data = new Dato[data2.length];
            System.arraycopy(data2, 0, data, 0, data2.length);

            data[count] = new Dato(cognome, nome, altezza);
            count++;
        }
    }

    public void save(String filename) {
    }

    public void load(String filename) {
    }

    public Dato getDato(int pos) throws ArrayIndexOutOfBoundsException {
        return data[pos];
    }

    public int length(){
        return data.length;
    }

    public int getCount() {
        return count;
    }

    public void resetCount() {
        count = 0;
    }
}
