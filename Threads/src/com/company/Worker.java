package com.company;

public class Worker extends Thread {
    private final int limit, starting_i;
    private int result;

    Worker(int starting_i, int limit) {
        super();
        this.starting_i = starting_i;
        this.limit = Math.min(limit, Datas.length());
    }

    public void run() {
        for (int i = starting_i; i < limit; i++) result += Datas.get(i);
    }

    public int getResult(){
        return result;
    }
}