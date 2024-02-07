package com.company;

public class Main {

    public static void main(String[] args) {
        UserThread userThread = new UserThread("user thread");
        int i = 0;
        while (i < 4) {
            if (!userThread.isAlive()) {
                i++;
                userThread.interrupt();
                userThread = new UserThread("user thread");
                userThread.start();
            }
        }

        i = 0;
        IntRunabl intRunabl = new IntRunabl();
        Thread intThread = new Thread(intRunabl);
        while (i < 4) {
            if (!intThread.isAlive()) {
                i++;
                intThread.interrupt();
                intThread = new Thread(intRunabl);
                intThread.start();
            }
        }
    }
}