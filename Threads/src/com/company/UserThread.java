package com.company;

public class UserThread extends Thread {
    UserThread(String nome) {
        super(nome);
    }

    public void run() {
        for (int i = 0; i < 4; i++) {
            Datas.setResult(i, Datas.getV1(i) + Datas.getV2(i));
        }
    }
}
