package com.company;

public class Main {

    public static void main(String[] args) {
        Datas.init();
        for (int i = 0; i < 4; i++) {
            UserThread us = new UserThread("thread " + i);
            us.start();
        }
        Datas.print();
    }
}