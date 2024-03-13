package com.company;


public class ThreadS extends Thread{
    private Productor p;
    int n;

    ThreadS(String nome, Productor p){
        super(nome);
        this.p = p;
    }

    @Override
    public void run() {
        n = p.get();
        System.out.println("Thread " + getName() + ", got prod: " + n);
    }
}
