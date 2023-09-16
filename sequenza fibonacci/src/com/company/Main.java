package com.company;
//fibonacci
public class Main {

    public static void main(String[] args) {
        int n = 20;
        for (int i = 0; i <= n; i++) {
            int m = fibonacci(i);
            System.out.println(" " + m);
        }
    }

    public static int fibonacci(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }
}