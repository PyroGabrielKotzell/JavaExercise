package com.company;

public class Productor extends Thread{
    private final int max;
    private int counter;

    Productor(String nome, int max){
        super(nome);
        this.max = max;
    }

    @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            counter++;
        }
    }

    public int get() {
        if (counter <= 0) {
            if (!isAlive()) return -1;
            return 0;
        }
        counter--;
        return 1;
    }
}
