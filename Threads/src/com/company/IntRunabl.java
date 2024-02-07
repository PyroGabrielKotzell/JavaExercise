package com.company;

public class IntRunabl implements Runnable {
    private String name;

    IntRunabl(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("THREAD START" + "\n\n");
        System.out.println("THREAD NAME: " + name + "\n\n");
        System.out.println("THREAD FINISHED" + "\n");
    }
}
