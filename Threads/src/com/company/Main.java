package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Datas.init(100);

        // fase 1
        for (int i = 0; i < Datas.length(); i++) {
            Datas.add(Datas.get(i));
        }

        Datas.print();

        Datas.reset();

        // fase 2
        ArrayList<Worker> threads = new ArrayList<>();

        int var = 3;
        int limit = var;
        int starting_i = 0;

        for (int i = 0; i < Datas.length(); i++) {
            threads.add(new Worker(starting_i, limit));
            threads.get(i).start();
            starting_i = limit;
            limit += var;
        }

        for (Worker t : threads) {
            try{
                t.join();
            }catch (Exception e){}
            Datas.add(t.getResult());
        }

        Datas.print();

        Datas.reset();

        // fase 3

        RecursiveWorker rw = new RecursiveWorker(0, Datas.length());
        rw.start();

        try{
            rw.join();
        }catch (Exception e){}

        Datas.add(rw.getResult());
        Datas.print();
    }
}