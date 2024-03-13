package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int n = 100000;
        Productor p = new Productor("Productor", n);

        ArrayList<ThreadS> ts = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            ts.add(new ThreadS("Mario " + (j + 1), p));
        }

        p.start();
        for (ThreadS t : ts) {
            t.start();
        }

        int sum = 0;
        try {
            for (ThreadS t : ts) {
                t.join();
                sum += t.n;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(sum);
    }
}
