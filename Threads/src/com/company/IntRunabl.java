package com.company;

public class IntRunabl implements Runnable {

    @Override
    public void run() {
        System.out.println("THREAD START" + "\n\n");
        System.out.println("THREAD FINISHED" + "\n");
    }
}
