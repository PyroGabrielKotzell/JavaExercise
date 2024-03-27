package com.company;

import java.util.Stack;

public class Buffer {
    private final Stack<Integer> datas;
    private static Buffer instance;

    private Buffer() {
        datas = new Stack<>();
    }

    public static Buffer instance() {
        if (instance == null) instance = new Buffer();
        return instance;
    }

    public void add(String name, int data) {
        synchronized (this) {
            while (datas.size() >= 10) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            datas.add(data);
            System.out.println("-Thread " + name + " made integer: " + data);
            notifyAll();
        }
    }

    public int get(String name) {
        synchronized (this) {
            while (datas.empty()) {
                try {
                    wait();
                    //if () return 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            int i = datas.pop();
            System.out.println("-Thread " + name + " got the integer: " + i);
            notifyAll();
            return i;
        }
    }

    public Stack<Integer> getDatas() {
        return datas;
    }
}