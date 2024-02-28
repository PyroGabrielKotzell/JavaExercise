package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Datas.init(4);

        ArrayList<UserThread> threads = new ArrayList<>();

        for (int i = 0; i < Datas.length(); i++) {
            threads.add(new UserThread("thread " + i, i));
            threads.get(i).start();
        }

        boolean areAlive;
        do {
            threads.removeIf(thread -> !thread.isAlive());
            areAlive = threads.size() > 0;
        }while (areAlive);

        Datas.print();
    }
}