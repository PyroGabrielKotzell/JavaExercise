package com.company;

import java.util.Arrays;
import java.util.Random;

public abstract class Datas {
    private static final int[] V1 = new int[4];
    private static final int[] V2 = new int[4];
    private static final int[] V3 = new int[4];

    public static void init(){
        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            V1[i] = r.nextInt(11);
            V2[i] = r.nextInt(11);
        }
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
