package com.company;

public class UserThread extends Thread {
    UserThread(String nome) {
        super(nome);
    }

    public void run() {
        Datas.setResult(Datas.index, Datas.getV1(Datas.index) + Datas.getV2(Datas.index));
    }
}
