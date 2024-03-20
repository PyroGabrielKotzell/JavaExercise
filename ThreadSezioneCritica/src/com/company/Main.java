package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<ThreadC> threads = new ArrayList<>();
        Counter.instance();

        for (int i = 30; i > -1; i--) {
            threads.add(new ThreadC("Marco " + i, i));
        }

        System.out.println("{Threads: " + threads.toString());

        for (ThreadC t : threads) {
            t.start();
        }

        for (ThreadC t : threads) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(Counter.instance().getCounter());
    }
}
