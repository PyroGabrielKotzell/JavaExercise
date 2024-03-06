package com.company;

public class TimedWorker extends Thread{
    private final int time;

    TimedWorker(String name, int time) {
        super(name);
        this.time = time;
    }

    public void run() {
        long s = System.currentTimeMillis();

        try {
            sleep(time);
        }catch (Exception ignored){}
        long e = System.currentTimeMillis();
        System.out.println("\nThread " + getName() + "\nTempo esecuzione: " + ((e-s)/1000d) + "s\n-Inizio: " + Main.timeToString(s) + "s\n-Fine: " + Main.timeToString(e) + "s");
    }
}