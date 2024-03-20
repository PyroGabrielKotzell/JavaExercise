package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Person[] people = createPeople();
        ThreadC[] threads = new ThreadC[people.length];
        Lister.instance();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadC(people[i].getInfo()[0], people[i]);
        }

        System.out.println("{Threads: " + Arrays.toString(threads));

        for (ThreadC t : threads) {
            t.start();
        }

        for (ThreadC t : threads) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        for (Person p: Lister.instance().getServed()) {
            System.out.println(Arrays.toString(p.getInfo()));
            System.out.println();
        }
    }

    public static Person[] createPeople(){
        Person[] people = new Person[5];
        // quarto
        people[0] = new Person("Marco", "Maniero", "COOWO4U284YU", 3);
        // quinto
        people[1] = new Person("Torli", "Christian", "PKJIWLJRNCMN", 4);
        // primo
        people[2] = new Person("Funko", "POP", "8R3HOHOHORHO", 0);
        // secondo
        people[3] = new Person("Mario", "Mario", "MARIOMARIOMA", 1);
        // terzo
        people[4] = new Person("Luigi", "Mario", "UOMOVERDEBAF", 2);
        return people;
    }
}
