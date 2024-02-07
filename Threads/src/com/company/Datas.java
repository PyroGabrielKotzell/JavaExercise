package com.company;

import java.util.Arrays;
import java.util.Random;

public abstract class Datas {
    private static int[] V1;
    private static int[] V2;
    private static int[] V3;
    public static int index = 0;

    public static void init(int len){
        V1 = new int[len];
        V2 = new int[len];
        V3 = new int[len];
        Random r = new Random();
        for (int i = 0; i < len; i++) {
            V1[i] = r.nextInt(11);
            V2[i] = r.nextInt(11);
        }
    }

    public static int length() {
        return V1.length;
    }

    public static int getV1(int index) {
        return V1[index];
    }

    public static int getV2(int index) {
        return V2[index];
    }

    public static void setResult(int index, int number) {
        V3[index] = number;
    }

    public static void print() {
        System.out.println(Arrays.toString(V3));
    }
}
