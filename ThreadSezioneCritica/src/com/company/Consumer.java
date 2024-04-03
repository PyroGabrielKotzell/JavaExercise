package com.company;

public class Consumer extends Thread {
    private int data;

    Consumer(String name) {
        super(name);
    }

    @Override
    public void run() {
        // get di un dato dal buffer
        data = Buffer.instance().get(getName());
    }

    public int getData() {
        return data;
    }
}