package com.company;

import java.util.Arrays;
import java.util.Random;

public abstract class Datas {
    private static int[] V;
    private static int result;
    private static boolean accessed;

    public static void init(int len) {
        V = new int[len];
        result = 0;
        accessed = false;
        Random r = new Random();
        for (int i = 0; i < len; i++) {
            V[i] = r.nextInt(11);
        }
        //System.out.println(Arrays.toString(V));
    }

    public static int length() {
        return V.length;
    }

    public static int get(int index) {
        return V[index];
    }

    public static void add(int number) {
        result += number;
    }

    public static void reset() {
        result = 0;
    }

    public static void print() {
        System.out.println(result + "\n");
    }
}