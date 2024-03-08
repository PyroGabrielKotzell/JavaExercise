package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        boolean calc = true;

        long s = System.currentTimeMillis(), e;

        if (calc) {
            Datas.init(100000000);

            // fase 1
            s = System.currentTimeMillis();
            for (int i = 0; i < Datas.length(); i++) {
                Datas.add(Datas.get(i));
            }

            e = System.currentTimeMillis();
            System.out.println("tempo fase 1: " + ((e - s) / 1000d) + "s");

            Datas.print();
            Datas.reset();

            // fase 2
            ArrayList<Worker> workers = new ArrayList<>();

            int n = 3;
            int var = Datas.length() / n;
            int limit = var;
            int starting_i = 0;
            s = System.currentTimeMillis();

            for (int i = 0; i < n + 1; i++) {
                workers.add(new Worker(starting_i, limit));
                workers.get(i).start();
                starting_i = limit;
                limit += var;
            }

            for (Worker w : workers) {
                try {
                    w.join();
                } catch (Exception ex) {
                }
                Datas.add(w.getResult());
            }

            e = System.currentTimeMillis();
            System.out.println("tempo fase 2: " + ((e - s) / 1000d) + "s");

            Datas.print();
            Datas.reset();

            // fase 3
            s = System.currentTimeMillis();
            RecursiveWorker rw = new RecursiveWorker(0, Datas.length(), var);
            rw.start();

            try {
                rw.join();
            } catch (Exception ex) {
            }

            e = System.currentTimeMillis();
            System.out.println("tempo fase 3: " + ((e - s) / 1000d) + "s");

            Datas.add(rw.getResult());
            Datas.print();
        }
        else {
            ArrayList<TimedWorker> tws = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                Random r = new Random();
                tws.add(new TimedWorker("TimedWorker " + i, r.nextInt(10) * 1000));
                tws.get(i).start();
            }

            for (TimedWorker tw : tws) {
                try {
                    tw.join();
                } catch (Exception ex) {
                }
            }

            e = System.currentTimeMillis();
            System.out.println("\nThread Main\nTempo esecuzione: " + ((e - s) / 1000d) + "s\n-Inizio: " + timeToString(s) + "s\n-Fine: " + timeToString(e) + "s");
        }
    }

    public static String timeToString(long millis){
        return new SimpleDateFormat("hh:mm:ss:SS").format(new Date(millis));
    }
}