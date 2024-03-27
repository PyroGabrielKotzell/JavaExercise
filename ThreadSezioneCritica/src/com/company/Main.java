package com.company;

import java.util.ArrayList;

public class Main {
    // public static Productor p;

    public static void main(String[] args) {
        int len = 30;
        Consumer[] threads = new Consumer[len];
        Buffer.instance();

        Productor p = new Productor("Pino", len);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Consumer("Mariozzo " + (i + 1));
        }

        p.start();
        for (Consumer c : threads) {
            c.start();
        }
        try {
            p.join();
            for (Consumer t : threads) t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("data left: " + Buffer.instance().getDatas());
        for (int i = 0; i < threads.length; i++) {
            System.out.print(threads[i].getName() + "=" + threads[i].getData() + (i != 29 ? ", " : ""));
            if (i%10 == 9) System.out.println();
        }
    }
}