package com.company;

public class Counter {
    private int counter;
    private static Counter instance;

    private Counter(int c) {
        counter = c;
    }

    public static Counter instance() {
        if (instance == null) instance = new Counter(0);
        return instance;
    }

    public void add(int p, String name) {
        synchronized (this) {
            while (p != counter) {
                try {
                    wait();
                    System.out.println(" [Thread " + name + " woke up");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            counter++;
            System.out.println("-Thread " + name + " added");
            notifyAll();
        }
    }

    public int getCounter() {
        return counter;
    }
}
