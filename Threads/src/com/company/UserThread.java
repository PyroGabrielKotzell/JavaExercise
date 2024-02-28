package com.company;

public class UserThread extends Thread {
    private final int i;

    UserThread(String nome, int i) {
        super(nome);
        this.i = i;
    }

    public void run() {
        Datas.setResult(i, Datas.getV1(i) + Datas.getV2(i));
    }
}
