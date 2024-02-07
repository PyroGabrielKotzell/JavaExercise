package com.company;

public class UserThread extends Thread {
    UserThread(String nome) {
        super(nome);
    }

    public void run() {
        System.out.println("THREAD START" + "\n\n");
        System.out.println("THREAD NAME: " + this.getName() + "\n");
        System.out.println("THREAD ID: " + this.getId() + "\n");
        System.out.println("THREAD PRIORITY: " + this.getPriority() + "\n");
        System.out.println("THREAD STATE: " + this.getState() + "\n\n");
        System.out.println("THREAD FINISHED" + "\n");
    }
}
