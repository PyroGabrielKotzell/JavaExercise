package com.company;

public class Consumer extends Thread {
    private int data;

    Consumer(String name) {
        super(name);
    }

    @Override
    public void run() {
        data = Buffer.instance().get(getName());
    }

    public int getData() {
        return data;
    }
}