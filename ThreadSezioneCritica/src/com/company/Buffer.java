package com.company;

import java.util.ArrayList;

public class Buffer {
    // stack con i dati all'interno
    private final ArrayList<Integer> datas;

    // istanza del buffer per non farne creare altri
    private static Buffer instance;

    // grandezza massima del buffer
    private int maxSize = 10;

    private Buffer() {
        datas = new ArrayList<>();
    }

    // get dell'istanza del buffer
    public static Buffer instance() {
        if (instance == null) instance = new Buffer();
        return instance;
    }

    // aggiunta di dati dal thread produttore
    public void add(String name, int data) {
        synchronized (this) {
            // nel mentre è pieno mi fermo dall'aggiungere
            while (datas.size() >= maxSize) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // aggiunta e notify
            datas.add(data);
            System.out.println("-Thread " + name + " made integer: " + data);
            notifyAll();
        }
    }

    // set della grandezza massima del buffer
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    // get chiamato dai thread consumatori
    public int get(String name) {
        synchronized (this) {
            // nel mentre il buffer è vuoto mi fermo
            while (datas.isEmpty()) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // rimozione del dato aggiunto ultimamente (pop) e notify
            int i = datas.get(0);
            datas.remove(0);
            System.out.println("-Thread " + name + " got the integer: " + i);
            notifyAll();
            return i;
        }
    }

    // get dei dati nello stack del buffer
    public ArrayList<Integer> getDatas() {
        return datas;
    }
}