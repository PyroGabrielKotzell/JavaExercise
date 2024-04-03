package com.company;

import java.util.Random;

public class Productor extends Thread {
    // rispettivamente: quantità dei dati e massimo valore di un dato (per il random)
    private final int length, max;

    Productor(String name, int length, int max) {
        super(name);
        this.length = length;
        this.max = max;
    }

    @Override
    public void run() {
        Random r = new Random();
        // aggiungo un valore random al buffer
        for (int i = 0; i < length; i++)
            Buffer.instance().add(getName(), r.nextInt(max));
    }
}