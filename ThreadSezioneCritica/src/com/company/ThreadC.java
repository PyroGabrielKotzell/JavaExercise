package com.company;

public class ThreadC extends Thread {
    private final Person person;

    ThreadC(String name, Person person) {
        super(name);
        this.person = person;
    }

    @Override
    public void run() {
        Lister.instance().add(person, getName());
    }
}