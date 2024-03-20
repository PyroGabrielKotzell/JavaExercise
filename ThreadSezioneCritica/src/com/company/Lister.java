package com.company;

import java.util.ArrayList;

public class Lister {
    private final ArrayList<Person> served;
    private static Lister instance;

    private Lister() {
        served = new ArrayList<>();
    }

    public static Lister instance() {
        if (instance == null) instance = new Lister();
        return instance;
    }

    public void add(Person person, String name) {
        synchronized (this) {
            while (person.getPriority() != served.size()) {
                try {
                    wait();
                    System.out.println(" [Thread " + name + " woke up");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            served.add(person);
            System.out.println("-Thread " + name + " was served");
            notifyAll();
        }
    }

    public ArrayList<Person> getServed() {
        return served;
    }
}
