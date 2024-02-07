package com.company;

public class Main {

    public static void main(String[] args) {
        UserThread userThread = new UserThread("user thread 0");
        userThread.start();
        int i = 0;
        while (i < 4) {
            if (!userThread.isAlive()) {
                i++;
                userThread.interrupt();
                userThread = new UserThread("user thread " + i);
                userThread.start();
            }
        }

        i = 0;
        IntRunabl intRunabl = new IntRunabl("thread " + i);
        Thread intThread = new Thread(intRunabl);
        intThread.start();
        while (i < 4) {
            if (!intThread.isAlive()) {
                i++;
                intThread.interrupt();
                intRunabl = new IntRunabl("thread " + i);
                intThread = new Thread(intRunabl);
                intThread.start();
            }
        }
    }
}