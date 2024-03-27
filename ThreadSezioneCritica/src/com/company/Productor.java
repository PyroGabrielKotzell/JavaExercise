package com.company;

import java.util.Random;

public class Productor extends Thread {
    private final int length;

    Productor(String name, int length) {
        super(name);
        this.length = length;
    }

    @Override
    public void run() {
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            Buffer.instance().add(getName(), r.nextInt(31));
        }
    }
}
