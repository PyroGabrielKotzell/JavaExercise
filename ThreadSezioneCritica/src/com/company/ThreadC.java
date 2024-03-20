package com.company;

public class ThreadC extends Thread {
    private final int priority;

    ThreadC(String nome, int priority) {
        super(nome);
        this.priority = priority;
    }

    @Override
    public void run() {
        Counter.instance().add(priority, getName());
    }
}