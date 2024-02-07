package com.company;

public class Main {

    public static void main(String[] args) {
        Datas.init(4);
        for (int i = 0; i < Datas.length(); i++) {
            UserThread us = new UserThread("thread " + i);
            us.start();
            Datas.index++;
        }
        Datas.print();
    }
}